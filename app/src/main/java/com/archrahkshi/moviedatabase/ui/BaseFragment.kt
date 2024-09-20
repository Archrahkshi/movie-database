package com.archrahkshi.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.archrahkshi.moviedatabase.data.DataObject
import com.archrahkshi.moviedatabase.data.network.Response
import com.archrahkshi.moviedatabase.data.vo.ViewObject
import com.archrahkshi.moviedatabase.databinding.ProgressBarBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import timber.log.Timber.Forest.e

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!
    private var _progressBarBinding: ProgressBarBinding? = null
    private val progressBarBinding get() = _progressBarBinding!!
    private val adapter by lazy<GroupAdapter<GroupieViewHolder>>(::GroupAdapter)
    private val compositeDisposable by lazy(::CompositeDisposable)

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding(inflater, container)
        _progressBarBinding = ProgressBarBinding.bind(binding.root)
        return binding.root
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _progressBarBinding = null
        adapter.clear()
    }

    private fun addToCompositeDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun <T : Any> Single<T>.subscribeAndDispose(action: T.() -> Unit) {
        addToCompositeDisposable(subscribe(action, ::e))
    }

    protected fun <T : Any> Single<T>.applySchedulers(
        subscribeScheduler: Scheduler = io(),
        observeScheduler: Scheduler = mainThread()
    ) = subscribeOn(subscribeScheduler).observeOn(observeScheduler)

    protected fun <T : Any> Single<T>.withProgressBar(
        vararg viewsToHideWhileLoading: View
    ) = doOnSubscribe {
        viewsToHideWhileLoading.forEach { it.isVisible = false }
        progressBarBinding.progressBar.isVisible = true
    }.doFinally {
        progressBarBinding.progressBar.isVisible = false
        viewsToHideWhileLoading.forEach { it.isVisible = true }
    }

    protected fun <T : Any> Observable<T>.onReceive(
        subscribeScheduler: Scheduler = io(),
        observeScheduler: Scheduler = mainThread(),
        action: T.() -> Unit
    ) {
        addToCompositeDisposable(
            subscribeOn(subscribeScheduler).observeOn(observeScheduler).subscribe(action, ::e)
        )
    }

    protected fun <T : Response> Single<T>.render(
        view: RecyclerView,
        action: GroupAdapter<GroupieViewHolder>.(ViewObject) -> Unit
    ) {
        subscribeAndDispose {
            action(adapter, toViewObject())
            view.adapter = adapter
        }
    }

    protected fun <T : List<DataObject>> Single<T>.renderAll(
        view: RecyclerView,
        action: List<ViewObject>.(GroupAdapter<GroupieViewHolder>) -> Unit
    ) {
        subscribeAndDispose {
            adapter.clear()
            action(map { it.toViewObject() }, adapter)
            view.adapter = adapter
        }
    }
}

package com.archrahkshi.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
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
    protected val binding: Binding get() = _binding!!
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
        adapter.clear()
    }

    private fun addToCompositeDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
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

    protected fun <T : Any> Single<T>.onReceive(
        subscribeScheduler: Scheduler = io(),
        observeScheduler: Scheduler = mainThread(),
        action: T.() -> Unit
    ) {
        addToCompositeDisposable(
            subscribeOn(subscribeScheduler).observeOn(observeScheduler).subscribe(action, ::e)
        )
    }

    protected fun <T : Any> Single<T>.render(
        view: RecyclerView,
        subscribeScheduler: Scheduler = io(),
        observeScheduler: Scheduler = mainThread(),
        action: T.(GroupAdapter<GroupieViewHolder>) -> Unit
    ) {
        onReceive(subscribeScheduler, observeScheduler) {
            view.adapter = adapter.also { groupAdapter ->
                action(this, groupAdapter)
            }
        }
    }
}

package com.archrahkshi.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import timber.log.Timber.Forest.e

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!
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
        compositeDisposable.dispose()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun <T : Any> Single<T>.then(
        subscribeScheduler: Scheduler = io(),
        observeScheduler: Scheduler = mainThread(),
        action: T.() -> Unit
    ) {
        compositeDisposable.add(
            subscribeOn(subscribeScheduler).observeOn(observeScheduler).subscribe(action, ::e)
        )
    }
}

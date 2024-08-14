package com.archrahkshi.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.FeedHeaderBinding
import timber.log.Timber.Forest.d

private const val MIN_LENGTH = 3
const val KEY_SEARCH = "search"

abstract class BaseFragmentWithSearch<Binding : ViewBinding> : BaseFragment<Binding>() {
    private var _searchBinding: FeedHeaderBinding? = null
    private val searchBinding get() = _searchBinding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bindHeader()
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

    private fun bindHeader() {
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        requireActivity().addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
                    inflater.inflate(R.menu.main_menu, menu)
                }

                override fun onMenuItemSelected(item: MenuItem): Boolean {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    private fun openSearch(searchText: String) {
        findNavController().navigate(
            R.id.search_dest,
            bundleOf(KEY_SEARCH to searchText),
            navOptions
        )
    }
}
package com.archrahkshi.moviedatabase.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archrahkshi.moviedatabase.databinding.FeedHeaderBinding
import com.archrahkshi.moviedatabase.databinding.SearchFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.KEY_SEARCH

class SearchFragment : BaseFragment<SearchFragmentBinding>() {
    private var _searchBinding: FeedHeaderBinding? = null
    private val searchBinding get() = _searchBinding!!

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        SearchFragmentBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBinding.searchToolbar.setText(requireArguments().getString(KEY_SEARCH))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }
}

package com.archrahkshi.moviedatabase.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.FeedHeaderBinding
import com.archrahkshi.moviedatabase.databinding.SearchFragmentBinding
import com.archrahkshi.moviedatabase.ui.KEY_SEARCH

class SearchFragment : Fragment(R.layout.search_fragment) {

    private var _binding: SearchFragmentBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        searchBinding.searchToolbar.setText(searchTerm)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }
}

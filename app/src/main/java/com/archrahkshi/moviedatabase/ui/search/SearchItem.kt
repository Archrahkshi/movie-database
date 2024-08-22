package com.archrahkshi.moviedatabase.ui.search

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.POSTER_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.databinding.SearchItemBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class SearchItem(
    private val content: Movie,
    private val onClick: (Movie) -> Unit
) : BindableItem<SearchItemBinding>() {
    override fun bind(viewBinding: SearchItemBinding, position: Int) {
        with(viewBinding) {
            searchItemPoster.loadFromPath(content.posterPath, POSTER_WIDTH)
            searchItemTitle.text = content.title
            searchItemRating.text = content.voteAverage
            searchItem.setOnClickListener { onClick(content) }
            searchItemYear.text = content.year
        }
    }

    override fun getLayout() = R.layout.search_item

    override fun initializeViewBinding(view: View) = SearchItemBinding.bind(view)
}

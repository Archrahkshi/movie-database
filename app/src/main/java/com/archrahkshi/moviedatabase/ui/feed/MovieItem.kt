package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.databinding.ItemWithTextBinding
import com.archrahkshi.moviedatabase.ui.loadFromUrl
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemWithTextBinding>() {
    override fun bind(viewBinding: ItemWithTextBinding, position: Int) {
        with(viewBinding) {
            movieTitle.text = content.title
            // content.voteAverage is 0..10, but rating in RatingBar is 0..5 stars
            movieRating.rating = content.voteAverage / 2
            container.setOnClickListener { onClick(content) }
            moviePreview.loadFromUrl("$IMAGE_BASE_URL${content.posterPath}")
        }
    }

    override fun getLayout() = R.layout.item_with_text

    override fun initializeViewBinding(view: View) = ItemWithTextBinding.bind(view)
}

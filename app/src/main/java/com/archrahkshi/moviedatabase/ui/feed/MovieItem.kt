package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.databinding.ItemCardBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemCardBinding>() {
    override fun bind(viewBinding: ItemCardBinding, position: Int) {
        with(viewBinding) {
            moviePoster.loadFromPath(content.posterPath!!)
            movieTitle.text = content.title!!
            // content.voteAverage is 0..10, but rating in RatingBar is 0..5 stars
            movieRating.rating = content.voteAverage / 2
            movieCard.setOnClickListener { onClick(content) }
        }
    }

    override fun getLayout() = R.layout.item_card

    override fun initializeViewBinding(view: View) = ItemCardBinding.bind(view)
}

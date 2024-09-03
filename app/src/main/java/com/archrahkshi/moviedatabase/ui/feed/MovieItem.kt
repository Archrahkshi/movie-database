package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import android.view.View.OnClickListener
import com.archrahkshi.moviedatabase.BuildConfig.POSTER_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.vo.Movie
import com.archrahkshi.moviedatabase.databinding.ItemCardBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(
    private val content: Movie,
    private val onClick: OnClickListener
) : BindableItem<ItemCardBinding>() {
    override fun bind(viewBinding: ItemCardBinding, position: Int) {
        with(viewBinding) {
            moviePoster.loadFromPath(content.posterPath, POSTER_WIDTH)
            movieTitle.text = content.title
            movieRating.rating = content.ratingInStars
            movieCard.setOnClickListener(onClick)
        }
    }

    override fun getLayout() = R.layout.item_card

    override fun initializeViewBinding(view: View) = ItemCardBinding.bind(view)
}

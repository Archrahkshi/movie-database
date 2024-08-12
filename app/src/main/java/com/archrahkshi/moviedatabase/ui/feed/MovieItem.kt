package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.databinding.ItemWithTextBinding
import com.archrahkshi.moviedatabase.util.loadFromUrl
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun getLayout() = R.layout.item_with_text

    override fun bind(viewBinding: ItemWithTextBinding, position: Int) {
        with(viewBinding) {
            description.text = content.title
            // content.rating is 0..10, but rating in RatingBar is 0..5 with step 0.1
            movieRating.rating = content.voteAverage / 2
            container.setOnClickListener { onClick(content) }
            imagePreview.loadFromUrl("$IMAGE_BASE_URL${content.posterPath}")
        }
    }

    override fun initializeViewBinding(view: View) = ItemWithTextBinding.bind(view)
}

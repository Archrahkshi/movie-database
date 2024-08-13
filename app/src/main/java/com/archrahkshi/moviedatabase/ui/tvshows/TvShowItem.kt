package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.ItemTvShowBinding
import com.archrahkshi.moviedatabase.ui.loadFromUrl
import com.xwray.groupie.viewbinding.BindableItem

class TvShowItem(
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewBinding: ItemTvShowBinding, position: Int) {
        with(viewBinding) {
            tvShowTitle.text = content.name
            // content.voteAverage is 0..10, but rating in RatingBar is 0..5 stars
            tvShowRating.rating = content.voteAverage / 2
            itemTvShow.setOnClickListener { onClick(content) }
            tvShowPreview.loadFromUrl("$IMAGE_BASE_URL${content.posterPath}")
        }
    }

    override fun initializeViewBinding(view: View) = ItemTvShowBinding.bind(view)
}

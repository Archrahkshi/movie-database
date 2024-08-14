package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.View
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.ItemTvShowBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class TvShowItem(
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewBinding: ItemTvShowBinding, position: Int) {
        with(viewBinding) {
            tvShowPreview.loadFromPath(content.posterPath!!)
            tvShowTitle.text = content.name!!
            // content.voteAverage is 0..10, but rating in RatingBar is 0..5 stars
            tvShowRating.rating = content.voteAverage / 2
            itemTvShow.setOnClickListener { onClick(content) }
        }
    }

    override fun initializeViewBinding(view: View) = ItemTvShowBinding.bind(view)
}

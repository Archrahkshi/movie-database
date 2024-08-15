package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.ItemTvShowBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.archrahkshi.moviedatabase.ui.toStars
import com.xwray.groupie.viewbinding.BindableItem

class TvShowItem(
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {
    override fun bind(viewBinding: ItemTvShowBinding, position: Int) {
        with(viewBinding) {
            content.posterPath?.let { tvShowPreview.loadFromPath(it, BACKDROP_WIDTH) }
            tvShowTitle.text = content.name
            tvShowRating.rating = content.voteAverage.toStars()
            itemTvShow.setOnClickListener { onClick(content) }
        }
    }

    override fun getLayout() = R.layout.item_tv_show

    override fun initializeViewBinding(view: View) = ItemTvShowBinding.bind(view)
}

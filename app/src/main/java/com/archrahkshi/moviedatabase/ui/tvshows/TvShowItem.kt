package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.View
import android.view.View.OnClickListener
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.ItemTvShowBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class TvShowItem(
    private val content: TvShow,
    private val onClick: OnClickListener
) : BindableItem<ItemTvShowBinding>() {
    override fun bind(viewBinding: ItemTvShowBinding, position: Int) {
        with(viewBinding) {
            tvShowPreview.loadFromPath(content.posterPath, BACKDROP_WIDTH)
            tvShowTitle.text = content.name
            tvShowRating.rating = content.ratingInStars
            itemTvShow.setOnClickListener(onClick)
        }
    }

    override fun getLayout() = R.layout.item_tv_show

    override fun initializeViewBinding(view: View) = ItemTvShowBinding.bind(view)
}

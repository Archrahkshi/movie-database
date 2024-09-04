package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.View
import android.view.View.OnClickListener
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.vo.TvShow
import com.archrahkshi.moviedatabase.databinding.TvShowItemBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class TvShowItem(
    private val content: TvShow,
    private val onClick: OnClickListener
) : BindableItem<TvShowItemBinding>() {
    override fun bind(viewBinding: TvShowItemBinding, position: Int) {
        with(viewBinding) {
            tvShowPreview.loadFromPath(content.posterPath, BACKDROP_WIDTH)
            tvShowTitle.text = content.name
            tvShowRating.rating = content.ratingInStars
            tvShowItem.setOnClickListener(onClick)
        }
    }

    override fun getLayout() = R.layout.tv_show_item

    override fun initializeViewBinding(view: View) = TvShowItemBinding.bind(view)
}

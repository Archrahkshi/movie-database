package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.View
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.ItemTvShowBinding
import com.archrahkshi.moviedatabase.util.loadFromUrl
import com.xwray.groupie.viewbinding.BindableItem

class TvShowItem(
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewBinding: ItemTvShowBinding, position: Int) {
        with(viewBinding) {
            tvShowTitle.text = content.title
            // content.rating is 0..10, but rating in RatingBar is 0..5 with step 0.5
            tvShowRating.rating = content.rating / 2
            itemTvShow.setOnClickListener { onClick(content) }
            tvShowPreview.loadFromUrl(content.previewUrl)
        }
    }

    override fun initializeViewBinding(view: View) = ItemTvShowBinding.bind(view)
}

package ru.androidschool.intensiv.ui.tvshows

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.TvShow
import ru.androidschool.intensiv.databinding.ItemTvShowBinding
import ru.androidschool.intensiv.util.loadFromUrl

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

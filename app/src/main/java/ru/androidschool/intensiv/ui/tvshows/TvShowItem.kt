package ru.androidschool.intensiv.ui.tvshows

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.TvShow
import ru.androidschool.intensiv.databinding.ItemTvShowBinding

class TvShowItem(
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewBinding: ItemTvShowBinding, position: Int) {
        viewBinding.tvShowName.text = content.title
        viewBinding.tvShowRating.rating = content.voteAverage / 2
        viewBinding.itemTvShow.setOnClickListener { onClick(content) }

        Picasso.get()
            .load("https://i.ytimg.com/vi/mkD5Nsr4vfc/maxresdefault.jpg")
            .into(viewBinding.tvShowPreview)
    }

    override fun initializeViewBinding(view: View) = ItemTvShowBinding.bind(view)
}

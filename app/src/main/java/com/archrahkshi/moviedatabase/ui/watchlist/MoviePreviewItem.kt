package com.archrahkshi.moviedatabase.ui.watchlist

import android.view.View
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.vo.Movie
import com.archrahkshi.moviedatabase.databinding.ItemSmallBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class MoviePreviewItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout() = R.layout.item_small

    override fun bind(view: ItemSmallBinding, position: Int) {
        view.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }
        // TODO Получать из модели
        Picasso.get()
            .load("https://www.kinopoisk.ru/images/film_big/1143242.jpg")
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(v: View): ItemSmallBinding = ItemSmallBinding.bind(v)
}

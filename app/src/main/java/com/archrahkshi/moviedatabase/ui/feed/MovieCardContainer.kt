package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.ItemCardBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class MovieCardContainer(
    private val title: String,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemCardBinding>() {

    override fun getLayout() = R.layout.item_card

    override fun bind(view: ItemCardBinding, position: Int) {
        view.movieCardTitle.text = title
        view.itemsContainer.adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }

    override fun initializeViewBinding(view: View): ItemCardBinding = ItemCardBinding.bind(view)
}

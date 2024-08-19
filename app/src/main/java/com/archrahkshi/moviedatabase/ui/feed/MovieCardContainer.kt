package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.ItemCardContainerBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class MovieCardContainer(
    private val title: String,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemCardContainerBinding>() {
    override fun bind(view: ItemCardContainerBinding, position: Int) {
        view.movieCardTitle.text = title
        view.itemsContainer.adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }

    override fun getLayout() = R.layout.item_card_container

    override fun initializeViewBinding(view: View) = ItemCardContainerBinding.bind(view)
}

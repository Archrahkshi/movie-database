package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import androidx.annotation.StringRes
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.ItemCardBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class MovieCardContainer(
    @StringRes private val title: Int,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemCardBinding>() {

    override fun getLayout() = R.layout.item_card

    override fun bind(view: ItemCardBinding, position: Int) {
        view.titleTextView.text = view.titleTextView.context.getString(title)
        view.itemsContainer.adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }

    override fun initializeViewBinding(view: View): ItemCardBinding = ItemCardBinding.bind(view)
}

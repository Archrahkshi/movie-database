package com.archrahkshi.moviedatabase.ui.watchlist

import android.view.View
import android.view.View.OnClickListener
import com.archrahkshi.moviedatabase.BuildConfig.WATCHLIST_ITEM_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.WatchlistItemBinding
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class WatchlistItem(
    private val posterPath: String,
    private val onClick: OnClickListener
) : BindableItem<WatchlistItemBinding>() {
    override fun bind(viewBinding: WatchlistItemBinding, position: Int) {
        viewBinding.watchlistItemPreview.loadFromPath(posterPath, WATCHLIST_ITEM_WIDTH)
        viewBinding.watchlistItemPreview.setOnClickListener(onClick)
    }

    override fun getLayout() = R.layout.watchlist_item

    override fun initializeViewBinding(view: View)= WatchlistItemBinding.bind(view)
}

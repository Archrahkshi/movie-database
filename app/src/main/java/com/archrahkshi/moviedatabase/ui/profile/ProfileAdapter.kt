package com.archrahkshi.moviedatabase.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.archrahkshi.moviedatabase.ui.watchlist.WatchlistFragment

class ProfileAdapter(
    fragment: Fragment,
    private val itemsCount: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = itemsCount

    override fun createFragment(position: Int) = WatchlistFragment.newInstance()
}

package com.archrahkshi.moviedatabase.ui.profile

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.archrahkshi.moviedatabase.MovieDatabaseApp.Companion.appContext
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.db.AppDatabase
import com.archrahkshi.moviedatabase.databinding.ProfileFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ProfileFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(R.drawable.ic_avatar)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.ic_avatar)
            .into(binding.profileAvatar)

        val tabTitles = resources.getStringArray(R.array.tab_titles)

        binding.profileViewPager.adapter = ProfileAdapter(this, tabTitles.size)

        AppDatabase.getInstance(appContext).movieDao().getFavoriteMovies()
            .applySchedulers()
            .subscribeAndDispose {
                TabLayoutMediator(binding.tabLayout, binding.profileViewPager) { tab, position ->
                    tab.text = SpannableString("$size\n${tabTitles[position]}").also {
                        it.setSpan(RelativeSizeSpan(2f), 0, size.toString().length, 0)
                    }
                }.attach()
            }
    }

    companion object {
        val recycledViewPool = RecycledViewPool()
    }
}

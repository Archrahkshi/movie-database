package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.data.MockRepository
import ru.androidschool.intensiv.data.TvShow
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.ui.BaseFragment

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        TvShowsFragmentBinding.inflate(inflater, container, false)

    private val adapter by lazy<GroupAdapter<GroupieViewHolder>>(::GroupAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvShowsRecyclerView.adapter = adapter.apply {
            add(
                TvShowItem(
                    TvShow(
                        "A TV show with a very very very very long title",
                        10f,
                        "https://i.ytimg.com/vi/mkD5Nsr4vfc/maxresdefault.jpg"
                    )
                ) {}
            )
            addAll(MockRepository.getTvShows().map { TvShowItem(it) {} })
        }
    }
}

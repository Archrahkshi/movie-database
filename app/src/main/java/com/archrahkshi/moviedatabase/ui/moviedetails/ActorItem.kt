package com.archrahkshi.moviedatabase.ui.moviedetails

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.PROFILE_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Actor
import com.archrahkshi.moviedatabase.databinding.ActorItemBinding
import com.archrahkshi.moviedatabase.loadFromPath
import com.xwray.groupie.viewbinding.BindableItem

class ActorItem(private val content: Actor) : BindableItem<ActorItemBinding>() {
    override fun bind(viewBinding: ActorItemBinding, position: Int) {
        with(viewBinding) {
            content.profilePath?.let { actorImage.loadFromPath(it, PROFILE_WIDTH) }
            actorName.text = content.name
        }
    }

    override fun getLayout() = R.layout.actor_item

    override fun initializeViewBinding(view: View) = ActorItemBinding.bind(view)
}

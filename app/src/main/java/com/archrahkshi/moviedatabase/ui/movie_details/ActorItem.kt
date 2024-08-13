package com.archrahkshi.moviedatabase.ui.movie_details

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.IMAGE_BASE_URL
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Actor
import com.archrahkshi.moviedatabase.databinding.ActorItemBinding
import com.archrahkshi.moviedatabase.ui.loadFromUrl
import com.xwray.groupie.viewbinding.BindableItem

class ActorItem(private val content: Actor) : BindableItem<ActorItemBinding>() {
    override fun bind(viewBinding: ActorItemBinding, position: Int) {
        with(viewBinding) {
            actorImage.loadFromUrl("$IMAGE_BASE_URL${content.profilePath}")
            actorName.text = content.name
        }
    }

    override fun getLayout() = R.layout.actor_item

    override fun initializeViewBinding(view: View) = ActorItemBinding.bind(view)
}

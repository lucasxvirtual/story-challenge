package com.lucasxvirtual.stories.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucasxvirtual.stories.data.model.Story
import com.lucasxvirtual.stories.databinding.ItemStoryBinding
import com.lucasxvirtual.stories.databinding.ItemStoryVideoBinding
import com.lucasxvirtual.stories.presentation.util.MyTouchEvent
import java.net.URLConnection


class StoryAdapter(private var stories : List<Story>,
                   private var screenX : Int,
                   private var next: () -> Unit,
                   private var previous: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIDEO){
            StoryVideoViewHolder(
                    ItemStoryVideoBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                    ),
                    screenX,
                    next,
                    previous
            )
        } else {
            StoryViewHolder(
                    ItemStoryBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                    ),
                    screenX,
                    next,
                    previous
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val type = URLConnection.guessContentTypeFromName(stories[position].largeUrl.split("%")[0])
        if(type != null && type.contains("video", true)){
            return VIDEO
        }
        return IMAGE
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = stories[position]
        when(holder){
            is StoryVideoViewHolder -> holder.bind(story)
            is StoryViewHolder -> holder.bind(story)
            else -> throw IllegalStateException()
        }
    }

    fun replaceItems(items: List<*>) {
        this.stories = items.filterIsInstance<Story>() as ArrayList<Story>
        notifyDataSetChanged()
    }

    companion object {
        const val VIDEO = 0
        const val IMAGE = 1
    }
}

class StoryViewHolder internal constructor(
    private val binding : ItemStoryBinding,
    private val screenX : Int,
    private val next : () -> Unit,
    private val previous : () -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(story : Story) {
        Glide.with(binding.image)
            .load(story.url)
            .override(100, 0)
            .thumbnail(.05f)
            .into(binding.image)

        setupOnTouchListener()
    }

    private fun setupOnTouchListener(){

        val onTouchListener = MyTouchEvent(screenX) {
            when(it) {
                MyTouchEvent.EventType.LEFT -> {
                    previous.invoke()
                }
                MyTouchEvent.EventType.RIGHT -> {
                    next.invoke()
                }
                else -> {}
            }
        }

        binding.root.setOnTouchListener(onTouchListener)

    }

}

class StoryVideoViewHolder internal constructor(
        private val binding : ItemStoryVideoBinding,
        private val screenX : Int,
        private val next : () -> Unit,
        private val previous : () -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(story : Story) {
        binding.video.setVideoPath(story.largeUrl)
        binding.video.seekTo(1)
        binding.video.setOnPreparedListener {
            it.isLooping = true
            binding.video.start()
        }

        setupOnTouchListener()
    }

    private fun setupOnTouchListener(){

        val onTouchListener = MyTouchEvent(screenX) {
            when(it) {
                MyTouchEvent.EventType.LEFT -> {
                    previous.invoke()
                }
                MyTouchEvent.EventType.RIGHT -> {
                    next.invoke()
                }
                MyTouchEvent.EventType.DOWN -> {
                    binding.video.pause()
                }
                MyTouchEvent.EventType.UP -> {
                    binding.video.start()
                }
            }
        }

        binding.root.setOnTouchListener(onTouchListener)

    }

}
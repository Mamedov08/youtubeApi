package com.example.youtubeapi.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtubeapi.databinding.ItemPlaylistaBinding
import com.example.youtubeapi.model.Item
import com.example.youtubeapi.model.Playlists
import com.example.youtubeapi.playlists.PlaylistsAdapter.PlaylistsViewHolder

class PlaylistsAdapter(
    private val onClickItem: (id: String) -> Unit
) : RecyclerView.Adapter<PlaylistsViewHolder>() {

    private var playLists = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            ItemPlaylistaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        playLists[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return playLists.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(playLists: ArrayList<Item>) {
        this.playLists = playLists
        notifyDataSetChanged()
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.image.load(item.snippet?.thumbnails?.default?.url)
            binding.tvTitle.text = item.snippet?.title
            itemView.setOnClickListener {
                item.id?.let {
                    onClickItem(it)
                }
            }
        }
    }
}



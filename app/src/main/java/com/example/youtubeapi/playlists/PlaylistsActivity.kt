package com.example.youtubeapi.playlists

import android.content.Intent
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a6mon_hw2.base.BaseActivity
import com.example.a6mon_hw2.base.BaseViewModel
import com.example.youtubeapi.ConnectivityStatus
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding
import com.example.youtubeapi.playlists.detail.DetailPlayListViewModel
import com.example.youtubeapi.playlists.detail.DetailPlaylistActivity

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, BaseViewModel>() {

    private val adapter = PlaylistsAdapter(this::onClickItem)
    private val connectivityStatus: ConnectivityStatus by lazy {
        ConnectivityStatus(this)
    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(inflater)
    }

    override fun initViews() {
        binding.rvPlaylists.adapter = adapter
        binding.rvPlaylists.layoutManager = LinearLayoutManager(this)
    }

    override fun initViewModel() {
        connectivityStatus.observe(this) {
            visibleIncludeNoInternet(it)
            if (it) {
                viewModel.playlists().observe(this) { playlist ->
                    adapter.setList(playlist.items)
                    Toast.makeText(this, playlist.etag.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun visibleIncludeNoInternet(bool: Boolean) {
        when (bool) {
            true -> {
                binding.rvPlaylists.isVisible = true
                binding.checkInet.root.isVisible = false
            }
            false -> {
                binding.rvPlaylists.isVisible = false
                binding.checkInet.root.isVisible = true
            }
        }
    }

    private fun onClickItem(id: String) {
        val intent = Intent(this, DetailPlaylistActivity::class.java)
        intent.putExtra("playlistId", id)
        startActivity(intent)
    }
}
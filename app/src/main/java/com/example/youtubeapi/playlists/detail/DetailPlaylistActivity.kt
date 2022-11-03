package com.example.youtubeapi.playlists.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a6mon_hw2.base.BaseActivity
import com.example.a6mon_hw2.base.BaseViewModel
import com.example.youtubeapi.R
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.playlists.PlaylistsViewModel

class DetailPlaylistActivity :
    BaseActivity<ActivityPlaylistDetailBinding, DetailPlayListViewModel>() {

    override val viewModel: DetailPlayListViewModel by lazy {
        ViewModelProvider(this)[DetailPlayListViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

    override fun initViews() {
        Toast.makeText(this, intent.getStringExtra("playlistId"), Toast.LENGTH_SHORT).show()
    }

    override fun initListener() {

    }

}
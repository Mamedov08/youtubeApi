package com.example.a6mon_hw2.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding

abstract class BaseActivity <VB: ViewBinding, VM:BaseViewModel>: AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    protected abstract fun inflateViewBinding(inflater: LayoutInflater) :VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=inflateViewBinding(layoutInflater)
        setContentView(binding.root)

        isConnected()
        initViews()
        initViewModel()
        initListener()
    }

    open fun initViews(){}
    open fun initListener(){}
    open fun initViewModel(){}
    open fun isConnected(){}
}
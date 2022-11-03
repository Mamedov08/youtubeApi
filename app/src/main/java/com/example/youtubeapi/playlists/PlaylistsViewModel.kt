package com.example.youtubeapi.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a6mon_hw2.base.BaseViewModel
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.`object`.Constant
import com.example.youtubeapi.model.Playlists
import com.example.youtubeapi.remote.ApiService
import com.example.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlists> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getPlaylists(Constant.part, API_KEY, Constant.channelId)
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    println(call)
                }
            })
        return data
    }

}
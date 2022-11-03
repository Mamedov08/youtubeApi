package com.example.youtubeapi.remote

import com.example.youtubeapi.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String,
    ): Call<Playlists>

    @GET("playlistItems")
    fun getPlaylistItem(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int,
    ): Call<Playlists>
}

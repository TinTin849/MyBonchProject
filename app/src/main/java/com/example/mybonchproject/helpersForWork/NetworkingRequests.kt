package com.example.mybonchproject.helpersForWork

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkingRequests {
    @GET("albums")
    fun getAlbum(): Call<List<AlbumsDataClass>>

    @GET("users")
    fun getUsers(): Call<List<UsersDataClass>>

    @POST("posts")
    fun postCreatePost(@Body enteredData: CreatePostDataClass): Call<CreatePostDataClass>
}
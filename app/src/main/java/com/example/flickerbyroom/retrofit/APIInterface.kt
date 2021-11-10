package com.example.flickerbyroom.retrofit

import com.example.flickerbyroom.details.PhotoDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("rest/?method=flickr.photos.search&api_key=e9adcd4ca6d5f0be2911653427f6a517&per_page=10&format=json&nojsoncallback=1")
    fun doGetListResources(@Query("text")text:String): Call<PhotoDetails?>?

}
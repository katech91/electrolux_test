package com.electrolux.test.api

import com.electrolux.test.data.FlickrSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IFlickrRequest {
    @GET("/services/rest")
    fun searchPhotos(
        @Query("tags") tag: String,
        @Query("api_key") key: String = "013679f9ca357216e21e12ed34cfd787",
        @Query("method") method: String = "flickr.photos.search",
        @Query("per_page") perPage: Int = 21,
        @Query("page") page: Int = 1,
        @Query("extras") exstras: String = "url_m,url_sq",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noCallback: String = "true"

    ): retrofit2.Call<FlickrSearchResponse>
}
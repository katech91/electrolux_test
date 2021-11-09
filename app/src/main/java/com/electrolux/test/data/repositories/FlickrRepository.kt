package com.electrolux.test.data.repositories

import com.electrolux.test.api.IFlickrRequest
import com.electrolux.test.data.FlickrPhoto
import com.electrolux.test.data.FlickrSearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlickrRepository(private val service: IFlickrRequest) {


    @ExperimentalCoroutinesApi
    fun getSearchStream(query: String): Flow<List<FlickrPhoto>?> = callbackFlow {

        val res =  service.searchPhotos(tag = query)

       res.enqueue( object : Callback<FlickrSearchResponse> {
            override fun onResponse(
                call: Call<FlickrSearchResponse>?,
                response: Response<FlickrSearchResponse>
            ){

                trySend(response.body()?.photos?.photo)
            }

           override fun onFailure(call: Call<FlickrSearchResponse>?, t: Throwable?){
               cancel(t?.message ?: "Unknown exception")
           }
       })

        //some magic https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/await-close.html
        awaitClose { res.cancel() }
    }
}
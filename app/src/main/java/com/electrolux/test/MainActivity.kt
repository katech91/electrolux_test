package com.electrolux.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.electrolux.test.api.IFlickrRequest
import com.electrolux.test.data.FlickrSearchResponse
import com.electrolux.test.data.Image
import com.electrolux.test.ui.GalleryAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv_image_list = findViewById<RecyclerView>(R.id.rv_image_list)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        with(rv_image_list){
            adapter = GalleryAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(divider)


//            (adapter as GalleryAdapter).submitList(MutableList<Image>(21, {Image("https://picsum.photos/300/200?random=$it")}))
        }

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.flickr.com")
            .build()

        val service: IFlickrRequest = retrofit.create(IFlickrRequest::class.java)

        val res = service.searchPhotos(tag = "Electrolux")
        res.enqueue( object : Callback<FlickrSearchResponse> {
            override fun onResponse(
                call: Call<FlickrSearchResponse>?,
                response: Response<FlickrSearchResponse>?
            ) {

                println(response?.body()!!)
                (rv_image_list.adapter as GalleryAdapter).submitList(response.body()!!.photos.photo)
            }

            override fun onFailure(call: Call<FlickrSearchResponse>?, t: Throwable?) {

            }
        })
    }
}
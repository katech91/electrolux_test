package com.electrolux.test.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.electrolux.test.api.IFlickrRequest
import com.electrolux.test.data.FlickrSearchResponse
import com.electrolux.test.ui.GalleryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel(application: Application): AndroidViewModel(application) {

    fun fetchPhotoList(flickrApi: IFlickrRequest?) {
        flickrApi?.searchPhotos("Electrolux")

    }
}
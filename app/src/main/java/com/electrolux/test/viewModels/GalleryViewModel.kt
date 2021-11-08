package com.electrolux.test.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.electrolux.test.App
import com.electrolux.test.data.Image
import com.electrolux.test.data.repositories.FlickrRepository
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var searchResult: Flow<List<Image>?>
    private val repository = FlickrRepository((application as App).flickrApi)

    fun searchPhotos(queryString: String): Flow<List<Image>?> {
        val newSearchResult: Flow<List<Image>?> = repository.getSearchStream(queryString)
        searchResult = newSearchResult
        return newSearchResult
    }
}
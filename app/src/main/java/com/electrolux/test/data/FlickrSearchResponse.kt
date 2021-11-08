package com.electrolux.test.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a photo search response from Flickr.
 */
data class FlickrSearchResponse(
    @field:SerializedName("photos")
    val photos: FlickrResults,
    @field:SerializedName("stats")
    val status: String
)

data class FlickrResults(
    @field:SerializedName("page")
    val page: Int,
    @field:SerializedName("pages")
    val pages: Int,
    @field:SerializedName("perpage")
    val perpage: Int,
    @field:SerializedName("total")
    val total: Int,
    @field:SerializedName("photo")
    val photo: List<FlickrPhoto>
)

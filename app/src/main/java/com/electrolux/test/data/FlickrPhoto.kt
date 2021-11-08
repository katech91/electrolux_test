package com.electrolux.test.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents results from Flickr.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below.
 */
data class FlickrPhoto(
    @field:SerializedName("url_m")
    override var url: String?,
    @field:SerializedName("url_sq")
    override var url_sq: String
): Image()

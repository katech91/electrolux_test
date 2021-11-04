package com.electrolux.test.data

import android.util.Log

data class Image(
    var url: String
    ) {
    init {

        println(url)
        Log.e("place", url)
    }
}
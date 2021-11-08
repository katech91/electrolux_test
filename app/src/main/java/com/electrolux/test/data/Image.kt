package com.electrolux.test.data

import android.util.Log

abstract class Image(
    ) {
    abstract var url: String
    init {

        println(url)
        Log.e("place", url)
    }
}
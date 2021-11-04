package com.electrolux.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.electrolux.test.data.Image
import com.electrolux.test.ui.GalleryAdapter

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


            (adapter as GalleryAdapter).submitList(MutableList<Image>(27, {Image("https://picsum.photos/300/200?random=$it")}))
        }

    }
}
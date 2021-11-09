package com.electrolux.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.electrolux.test.ui.GalleryAdapter
import com.electrolux.test.viewModels.GalleryViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GalleryAdapter
    private var searchJob: Job? = null
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        search("Electrolux")

    }

    private fun initViews() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val rv_image_list = findViewById<RecyclerView>(R.id.rv_image_list)
        adapter = GalleryAdapter {
            val snack = Snackbar.make(rv_image_list, "Do you want to save this photo?", Snackbar.LENGTH_LONG)
            snack.setAction("SAVE") {
                println("save $it")
            }
            snack.show()
        }
        with(rv_image_list){
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(divider)
        }
    }


    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPhotos(query).collect {
                adapter.submitList(it)
            }
        }
    }
}
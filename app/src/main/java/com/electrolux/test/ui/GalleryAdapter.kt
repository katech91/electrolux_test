package com.electrolux.test.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.electrolux.test.R
import com.electrolux.test.data.Image

/**
 * Adapter  for the RecholderyclerView in MainActivity
 */

class GalleryAdapter(val listener: (Image) -> Unit): ListAdapter<Image, RecyclerView.ViewHolder>(GalleryDiffCallback()) {

    var photos: List<Image>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_image, parent, false)
        return GalleryViewHolder(convertView, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("onBindViewHolder", "position: $position")
        (holder as GalleryViewHolder).bind(getItem(position), listener)
    }

    class GalleryViewHolder(var convertView: View, parent: ViewGroup): RecyclerView.ViewHolder(convertView) {

        fun bind(img: Image, listener: (Image) -> Unit) {
           val iv = convertView.findViewById<ImageView>(R.id.iv_photo)
                Glide.with(itemView)
                    .load(img.url ?: img.url_sq)
                    .into(iv)
            itemView.setOnClickListener {
                it.elevation = 8f
                listener.invoke(img)
            }
        }
    }

    private class GalleryDiffCallback: DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            val hold = oldItem.hashCode()
            val hnew = newItem.hashCode()
            return hnew == hold
        }

    }
}
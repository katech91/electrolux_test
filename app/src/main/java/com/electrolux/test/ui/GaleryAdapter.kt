package com.electrolux.test.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.electrolux.test.R
import com.electrolux.test.data.Image

/**
 * Adapter  for the RecyclerView in MainActivity
 */

class GalleryAdapter: ListAdapter<Image, RecyclerView.ViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_image, parent, false)
        return GalleryViewHolder(convertView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GalleryViewHolder).bind(getItem(position))
    }

    class GalleryViewHolder(var convertView: View): RecyclerView.ViewHolder(convertView) {
        fun bind(img: Image) {
            convertView.findViewById<TextView>(R.id.tv_image).text = img.url
        }
    }

    private class GalleryDiffCallback: DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
}
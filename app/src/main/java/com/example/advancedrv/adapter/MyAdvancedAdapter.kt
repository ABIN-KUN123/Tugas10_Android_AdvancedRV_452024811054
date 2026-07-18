package com.example.advancedrv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.advancedrv.model.DataItem
import com.example.advancedrv.model.MyDataModel
import com.example.advancedrv.databinding.ListItemHeaderBinding
import com.example.advancedrv.databinding.ListItemContentBinding

const val ITEM_VIEW_TYPE_HEADER = 0
const val ITEM_VIEW_TYPE_CONTENT = 1

class MyAdvancedAdapter : ListAdapter<DataItem, RecyclerView.ViewHolder>(MyDataDiffCallback()) {

    fun addHeaderAndSubmitList(list: List<MyDataModel>?) {
        val items = when (list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.ContentItem(it) }
        }
        submitList(items)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ContentItem -> ITEM_VIEW_TYPE_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_CONTENT -> ContentViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentViewHolder -> {
                val item = getItem(position) as DataItem.ContentItem
                holder.bind(item.itemData)
            }
            is HeaderViewHolder -> holder.bind()
        }
    }

    // Clean binding architecture via Factory Method
    class HeaderViewHolder private constructor(val binding: ListItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class ContentViewHolder private constructor(val binding: ListItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyDataModel) {
            binding.myData = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ContentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemContentBinding.inflate(layoutInflater, parent, false)
                return ContentViewHolder(binding)
            }
        }
    }
}

class MyDataDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}
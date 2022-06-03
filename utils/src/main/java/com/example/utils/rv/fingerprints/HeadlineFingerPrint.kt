package com.example.utils.rv.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.rv.HeadlineItem
import com.example.domain.entities.rv.Item
import com.example.utils.R
import com.example.utils.databinding.HeadlineItemBinding
import com.example.utils.rv.BaseViewHolder

class HeadlineFingerPrint : ItemFingerPrint<HeadlineItemBinding, HeadlineItem> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is HeadlineItem
    }

    override fun getLayoutId(): Int {
        return R.layout.headline_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<HeadlineItemBinding, HeadlineItem> {
        return HeadlineViewHolder(HeadlineItemBinding.inflate(layoutInflater, parent,false))

    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<HeadlineItem> {
        return diffUtil
    }

    private val diffUtil = object : DiffUtil.ItemCallback<HeadlineItem>(){
        override fun areItemsTheSame(oldItem: HeadlineItem, newItem: HeadlineItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HeadlineItem, newItem: HeadlineItem): Boolean {
            return oldItem.title == newItem.title
        }

    }
    class HeadlineViewHolder(private val binding:HeadlineItemBinding) : BaseViewHolder<HeadlineItemBinding, HeadlineItem>(binding){
        override fun onBind(item: HeadlineItem) {
            super.onBind(item)
            binding.title.text = item.title
        }
    }
}
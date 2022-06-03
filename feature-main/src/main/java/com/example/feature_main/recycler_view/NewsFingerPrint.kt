package com.example.feature_main.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.domain.entities.rv.FeedNews
import com.example.domain.entities.rv.Item
import com.example.feature_main.R
import com.example.feature_main.databinding.NewsSimpleItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class NewsFingerPrint(val onItemClicked: (item:FeedNews) -> Unit) : ItemFingerPrint<NewsSimpleItemBinding, FeedNews>{
    override fun isRelativeItem(item: Item): Boolean {
        return item is FeedNews
    }

    override fun getLayoutId(): Int {
        return R.layout.news_simple_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<NewsSimpleItemBinding, FeedNews> {
        return NewsViewHolder(NewsSimpleItemBinding.inflate(layoutInflater, parent, false), onItemClicked)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<FeedNews> {
        return diffUtil
    }
    private val diffUtil = object : DiffUtil.ItemCallback<FeedNews>(){
        override fun areItemsTheSame(oldItem: FeedNews, newItem: FeedNews): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedNews, newItem: FeedNews): Boolean {
            return oldItem.title.equals(newItem.title)
        }

    }
    class NewsViewHolder(private val binding: NewsSimpleItemBinding, private val onItemClicked: (item:FeedNews) -> Unit): BaseViewHolder<NewsSimpleItemBinding, FeedNews>(binding){
        override fun onBind(item: FeedNews) {
            super.onBind(item)
            Glide.with(binding.root.context).load(item.imageUrl).into(binding.newsIcon)
            binding.newsTitle.text = item.title
        }
        init {
            binding.root.setOnClickListener(View.OnClickListener {
                onItemClicked(item as FeedNews)
            })
        }

    }
}
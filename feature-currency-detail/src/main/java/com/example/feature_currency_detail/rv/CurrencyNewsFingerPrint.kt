package com.example.feature_currency_detail.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.domain.entities.rv.FeedNews
import com.example.domain.entities.rv.Item
import com.example.feature_currency_detail.R
import com.example.feature_currency_detail.databinding.CurrencyNewsItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class CurrencyNewsFingerPrint : ItemFingerPrint<CurrencyNewsItemBinding, FeedNews> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is FeedNews
    }

    override fun getLayoutId(): Int {
        return R.layout.currency_news_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CurrencyNewsItemBinding, FeedNews> {
        return CurrencyNewsViewHolder(CurrencyNewsItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<FeedNews> {
        return diffUtil
    }
    private val diffUtil = object : DiffUtil.ItemCallback<FeedNews>(){
        override fun areItemsTheSame(oldItem: FeedNews, newItem: FeedNews): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedNews, newItem: FeedNews): Boolean {
            return oldItem.newsUrl == newItem.newsUrl
        }
    }
    class CurrencyNewsViewHolder(val binding: CurrencyNewsItemBinding): BaseViewHolder<CurrencyNewsItemBinding, FeedNews>(binding){
        override fun onBind(item: FeedNews) {
            super.onBind(item)
            Glide.with(binding.root.context).load(item.imageUrl).into(binding.publisherImage)
            binding.title.text = item.title
        }
    }
}
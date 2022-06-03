package com.example.feature_main.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.Feature
import com.example.domain.entities.rv.FeatureItem
import com.example.domain.entities.rv.Item
import com.example.feature_main.R
import com.example.feature_main.databinding.FeaturesItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class PageHeaderFingerPrint(private val onItemClicked:(feature: Feature) -> Unit) : ItemFingerPrint <FeaturesItemBinding, FeatureItem>{
    override fun isRelativeItem(item: Item): Boolean {
        return item is FeatureItem
    }

    override fun getLayoutId(): Int {
        return R.layout.features_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<FeaturesItemBinding, FeatureItem> {
        return FeaturesViewHolder(FeaturesItemBinding.inflate(layoutInflater, parent, false), onItemClicked)

    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<FeatureItem> {
        return diffUtil
    }

    private val diffUtil = object : DiffUtil.ItemCallback<FeatureItem>(){
        override fun areItemsTheSame(oldItem: FeatureItem, newItem: FeatureItem): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FeatureItem, newItem: FeatureItem): Boolean {
            return oldItem == newItem
        }
    }
    class FeaturesViewHolder(private val binding: FeaturesItemBinding, private val onItemClicked:(feature: Feature) -> Unit): BaseViewHolder<FeaturesItemBinding, FeatureItem>(binding){
        override fun onBind(item: FeatureItem) {
            super.onBind(item)
            binding.compareCard.setOnClickListener(View.OnClickListener {
                onItemClicked(Feature.Compare)
            })
            binding.convertCard.setOnClickListener(View.OnClickListener {
                onItemClicked(Feature.Convert)
            })
            binding.watchListCard.setOnClickListener(View.OnClickListener {
                onItemClicked(Feature.WatchList)
            })
        }
    }
}
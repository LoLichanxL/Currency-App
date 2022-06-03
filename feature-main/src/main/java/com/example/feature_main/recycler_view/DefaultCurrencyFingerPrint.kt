package com.example.feature_main.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.Item
import com.example.feature_main.R
import com.example.feature_main.databinding.MainCurrencySimpleItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class DefaultCurrencyFingerPrint : ItemFingerPrint<MainCurrencySimpleItemBinding, CryptoCurrency> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is CryptoCurrency
    }

    override fun getLayoutId(): Int {
        return R.layout.main_currency_simple_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<MainCurrencySimpleItemBinding, CryptoCurrency> {
        return DefaultCurrencyViewHolder(MainCurrencySimpleItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<CryptoCurrency> {
        return itemDiffutil
    }
    private val itemDiffutil = object:DiffUtil.ItemCallback<CryptoCurrency>(){
        override fun areItemsTheSame(oldItem: CryptoCurrency, newItem: CryptoCurrency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoCurrency, newItem: CryptoCurrency): Boolean {
            return oldItem.name == newItem.name && oldItem.priceUsd == newItem.priceUsd
        }
    }
    class DefaultCurrencyViewHolder(val binding: MainCurrencySimpleItemBinding) : BaseViewHolder<MainCurrencySimpleItemBinding, CryptoCurrency>(binding) {
        @SuppressLint("ResourceAsColor")
        override fun onBind(item: CryptoCurrency) {
            super.onBind(item)
            val roundedPrice = Math.round(item.priceUsd * 100.0) / 100.0
            val roundedPercentChange = Math.round(item.percentChange_24h * 100.0) / 100.0
            Glide.with(binding.root).load(item.logoUrl).into(binding.coinLogo)
            binding.coinName.text = item.name
            binding.currencyPrice.text = roundedPrice.toString() + "$"
            binding.coinPercentChange.text = roundedPercentChange.toString() + "%"
            if(item.percentChange_24h > 0) {
                binding.coinPercentChange.setTextColor(com.example.utils.R.color.green)
                binding.arrowUp.visibility = View.VISIBLE
                binding.arrowDown.visibility = View.GONE
            }else{
                binding.coinPercentChange.setTextColor(com.example.utils.R.color.pink)
                binding.arrowDown.visibility = View.VISIBLE
                binding.arrowUp.visibility = View.GONE
            }
        }
    }
}
package com.example.feature_main.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.Item
import com.example.feature_main.R
import com.example.feature_main.databinding.CurrencySimpleItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class CurrenciesFingerPrint(private val onItemClicked: (item: CryptoCurrency) -> Unit) :
    ItemFingerPrint<CurrencySimpleItemBinding, CryptoCurrency> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is CryptoCurrency
    }

    override fun getLayoutId(): Int {
        return R.layout.currency_simple_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CurrencySimpleItemBinding, CryptoCurrency> {
        return CurrencyViewHolder(
            CurrencySimpleItemBinding.inflate(layoutInflater, parent, false),
            onItemClicked
        )
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<CryptoCurrency> {
        return diffUtil
    }

    private val diffUtil = object : DiffUtil.ItemCallback<CryptoCurrency>() {
        override fun areItemsTheSame(oldItem: CryptoCurrency, newItem: CryptoCurrency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoCurrency, newItem: CryptoCurrency): Boolean {
            return oldItem.id.equals(newItem.id)
        }

    }

    class CurrencyViewHolder(
        private val binding: CurrencySimpleItemBinding,
        private val onItemClicked: (item: CryptoCurrency) -> Unit,
    ) : BaseViewHolder<CurrencySimpleItemBinding, CryptoCurrency>(binding = binding) {
        override fun onBind(item: CryptoCurrency) {
            super.onBind(item)
            val roundedPrice = Math.round(item.priceUsd * 100.0) / 100.0
            val roundedPercentChange = Math.round(item.percentChange_24h * 100.0) / 100.0
            Glide.with(binding.root.context).load(item.logoUrl).into(binding.coinLogo)
            binding.coinName.text = item.name
            binding.coinPrice.text = "$" + roundedPrice.toString()
            binding.coinPercentChange.text = roundedPercentChange.toString() + "%"
            if(roundedPercentChange > 0){
                binding.coinPercentChange.setTextColor(ContextCompat.getColor(binding.root.context, com.example.utils.R.color.green))
            }else{
                binding.coinPercentChange.setTextColor(ContextCompat.getColor(binding.root.context, com.example.utils.R.color.pink))
            }
        }

        init {
            binding.root.setOnClickListener(View.OnClickListener {
                onItemClicked(item as CryptoCurrency)
            })
        }
    }
}
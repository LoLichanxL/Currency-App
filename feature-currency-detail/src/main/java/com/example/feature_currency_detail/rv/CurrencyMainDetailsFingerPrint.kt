package com.example.feature_currency_detail.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.rv.currency_details.CurrencyDetails
import com.example.domain.entities.rv.Item
import com.example.feature_currency_detail.R
import com.example.feature_currency_detail.databinding.CurrencyMainDetailsBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class CurrencyMainDetailsFingerPrint : ItemFingerPrint<CurrencyMainDetailsBinding, CurrencyDetails> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is CurrencyDetails
    }

    override fun getLayoutId(): Int {
        return R.layout.currency_main_details
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CurrencyMainDetailsBinding, CurrencyDetails> {
        return MainDetailsViewHolder(CurrencyMainDetailsBinding.inflate(layoutInflater, parent, false))
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<CurrencyDetails> {
        return diffUtil
    }
    private val diffUtil = object : DiffUtil.ItemCallback<CurrencyDetails>() {
        override fun areItemsTheSame(oldItem: CurrencyDetails, newItem: CurrencyDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CurrencyDetails,
            newItem: CurrencyDetails
        ): Boolean {
            return oldItem.price == newItem.price && newItem.name == oldItem.name
        }


    }
    class MainDetailsViewHolder(val binding: CurrencyMainDetailsBinding): BaseViewHolder<CurrencyMainDetailsBinding, CurrencyDetails>(binding){
        override fun onBind(item: CurrencyDetails) {
            super.onBind(item)
            val splitPrice = item.price.toString().split(".")
            binding.currencyName.text = item.name
            binding.currencyPrice.text =  splitPrice[0] + "." + splitPrice[1].subSequence(0, 3) + "$"
        }
    }
}
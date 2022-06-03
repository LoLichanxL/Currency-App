package com.example.feature_main.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.CurrenciesRecyclerViewItem
import com.example.domain.entities.rv.Item
import com.example.feature_main.R
import com.example.feature_main.databinding.CurrenciesRecyclerViewBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.adapter.BaseAdapter
import com.example.utils.rv.decoration.HorizontalItemDecoration
import com.example.utils.rv.fingerprints.ItemFingerPrint

class CurrenciesRecyclerViewFingerPrint(private val onItemClicked:(item: CryptoCurrency) -> Unit): ItemFingerPrint<CurrenciesRecyclerViewBinding, CurrenciesRecyclerViewItem>{
    override fun isRelativeItem(item: Item): Boolean {
        return item is CurrenciesRecyclerViewItem
    }

    override fun getLayoutId(): Int {
        return R.layout.currencies_recycler_view
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CurrenciesRecyclerViewBinding, CurrenciesRecyclerViewItem> {
        return CurrenciesRVViewHolder(onItemClicked, CurrenciesRecyclerViewBinding.inflate(layoutInflater, parent, false))
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<CurrenciesRecyclerViewItem> {
        return diffUtil
    }
    private val diffUtil = object : DiffUtil.ItemCallback<CurrenciesRecyclerViewItem>(){
        override fun areItemsTheSame(
            oldItem: CurrenciesRecyclerViewItem,
            newItem: CurrenciesRecyclerViewItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CurrenciesRecyclerViewItem,
            newItem: CurrenciesRecyclerViewItem
        ): Boolean {
            return oldItem.data == newItem.data
        }
    }
    class CurrenciesRVViewHolder(private val onItemClicked:(item: CryptoCurrency) -> Unit, private val binding: CurrenciesRecyclerViewBinding):BaseViewHolder<CurrenciesRecyclerViewBinding, CurrenciesRecyclerViewItem>(binding){
        override fun onBind(item: CurrenciesRecyclerViewItem) {
            super.onBind(item)
            binding.recyclerView.adapter = BaseAdapter(listOf(CurrenciesFingerPrint(onItemClicked)))
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            (binding.recyclerView.adapter as BaseAdapter).submitList(item.data)
        }
        init {
            binding.recyclerView.addItemDecoration(HorizontalItemDecoration(R.layout.currency_simple_item, 10))
        }
    }
}
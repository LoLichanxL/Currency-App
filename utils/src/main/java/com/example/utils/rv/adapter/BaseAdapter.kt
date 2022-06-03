package com.example.utils.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.domain.entities.rv.Item
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.FingerPrintDiffUtil
import com.example.utils.rv.fingerprints.ItemFingerPrint

class BaseAdapter(private val fingerPrints:List<ItemFingerPrint<*, *>>) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    FingerPrintDiffUtil(fingerPrints)
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return fingerPrints.find { it.getLayoutId() == viewType }?.getItemViewHolder(inflater, parent)
            ?.let { it as BaseViewHolder<ViewBinding, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return fingerPrints.find{it.isRelativeItem(item)}?.getLayoutId()?:throw IllegalStateException("ViewType not found $item")
    }
}
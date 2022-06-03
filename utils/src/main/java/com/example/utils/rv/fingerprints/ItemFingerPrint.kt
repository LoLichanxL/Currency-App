package com.example.utils.rv.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.domain.entities.rv.Item
import com.example.utils.rv.BaseViewHolder

interface ItemFingerPrint <V:ViewBinding, I: Item> {
    fun isRelativeItem(item: Item):Boolean

    @LayoutRes
    fun getLayoutId():Int

    fun getItemViewHolder(layoutInflater:LayoutInflater, parent: ViewGroup) : BaseViewHolder<V, I>

    fun getDiffUtil():DiffUtil.ItemCallback<I>
}
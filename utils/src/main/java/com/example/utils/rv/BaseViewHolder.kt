package com.example.utils.rv

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.domain.entities.rv.Item

abstract class BaseViewHolder <V:ViewBinding, I: Item>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var item: Item

    open fun onBind(item: I){
        this.item = item
    }

}
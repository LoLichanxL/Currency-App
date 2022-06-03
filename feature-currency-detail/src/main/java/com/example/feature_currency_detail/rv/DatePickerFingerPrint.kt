package com.example.feature_currency_detail.rv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.rv.Item
import com.example.domain.entities.rv.currency_details.DatePickerItem
import com.example.feature_currency_detail.R
import com.example.feature_currency_detail.databinding.DatePickerItemBinding
import com.example.utils.rv.BaseViewHolder
import com.example.utils.rv.fingerprints.ItemFingerPrint

class DatePickerFingerPrint(val onButtonChecked: (id: Int) -> Unit) :
    ItemFingerPrint<DatePickerItemBinding, DatePickerItem> {
    override fun isRelativeItem(item: Item): Boolean {
        return item is DatePickerItem
    }

    override fun getLayoutId(): Int {
        return R.layout.date_picker_item
    }

    override fun getItemViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<DatePickerItemBinding, DatePickerItem> {
        return DatePickerViewHolder(
            DatePickerItemBinding.inflate(layoutInflater, parent, false),
            onButtonChecked
        )
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<DatePickerItem> {
        return diffutil
    }

    private val diffutil = object : DiffUtil.ItemCallback<DatePickerItem>() {
        override fun areItemsTheSame(oldItem: DatePickerItem, newItem: DatePickerItem): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DatePickerItem, newItem: DatePickerItem): Boolean {
            return oldItem == newItem
        }
    }

    class DatePickerViewHolder(
        val binding: DatePickerItemBinding,
        val onButtonChecked: (id: Int) -> Unit
    ) : BaseViewHolder<DatePickerItemBinding, DatePickerItem>(binding) {
        override fun onBind(item: DatePickerItem) {
            super.onBind(item)
            binding.toggleGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

                onButtonChecked(checkedId)
            }
        }
    }
}
package com.example.utils.rv.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(val viewType:Int, val divider:Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildViewHolder(view).itemViewType != viewType) return

        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return

        with(outRect) {
            left = divider
            right = divider
        }
    }
}
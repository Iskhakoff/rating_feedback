package ru.zaoblako.feedbackratings.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.zaoblako.feedbackratings.adapter.cells.Cells

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind (cell : Cells)
}
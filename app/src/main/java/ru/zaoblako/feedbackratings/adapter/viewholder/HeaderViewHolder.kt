package ru.zaoblako.feedbackratings.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import ru.zaoblako.feedbackratings.R
import ru.zaoblako.feedbackratings.adapter.cells.Cells

class HeaderViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val listener : OnChangeRatingHeaderListener) :
    BaseViewHolder(inflater.inflate(R.layout.item_header_rating, parent, false)) {

    private val mTitle : TextView = itemView.findViewById(R.id.title_header_category)
    private val mRatingBar : RatingBar = itemView.findViewById(R.id.rating_bar_header)

    override fun bind(cell: Cells) {
        cell as Cells.HeaderRatingCell
        mTitle.text = cell.title
        mRatingBar.rating = cell.rating

        mRatingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                listener.onChangeRatingHeader(rating)
            }
        }
    }

    interface OnChangeRatingHeaderListener {
        fun onChangeRatingHeader(rating : Float)
    }
}
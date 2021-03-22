package ru.zaoblako.feedbackratings.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import ru.zaoblako.feedbackratings.R
import ru.zaoblako.feedbackratings.adapter.cells.Cells

class RatingViewHolder(inflater : LayoutInflater, parent : ViewGroup, private val listener : OnRatingChangeListener) :
    BaseViewHolder(inflater.inflate(R.layout.item_rating, parent, false)) {

    private val mTitle : TextView = itemView.findViewById(R.id.title_category)
    private val mRatingBar : RatingBar = itemView.findViewById(R.id.rating_bar)

    override fun bind(cell: Cells) {
        cell as Cells.RatingCell
        mTitle.text = cell.title
        mRatingBar.rating = cell.rating

        mRatingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                listener.onRatingChange(layoutPosition, rating)
            }
        }
    }

    interface OnRatingChangeListener {
        fun onRatingChange(position : Int, rating : Float)
    }
}
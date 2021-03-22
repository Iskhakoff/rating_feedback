package ru.zaoblako.feedbackratings.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RatingBar
import android.widget.TextView
import ru.zaoblako.feedbackratings.R
import ru.zaoblako.feedbackratings.adapter.cells.Cells

class RatingFoodViewHolder(inflater : LayoutInflater, parent : ViewGroup, private val listener : OnChangeFoodRatingListener) :
    BaseViewHolder(inflater.inflate(R.layout.item_food_rating, parent, false)){

    private val mTitle : TextView = itemView.findViewById(R.id.food_title_category)
    private val mRatingBar : RatingBar = itemView.findViewById(R.id.food_rating_bar)
    private val mCheckbox : CheckBox = itemView.findViewById(R.id.food_indicator)

    override fun bind(cell: Cells) {
        cell as Cells.RatingFoodCell
        mTitle.text = cell.title
        mRatingBar.rating = cell.rating
        mRatingBar.isEnabled = !cell.isNoFood
        mCheckbox.isChecked = cell.isNoFood

        mCheckbox.setOnCheckedChangeListener { _, isChecked ->
            listener.onSelectedNoFoodOption(layoutPosition, isChecked)
        }

        mRatingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                listener.onChangeFoodRating(layoutPosition, rating)
            }
        }
    }

    interface OnChangeFoodRatingListener {
        fun onChangeFoodRating(position : Int, rating : Float)
        fun onSelectedNoFoodOption(position: Int, isNoFood : Boolean)
    }
}
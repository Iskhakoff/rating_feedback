package ru.zaoblako.feedbackratings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.zaoblako.feedbackratings.adapter.cells.Cells
import ru.zaoblako.feedbackratings.adapter.viewholder.BaseViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.HeaderViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.RatingFoodViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.RatingViewHolder

class Adapter(private val inflater : LayoutInflater,
              private val ratingChangeListener : RatingViewHolder.OnRatingChangeListener,
              private val headerRatingChangeListener : HeaderViewHolder.OnChangeRatingHeaderListener,
              private val ratingFoodListener : RatingFoodViewHolder.OnChangeFoodRatingListener) : ListAdapter<Cells, BaseViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Cells>(){
            override fun areItemsTheSame(oldItem: Cells, newItem: Cells): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Cells, newItem: Cells): Boolean {
                return oldItem == newItem
            }

        }
    }

    private enum class ViewType {
        HEADER,
        RATING,
        FOOD_RATING
    }

    private val Cells.viewType : ViewType
        get() = when (this){
            is Cells.HeaderRatingCell -> ViewType.HEADER
            is Cells.RatingCell -> ViewType.RATING
            is Cells.RatingFoodCell -> ViewType.FOOD_RATING
        }

    private val viewTypeValues = ViewType.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewTypeValues[viewType]) {
            ViewType.HEADER -> HeaderViewHolder(inflater, parent, headerRatingChangeListener)
            ViewType.RATING -> RatingViewHolder(inflater, parent, ratingChangeListener)
            ViewType.FOOD_RATING -> RatingFoodViewHolder(inflater, parent, ratingFoodListener)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = getItem(position).viewType.ordinal
}
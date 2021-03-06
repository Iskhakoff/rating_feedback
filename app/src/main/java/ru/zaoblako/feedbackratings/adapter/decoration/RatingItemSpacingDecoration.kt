package ru.zaoblako.feedbackratings.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RatingItemSpacingDecoration(private val horizontalSpacing : Int,
                                  private val verticalSpacing : Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
            if (parent.getChildAdapterPosition(view) == 0) {
                left = 0
                right = 0
                bottom = verticalSpacing
            } else {
                left = horizontalSpacing
                right = horizontalSpacing
                bottom = verticalSpacing
            }

        }
    }
}
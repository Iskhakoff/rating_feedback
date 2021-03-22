package ru.zaoblako.feedbackratings.adapter.cells

sealed class Cells {
    data class HeaderRatingCell (
        val title : String,
        val rating : Float,
        val category: Category
    ) : Cells()

    data class RatingCell (
        val title : String,
        val rating : Float,
        val category: Category
    ) : Cells()

    data class RatingFoodCell(
        val title : String,
        val rating : Float,
        val isNoFood : Boolean = false,
        val category: Category,
    ) : Cells()
}

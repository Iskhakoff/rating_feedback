package ru.zaoblako.feedbackratings.adapter.cells

sealed class Cells(
    open val title : String,
    open val rating : Float,
    open val category: Category
) {
    data class HeaderRatingCell (
        override val title : String,
        override val rating : Float,
        override val category: Category
    ) : Cells(title, rating, category) {
        fun setRating(rating: Float) : HeaderRatingCell {
            return HeaderRatingCell(this.title, rating, this.category)
        }
    }

    data class RatingCell (
        override val title : String,
        override val rating : Float,
        override val category: Category
    ) : Cells(title, rating, category) {
        fun setRating(rating : Float) : RatingCell {
            return RatingCell(this.title, rating, this.category)
        }
    }

    data class RatingFoodCell(
        override val title : String,
        override var rating : Float,
        override val category: Category,
        val isNoFood : Boolean = false
    ) : Cells(title, rating, category) {
        fun setRating(rating : Float) : RatingFoodCell {
            return RatingFoodCell(this.title, rating, this.category, this.isNoFood)
        }
        fun setIsNoFood(isNoFood : Boolean) : RatingFoodCell {
            return RatingFoodCell(this.title, this.rating, this.category, isNoFood)
        }
    }
}

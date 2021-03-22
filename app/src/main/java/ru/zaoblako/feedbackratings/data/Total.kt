package ru.zaoblako.feedbackratings.data

data class Total(
    val flight : Int = 0,
    val crew : Int = 0,
    val aircraft : Int = 0,
    val seat : Int = 0,
    val people : Int = 0,
    val food : Int? = null,
    val feedback : String = ""
) {
    fun setFlight(rating : Int) : Total {
        return Total(rating, this.crew, this.aircraft, this.seat, this.people, this.food, this.feedback)
    }
    fun setCrew(rating: Int) : Total {
        return Total(this.flight, rating, this.aircraft, this.seat, this.people, this.food, this.feedback)
    }
    fun setAircraft(rating : Int) : Total {
        return Total(this.flight, this.crew, rating, this.seat, this.people, this.food, this.feedback)
    }
    fun setSeat(rating : Int) : Total {
        return Total(this.flight, this.crew, this.aircraft, rating, this.people, this.food, this.feedback)
    }
    fun setPeople(rating : Int) : Total {
        return Total(this.flight, this.crew, this.aircraft, this.seat, rating, this.food, this.feedback)
    }
    fun setFood(rating: Int?) : Total {
        return Total(this.flight, this.crew, this.aircraft, this.seat, this.people, rating, this.feedback)
    }
    fun setFeedback(text : String) : Total {
        return Total(this.flight, this.crew, this.aircraft, this.seat, this.people, this.food, text)
    }
}

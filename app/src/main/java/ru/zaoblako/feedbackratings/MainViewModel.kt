package ru.zaoblako.feedbackratings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.zaoblako.feedbackratings.adapter.cells.Category
import ru.zaoblako.feedbackratings.adapter.cells.Cells
import ru.zaoblako.feedbackratings.data.Total
import ru.zaoblako.feedbackratings.data.ViewState

class MainViewModel : ViewModel() {

    private var cells : MutableLiveData<List<Cells>>? = null
    private var state : MutableLiveData<ViewState> = MutableLiveData()

    fun getCells() : LiveData<List<Cells>> {
        if (cells == null) {
            cells = MutableLiveData()
            initCells()
        }
        return cells!!
    }

    fun getState() : LiveData<ViewState> {
        return state
    }

    fun initCells() {
        val list = mutableListOf<Cells>()
        list.add(Cells.HeaderRatingCell("How do you rate the overall flight experience", 0F, Category.FLIGHT))
        list.add(Cells.RatingCell("How crowded was the flight?", 0F, Category.PEOPLE))
        list.add(Cells.RatingCell("How do you rate the aircraft?", 0F, Category.AIRCRAFT))
        list.add(Cells.RatingCell("How do you rate the seats?", 0F, Category.SEAT))
        list.add(Cells.RatingCell("How do you rate the crew?", 0F, Category.CREW))
        list.add(Cells.RatingFoodCell("How do you rate the food?", 0F, Category.FOOD))
        cells!!.value = list
    }

    fun updateHeaderRating(rating : Float) {
        val list = ArrayList(cells!!.value)
        val item = list[0] as Cells.HeaderRatingCell
        list.removeAt(0)
        list.add(0, item.setRating(rating))
        cells!!.value = list
    }

    fun updateRating(position : Int, rating : Float) {
        val list = ArrayList(cells!!.value)
        val item = list[position] as Cells.RatingCell
        list.removeAt(position)
        list.add(position, item.setRating(rating))
        cells!!.value = list
    }

    fun updateFoodRating(position: Int, rating: Float) {
        val list = ArrayList(cells!!.value)
        val item = list[position] as Cells.RatingFoodCell
        list.removeAt(position)
        list.add(position, item.setRating(rating))
        cells!!.value = list
    }

    fun selectedNoFood(position: Int, isNoFood : Boolean) {
        val list = ArrayList(cells!!.value)
        val item = list[position] as Cells.RatingFoodCell
        list.removeAt(position)
        list.add(position, item.setIsNoFood(isNoFood))
        cells!!.value = list
    }

    fun submitTotalFeedback(text : String) {
        state.value = ViewState.Loading
        viewModelScope.launch {
            state.value = ViewState.SuccessSubmit(collectTotalFeedback(text))
            state.value = ViewState.Default
        }

    }

    private suspend fun collectTotalFeedback(text : String) : Total {
        val list = ArrayList(cells!!.value)
        var total = Total()
        withContext(Dispatchers.Default){
            delay(3000)
            list.forEach { cell ->
                when (cell.category) {
                    Category.FOOD -> {
                        cell as Cells.RatingFoodCell
                        total = if (cell.isNoFood) {
                            total.setFood(null)
                        } else {
                            total.setFood(cell.rating.toInt())
                        }
                    }
                    Category.CREW -> {
                        total = total.setCrew(cell.rating.toInt())
                    }
                    Category.SEAT -> {
                        total = total.setSeat(cell.rating.toInt())
                    }
                    Category.AIRCRAFT -> {
                        total = total.setAircraft(cell.rating.toInt())
                    }
                    Category.PEOPLE -> {
                        total = total.setPeople(cell.rating.toInt())
                    }
                    Category.FLIGHT -> {
                        total = total.setFlight(cell.rating.toInt())
                    }
                }
            }
            total = total.setFeedback(text)
        }
        return total
    }
}
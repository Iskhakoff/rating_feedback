package ru.zaoblako.feedbackratings.data

import ru.zaoblako.feedbackratings.adapter.cells.Cells

sealed class ViewState {
    object Default : ViewState()
    object Loading : ViewState()
    data class SuccessSubmit(val totalFeedback : Total) : ViewState()
}

package com.shobu.walk_in_clinic.ui.appointments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.data.repository.ReviewRepository
import com.shobu.walk_in_clinic.domain.models.Review
import com.shobu.walk_in_clinic.domain.use_cases.appointments.GetMyAppointmentsUseCase
import com.shobu.walk_in_clinic.ui.appointments.components.MyAppointmentEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MyAppointmentViewModel
@Inject constructor(
    private val getMyAppointmentsUseCase: GetMyAppointmentsUseCase,
    private val reviewRepository: ReviewRepository,
) : ViewModel() {

    var state by mutableStateOf(MyAppointmentState())
        private set

    var reviewDialogState by mutableStateOf(OpenReviewDialogState())

    init {
        viewModelScope.launch {
            getMyAppointmentsUseCase.invoke()
            getMyAppointmentsUseCase.myAppointments.collectLatest { list ->
                state = state.copy(
                    myAppointments = list
                )
            }
        }
    }

    fun onEvent(event: MyAppointmentEvents) {
        when (event) {
            is MyAppointmentEvents.OpenReviewDialog -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val review = reviewRepository.getReview(event.appointmentId)
                        reviewDialogState = reviewDialogState.copy(
                            openDialog = true,
                            review = review,
                            appointmentId = event.appointmentId
                        )
                    }
                }
            }

            MyAppointmentEvents.ClosReviewDialog -> {
                reviewDialogState = reviewDialogState.copy(
                    openDialog = false
                )
            }

            is MyAppointmentEvents.SaveReview -> {
                val newReview = Review(
                    id = reviewDialogState.appointmentId,
                    appointmentId = reviewDialogState.appointmentId!!,
                    review = event.review,
                    rating = event.rating
                )
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        reviewRepository.saveReview(review = newReview)
                    }
                }
            }
        }
    }
}
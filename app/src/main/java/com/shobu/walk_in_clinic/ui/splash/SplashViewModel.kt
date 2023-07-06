package com.shobu.walk_in_clinic.ui.splash

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_clinic.domain.use_cases.appointments.UpdateAppointmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SplashViewModel
@Inject constructor(
    private val updateAppointmentsUseCase: UpdateAppointmentsUseCase
) : ViewModel() {

    fun checkAppointments() {
        updateAppointmentsUseCase()
    }


}
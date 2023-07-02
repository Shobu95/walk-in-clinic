package com.shobu.walk_in_clinic.ui.search_clinic

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.domain.constants.Constants
import com.shobu.walk_in_clinic.ui.book_appointment.BookAppointmentActivity
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.search_clinic.components.SearchClinicBody
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchClinicActivity : ComponentActivity() {


    private val viewModel by viewModels<SearchClinicViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this)
        Places.initialize(this.applicationContext, "AIzaSyBcEZxL5X74ZHkj1G46ksjW8wbqTuAO_iY")
        viewModel.placesClient = Places.createClient(this)
        viewModel.geoCoder = Geocoder(this)

        setContent {
            WalkInClinicTheme {
                // A surface container using the 'background' color from the theme
                SearchClinicScreen(viewModel)
            }
        }
    }


    companion object {
        fun navigateToBookAppointment(context: Context, myLocation: MyLocation) {
            val intent = Intent(
                context,
                BookAppointmentActivity::class.java
            )
            intent.putExtra(Constants.CLINIC_NAME, myLocation.name)
            context.startActivity(intent)
        }
    }
}


@Preview
@Composable
fun SearchClinicScreenPrev() {
//    SearchClinicScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchClinicScreen(
    viewModel: SearchClinicViewModel
) {

    val context = LocalContext.current
    Scaffold(topBar = {
        MyTopAppBar(title = R.string.title_search_clinics, showBack = true) {
            (context as Activity).finish()
        }
    }) { innerPadding ->
        SearchClinicBody(
            innerPadding = innerPadding,
            viewModel = viewModel
        )
    }
}

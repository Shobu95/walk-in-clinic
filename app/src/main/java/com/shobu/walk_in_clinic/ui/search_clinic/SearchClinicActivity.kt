package com.shobu.walk_in_clinic.ui.search_clinic

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.components.SearchView

class SearchClinicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkInClinicTheme {
                // A surface container using the 'background' color from the theme
                SearchClinicScreen()
            }
        }
    }
}


@Preview
@Composable
fun SearchClinicScreenPrev() {
    SearchClinicScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchClinicScreen(
) {

    val context = LocalContext.current

    Scaffold(topBar = {
        MyTopAppBar(title = R.string.title_search_clinics, showBack = true) {
            (context as Activity).finish()
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {


            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                cameraPositionState = CameraPositionState(
                    position = CameraPosition.fromLatLngZoom(LatLng(21.34565, 23.45678), 1f)
                ),
            ) {

            }

            Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                SearchView(
                    hint = R.string.search,
                )
            }
        }
    }


}

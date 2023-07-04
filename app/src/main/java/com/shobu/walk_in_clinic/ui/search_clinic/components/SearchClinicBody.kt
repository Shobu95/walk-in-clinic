package com.shobu.walk_in_clinic.ui.search_clinic.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.domain.utils.bitmapDescriptorFromVector
import com.shobu.walk_in_clinic.ui.components.SearchView
import com.shobu.walk_in_clinic.ui.search_clinic.SearchClinicActivity
import com.shobu.walk_in_clinic.ui.search_clinic.SearchClinicViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun SearchClinicBodyPrev() {
//    SearchClinicBody()
}

@Composable
fun SearchClinicBody(
    innerPadding: PaddingValues,
    viewModel: SearchClinicViewModel
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
                position = CameraPosition.fromLatLngZoom(viewModel.currentLatLong, 15f)
            ),
        ) {
            if (viewModel.selectedLocation.name.isNotEmpty()) {
                Marker(
                    state = MarkerState(
                        position = viewModel.currentLatLong
                    ),
                    title = viewModel.selectedLocation.name,
                    icon = bitmapDescriptorFromVector(context, R.drawable.location_marker),
                    onClick = {
                        scope.launch {
                            delay(1000)
                            SearchClinicActivity.navigateToBookAppointment(
                                context,
                                viewModel.selectedLocation
                            )
                        }
                        false
                    }
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {

            var searchValue = remember { mutableStateOf("") }

            SearchView(
                searchValue = searchValue,
                hint = R.string.search,
            ) { query ->
                viewModel.searchPlaces(query)
            }

            AnimatedVisibility(
                viewModel.locationAutofill.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Transparent)
                    .padding(horizontal = 20.dp, vertical = 5.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .background(Color.White)

                ) {
                    items(viewModel.locationAutofill) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    searchValue.value = it.address
                                    viewModel.locationAutofill.clear()
                                    viewModel.getCoordinates(it)

                                }
                        ) {
                            Text(it.address)
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
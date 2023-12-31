package com.app.walk_in_clinic.ui.book_appointment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.walk_in_clinic.domain.use_cases.TimeSlotsManager

@Preview
@Composable
fun TimeSlotSelectorPrev() {
    TimeSlotSelector(TimeSlotsManager.getTimeSlots("")) {}
}

@Composable
fun TimeSlotSelector(
    timeSlots: List<String>,
    onTimeSelected: (String) -> Unit
) {

    val selectedSlot = remember {
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(
            items = timeSlots,
            itemContent = { index, item ->
                Card(
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier
                        .background(Color.White)
                        .padding(5.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier
                            .background(
                                if (selectedSlot.value == index)
                                    MaterialTheme.colorScheme.primary
                                else
                                    Color.LightGray
                            )
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                            .clickable {
                                selectedSlot.value = index
                                onTimeSelected(item)
                            }
                    )
                }
            })
    }
}
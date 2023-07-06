package com.shobu.walk_in_clinic.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.walk_in_appointment.ui.theme.RatingOrange
import com.mahmoudalim.compose_rating_bar.RatingBarView
import com.shobu.walk_in_clinic.R

@Preview
@Composable
fun RateAndReviewDialogPrev() {
    RateAndReviewDialog() {}
}

@Composable
fun RateAndReviewDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
        ) {
            Column(Modifier.padding(14.dp)) {

                Text(
                    text = stringResource(id = R.string.review_your_application),
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontSize = 18.sp,
                    fontWeight = Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                val rateState = remember {
                    mutableStateOf(0)
                }

                RatingBarView(
                    rating = rateState,
                    isRatingEditable = true,
                    isViewAnimated = true,
                    ratedStarsColor = RatingOrange
                )

                Spacer(modifier = Modifier.height(20.dp))
                var reasonValue = remember { mutableStateOf("") }
                BorderedTextArea(
                    hint = R.string.write_your_review,
                    textFieldValue = reasonValue,
                ) { reason ->
                    reasonValue.value = reason
                }


                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextButton(onClick = { onDismiss() })
                    { Text(text = "CANCEL") }

                    TextButton(onClick = { onDismiss() })
                    { Text(text = "SUBMIT") }

                }


            }
        }
    }
}

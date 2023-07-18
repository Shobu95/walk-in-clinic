package com.app.walk_in_clinic.ui.components

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.app.walk_in_clinic.R
import com.example.walk_in_appointment.ui.theme.RatingOrange
import com.mahmoudalim.compose_rating_bar.RatingBarView

@Preview
@Composable
fun RateAndReviewDialogPrev() {
    RateAndReviewDialog(onSubmit = { rating, reason -> }) {}
}

@Composable
fun RateAndReviewDialog(
    rating: Int = 0,
    review: String = "",
    onSubmit: (rating: Int, review: String) -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
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
                    mutableStateOf(rating)
                }

                RatingBarView(
                    rating = rateState,
                    isRatingEditable = true,
                    isViewAnimated = true,
                    ratedStarsColor = RatingOrange
                )

                Spacer(modifier = Modifier.height(20.dp))
                var reasonValue = remember { mutableStateOf(review) }
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

                    TextButton(onClick = { onCancel() })
                    { Text(text = "CANCEL") }

                    TextButton(onClick = { onSubmit(rateState.value, reasonValue.value) })
                    { Text(text = "SUBMIT") }

                }


            }
        }
    }
}

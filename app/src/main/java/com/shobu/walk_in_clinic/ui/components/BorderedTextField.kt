package com.shobu.walk_in_clinic.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun BorderedTextFieldPrev() {
    Box(
        modifier = Modifier
            .padding(10.dp)
    ) {
//        BorderedTextField()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorderedTextField(
    textFieldValue: MutableState<String>,
    @StringRes hint: Int,
    onTextChange: (value: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = textFieldValue.value,
            onValueChange = {
                if (!it.contains("\n")) {
                    textFieldValue.value = it
                    onTextChange(it)
                }
            },
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(
                    Font(com.shobu.walk_in_clinic.R.font.roboto)
                )
            ),
            placeholder = {
                Text(
                    text = stringResource(id = hint),
                    style = TextStyle(
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(
                            Font(com.shobu.walk_in_clinic.R.font.roboto)
                        )
                    ),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
    }
}
package com.app.walk_in_clinic.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.walk_in_clinic.R

@Preview
@Composable
fun SearchViewPrev() {
//    SearchView(hint = R.string.search)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    searchValue: MutableState<String>,
    @StringRes hint: Int,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Transparent)
            .clip(RoundedCornerShape(24.dp))
            .border(
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Black
                ),
            ),
    ) {
        TextField(
            modifier = modifier
                .background(Color.White)
                .fillMaxSize(),
            value = searchValue.value,
            onValueChange = {
                searchValue.value = it
                onValueChange(it)
            },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 13.sp,
                fontFamily = FontFamily(
                    Font(R.font.roboto)
                )
            ),
            placeholder = {
                Text(
                    text = stringResource(id = hint),
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(
                            Font(R.font.roboto)
                        )
                    ),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search location",
                )
            },
        )
    }
}

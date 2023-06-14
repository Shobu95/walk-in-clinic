package com.shobu.walk_in_appointment.ui.components

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobu.walk_in_appointment.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun BorderedDatePickerFieldPrev() {
//    BorderedDatePickerField()
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorderedDatePickerField(
    value: String,
    @StringRes hint: Int,
    onValueChange: (String) -> Unit
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        return
    }

    val year = date.year
    val monthValue = date.monthValue
    val dayOfMonth = date.dayOfMonth


    val datePickerDialog = remember {
        DatePickerDialog(
            context, { _: DatePicker, year: Int, monthValue: Int, dayOfMonth: Int ->
                onValueChange(LocalDate.of(year, monthValue + 1, dayOfMonth).format(formatter))
                focusManager.clearFocus()
            }, year, MonthMapper.monthsMap[monthValue]!!, dayOfMonth
        ).apply {
            setOnDismissListener {
                focusManager.clearFocus()
            }
        }
    }

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
                .fillMaxSize()
                .onFocusChanged {
                    if (it.isFocused) datePickerDialog.show()
                    else datePickerDialog.dismiss()
                },
            value = value.format(formatter),
            onValueChange = {
                onValueChange(LocalDate.of(year, monthValue, dayOfMonth).format(formatter))
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
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(
                            Font(R.font.roboto)
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
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Select a date",
                    modifier = Modifier.clickable {
                        datePickerDialog.show()
                    }
                )
            },
        )
    }
}

object MonthMapper {

    val monthsMap = mapOf(
        1 to 0,
        2 to 1,
        3 to 2,
        4 to 3,
        5 to 4,
        6 to 5,
        7 to 6,
        8 to 7,
        9 to 8,
        10 to 9,
        11 to 10,
        12 to 11,
    )
}
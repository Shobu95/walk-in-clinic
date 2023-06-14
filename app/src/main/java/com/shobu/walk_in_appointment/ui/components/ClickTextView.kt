package com.shobu.walk_in_appointment.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shobu.walk_in_appointment.R


@Preview
@Composable
fun ClickTextViewPrev() {
    ClickTextView(
        R.string.already_have_account,
        R.string.login,
        {}
    )
}

@Composable
fun ClickTextView(
    @StringRes firstString: Int,
    @StringRes secondString: Int,
    onClick: () -> Unit
) {
    ClickableText(
        onClick = { onClick() },
        text = buildAnnotatedString {
            append(stringResource(id = firstString))
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(stringResource(id = secondString))
            }
        },
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
            fontFamily = FontFamily(
                Font(R.font.roboto)
            )
        ),
        modifier = Modifier.padding(top = 10.dp)
    )
}


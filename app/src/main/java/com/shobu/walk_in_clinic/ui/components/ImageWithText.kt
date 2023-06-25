package com.shobu.walk_in_clinic.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shobu.walk_in_clinic.R


@Composable
fun ImageWIthText(@StringRes title: Int) {
    val splashLogo = painterResource(id = R.drawable.app_logo)
    Image(
        painter = splashLogo,
        contentDescription = "splash logo",
        Modifier.size(80.dp)
    )

    Text(
        text = stringResource(id = title),
        Modifier
            .padding(bottom = 30.dp, top = 20.dp)
            .padding(horizontal = 60.dp),
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 26.sp,
            textAlign = TextAlign.Center

        )
    )
}
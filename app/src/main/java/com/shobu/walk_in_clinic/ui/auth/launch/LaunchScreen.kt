package com.shobu.walk_in_clinic.ui.auth.launch

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.components.PrimaryButton
import com.shobu.walk_in_clinic.ui.navigation.auth_nav.AuthNavRoutes

@Preview
@Composable
fun LaunchScreenPrev() {
    val navController = rememberNavController()
    LaunchScreen(R.string.title_launch, navController)
}

@Composable
fun LaunchScreen(
    @StringRes title: Int,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val splashLogo = painterResource(id = R.drawable.app_logo)
        Image(
            painter = splashLogo,
            contentDescription = "splash logo",
            Modifier.size(180.dp)
        )

        Text(
            text = stringResource(id = R.string.app_title),
            Modifier
                .padding(bottom = 180.dp, top = 20.dp)
                .padding(horizontal = 60.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )
        )

        Column(modifier = Modifier.padding(horizontal = 30.dp)) {

            PrimaryButton(
                buttonText = R.string.login,
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                navController.navigate(AuthNavRoutes.Login.route)
            }

            Spacer(modifier = Modifier.height(18.dp))

            PrimaryButton(
                buttonText = R.string.create_an_account,
                backgroundColor = MaterialTheme.colorScheme.secondary
            ) {
                navController.navigate(AuthNavRoutes.Signup.route)
            }
        }
    }

}
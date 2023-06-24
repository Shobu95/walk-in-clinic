package com.shobu.walk_in_appointment.ui.auth.login

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobu.walk_in_appointment.R
import com.shobu.walk_in_appointment.ui.auth.AuthActivity
import com.shobu.walk_in_appointment.ui.components.BorderedPasswordField
import com.shobu.walk_in_appointment.ui.components.BorderedTextField
import com.shobu.walk_in_appointment.ui.components.ClickTextView
import com.shobu.walk_in_appointment.ui.components.ImageWIthText
import com.shobu.walk_in_appointment.ui.components.PrimaryButton
import com.shobu.walk_in_appointment.ui.components.ProgressDialog
import com.shobu.walk_in_appointment.ui.components.ShowToastAlert
import com.shobu.walk_in_appointment.ui.main.MainActivity
import com.shobu.walk_in_appointment.ui.navigation.auth_nav.AuthNavRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun LoginScreenPrev() {
    val navController = rememberNavController()
    LoginScreen(R.string.title_login, navController)
}

@Composable
fun LoginScreen(
    @StringRes title: Int,
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageWIthText(title = title)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {

            var emailValue = remember { mutableStateOf(loginViewModel.state.email) }
            BorderedTextField(textFieldValue = emailValue, R.string.email) { email ->
                loginViewModel.onEvent(
                    LoginEvents.OnStateChange(
                        state = loginViewModel.state.copy(
                            email = email
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            var passwordValue = remember { mutableStateOf(loginViewModel.state.password) }
            BorderedPasswordField(textFieldValue = passwordValue, R.string.password) { password ->
                loginViewModel.onEvent(
                    LoginEvents.OnStateChange(
                        state = loginViewModel.state.copy(
                            password = password
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
            PrimaryButton(
                buttonText = R.string.login,
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                loginViewModel.onEvent(LoginEvents.OnLoginClicked)
                showDialog = true
            }

            Spacer(modifier = Modifier.height(10.dp))
            ClickTextView(
                R.string.dont_have_account,
                R.string.create
            ) { navController.navigate(AuthNavRoutes.Signup.route) }

            if (loginViewModel.state.loginSuccess) {
                LaunchedEffect(key1 = null) {
                    delay(3500)
                    showDialog = false
                    ShowToastAlert(
                        context = context,
                        message = "Login Successfully"
                    )
                    AuthActivity.navigateToActivity(context, MainActivity())
                }
            }

            if (showDialog) {
                ProgressDialog()
            }

            LaunchedEffect(key1 = true) {
                loginViewModel.loginFailState.collectLatest {
                    delay(1000)
                    showDialog = false
                    ShowToastAlert(
                        context = context,
                        message = it.message
                    )
                }
            }
        }
    }
}



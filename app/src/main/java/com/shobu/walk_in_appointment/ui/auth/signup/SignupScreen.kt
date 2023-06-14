package com.shobu.walk_in_appointment.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.os.Build
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobu.walk_in_appointment.R
import com.shobu.walk_in_appointment.ui.auth.AuthActivity
import com.shobu.walk_in_appointment.ui.components.BorderedDatePickerField
import com.shobu.walk_in_appointment.ui.components.BorderedDropDown
import com.shobu.walk_in_appointment.ui.components.BorderedPasswordField
import com.shobu.walk_in_appointment.ui.components.BorderedTextField
import com.shobu.walk_in_appointment.ui.components.ClickTextView
import com.shobu.walk_in_appointment.ui.components.ImageWIthText
import com.shobu.walk_in_appointment.ui.components.PrimaryButton
import com.shobu.walk_in_appointment.ui.components.ProgressDialog
import com.shobu.walk_in_appointment.ui.home.HomeActivity
import com.shobu.walk_in_appointment.ui.navigation.auth_nav.AuthNavRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun SignupScreenPrev() {
    val navController = rememberNavController()
    SignupScreen(R.string.title_register, navController)
}

@Composable
fun SignupScreen(
    @StringRes title: Int,
    navController: NavHostController,
    signupViewModel: SignupViewModel = hiltViewModel()
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

            var nameValue = remember { mutableStateOf(signupViewModel.state.fullName) }
            BorderedTextField(textFieldValue = nameValue, R.string.full_name) { fullName ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            fullName = fullName
                        )
                    )
                )
            }


            Spacer(modifier = Modifier.height(30.dp))
            var dateValue = remember { mutableStateOf(signupViewModel.state.dob) }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                BorderedDatePickerField(
                    value = dateValue.value,
                    hint = R.string.date_of_birth,
                    onValueChange = { date ->
                        dateValue.value = date
                        signupViewModel.onEvent(
                            SignupEvents.OnStateChange(
                                state = signupViewModel.state.copy(
                                    dob = date
                                )
                            )
                        )
                    },
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            BorderedDropDown(hint = R.string.gender) { gender ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            gender = gender
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            var emailValue = remember { mutableStateOf(signupViewModel.state.email) }
            BorderedTextField(textFieldValue = emailValue, R.string.email) { email ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            email = email
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            var passwordValue = remember { mutableStateOf(signupViewModel.state.password) }
            BorderedPasswordField(textFieldValue = passwordValue, R.string.password) { password ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            password = password
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            PrimaryButton(
                buttonText = R.string.signup,
                backgroundColor = MaterialTheme.colorScheme.secondary
            ) {
                signupViewModel.onEvent(SignupEvents.OnSignupClicked)
                showDialog = true
            }

            if (signupViewModel.state.signupSuccess) {
                LaunchedEffect(key1 = null) {
                    delay(3500)
                    showDialog = false
                    AuthActivity.navigateToActivity(context, HomeActivity())

                }
            }
        }

        if (showDialog) {
            ProgressDialog()
        }

        Spacer(modifier = Modifier.height(10.dp))
        ClickTextView(
            R.string.already_have_account,
            R.string.login
        ) { navController.navigate(AuthNavRoutes.Login.route) }
    }
}



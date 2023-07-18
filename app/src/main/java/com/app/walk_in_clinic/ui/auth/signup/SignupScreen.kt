package com.app.walk_in_clinic.ui.auth.signup

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.walk_in_clinic.R
import com.app.walk_in_clinic.ui.auth.AuthActivity
import com.app.walk_in_clinic.ui.components.BorderedDatePickerField
import com.app.walk_in_clinic.ui.components.BorderedDropDown
import com.app.walk_in_clinic.ui.components.BorderedPasswordField
import com.app.walk_in_clinic.ui.components.BorderedTextField
import com.app.walk_in_clinic.ui.components.ClickTextView
import com.app.walk_in_clinic.ui.components.ImageWIthText
import com.app.walk_in_clinic.ui.components.PrimaryButton
import com.app.walk_in_clinic.ui.components.ProgressDialog
import com.app.walk_in_clinic.ui.components.ShowToastAlert
import com.app.walk_in_clinic.ui.main.MainActivity
import com.app.walk_in_clinic.ui.navigation.auth_nav.AuthNavRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Preview
@Composable
fun SignupScreenPrev() {
    val navController = rememberNavController()
    SignupScreen(R.string.create_an_account, navController)
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
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        ImageWIthText(title = title)
        Spacer(modifier = Modifier.height(20.dp))


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

            Spacer(modifier = Modifier.height(20.dp))
            var phoneNumberValue = remember { mutableStateOf(signupViewModel.state.phoneNumber) }
            BorderedTextField(
                textFieldValue = phoneNumberValue,
                hint = R.string.phone_number,
                keyboardType = KeyboardType.Phone,
            ) { phoneNumber ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            phoneNumber = phoneNumber
                        )
                    )
                )
            }


            Spacer(modifier = Modifier.height(20.dp))
            var dateValue = remember { mutableStateOf(signupViewModel.state.dateOfBirth) }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                BorderedDatePickerField(
                    value = dateValue.value,
                    hint = R.string.date_of_birth,
                    onValueChange = { date ->
                        dateValue.value = date
                        signupViewModel.onEvent(
                            SignupEvents.OnStateChange(
                                state = signupViewModel.state.copy(
                                    dateOfBirth = date
                                )
                            )
                        )
                    },
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            BorderedDropDown(hint = R.string.gender) { gender ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            gender = gender
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {

                var heightValue = remember { mutableStateOf(signupViewModel.state.height) }
                BorderedTextField(
                    textFieldValue = heightValue,
                    hint = R.string.height,
                    modifier = Modifier.weight(1F)
                ) { height ->
                    signupViewModel.onEvent(
                        SignupEvents.OnStateChange(
                            state = signupViewModel.state.copy(
                                height = height
                            )
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                var weightValue = remember { mutableStateOf(signupViewModel.state.weight) }
                BorderedTextField(
                    textFieldValue = weightValue,
                    hint = R.string.weight,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1F)
                ) { weight ->
                    signupViewModel.onEvent(
                        SignupEvents.OnStateChange(
                            state = signupViewModel.state.copy(
                                weight = weight
                            )
                        )
                    )
                }
            }



            Spacer(modifier = Modifier.height(20.dp))
            var emailValue = remember { mutableStateOf(signupViewModel.state.email) }
            BorderedTextField(
                textFieldValue = emailValue,
                hint = R.string.email,
                keyboardType = KeyboardType.Email
            ) { email ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            email = email
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
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

            Spacer(modifier = Modifier.height(20.dp))
            var confirmPasswordValue =
                remember { mutableStateOf(signupViewModel.state.confirmPassword) }
            BorderedPasswordField(
                textFieldValue = confirmPasswordValue,
                R.string.confirm_password
            ) { confirmPassword ->
                signupViewModel.onEvent(
                    SignupEvents.OnStateChange(
                        state = signupViewModel.state.copy(
                            confirmPassword = confirmPassword
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
            PrimaryButton(
                buttonText = R.string.create_an_account,
                backgroundColor = MaterialTheme.colorScheme.secondary
            ) {
                signupViewModel.onEvent(SignupEvents.OnSignupClicked)
                showDialog = true
            }

            if (signupViewModel.state.signupSuccess) {
                LaunchedEffect(key1 = null) {
                    delay(3500)
                    showDialog = false
                    AuthActivity.navigateToActivity(context, MainActivity())

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

        Spacer(modifier = Modifier.height(20.dp))

        LaunchedEffect(key1 = true) {
            signupViewModel.signupFailState.collectLatest {
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



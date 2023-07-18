package com.app.walk_in_clinic.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.app.walk_in_clinic.R
import com.app.walk_in_clinic.data.prefs.PreferenceKeys
import com.app.walk_in_clinic.data.prefs.UserPreferences
import com.app.walk_in_clinic.ui.auth.AuthActivity
import com.app.walk_in_clinic.ui.main.MainActivity
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var prefs: UserPreferences

    private val viewModel by viewModels<SplashViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkInClinicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreenBody()
                }
            }

            if (prefs.getString(PreferenceKeys.FULL_NAME, "").isNullOrEmpty()) {
                navigateActivity(LocalContext.current, AuthActivity())
            } else {
                viewModel.checkAppointments()
                navigateActivity(LocalContext.current, MainActivity())
            }

        }
    }

    private fun navigateActivity(context: Context, activity: Activity) {
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(
                this@SplashActivity,
                activity::class.java
            )
            context.startActivity(intent)
            finish()
        }
    }
}

@Preview
@Composable
fun SplashScreenBodyPrev() {
    SplashScreenBody()
}

@Composable
fun SplashScreenBody() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
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

            }

            Text(
                text = "LOADING...",
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.sp,
                    fontSize = 12.sp
                )
            )
        }
    }
}
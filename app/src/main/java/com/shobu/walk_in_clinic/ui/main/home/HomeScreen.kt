package com.shobu.walk_in_clinic.ui.main.home

import NavDrawerItem
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.appointments.MyAppointmentActivity
import com.shobu.walk_in_clinic.ui.appointments.components.AppointmentListItem
import com.shobu.walk_in_clinic.ui.auth.AuthActivity
import com.shobu.walk_in_clinic.ui.book_appointment.components.HeadingText
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.main.MainActivity
import com.shobu.walk_in_clinic.ui.navigation.nav_drawer.getNavList
import com.shobu.walk_in_clinic.ui.profile.ProfileActivity
import com.shobu.walk_in_clinic.ui.search_clinic.SearchClinicActivity
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun UpcomingAppointmentScreenPrev() {
    val navController = rememberNavController()
    HomeScreen(
        R.string.title_home,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    @StringRes title: Int,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // icons to mimic drawer destinations
    val items = getNavList()
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.width(280.dp)

            ) {
                Spacer(Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, bottom = 30.dp)
                ) {
                    val splashLogo = painterResource(id = R.drawable.app_logo)
                    Image(
                        painter = splashLogo,
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "splash logo",
                        modifier = Modifier.size(40.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.menu),
                        Modifier
                            .padding(bottom = 30.dp, top = 30.dp)
                            .padding(horizontal = 10.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center

                        )
                    )
                }

                items.forEach { item ->

                    NavDrawerItem(
                        item = item
                    ) {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                        when (item.title) {
                            R.string.title_my_appointments -> {
                                MainActivity.navigateToActivity(context, MyAppointmentActivity())
                            }

                            R.string.title_search_for_clinics -> {
                                MainActivity.navigateToActivity(context, SearchClinicActivity())
                            }

                            R.string.title_profile -> {
                                MainActivity.navigateToActivity(context, ProfileActivity())
                            }

                            R.string.title_logout -> {
                                viewModel.onEvent(HomeEvent.OnLogout)
                            }
                        }
                    }
                    Spacer(Modifier.height(30.dp))

                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    MyTopAppBar(
                        title = title,
                        showBack = false
                    ) {
                        scope.launch { drawerState.open() }
                    }
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .background(MaterialTheme.colorScheme.background),
                ) {

                    if (viewModel.state.onLogout) {
                        context.startActivity(Intent(context, AuthActivity::class.java))
                        (context as Activity).finishAffinity()
                    }

                    UserInfo(viewModel.state.fullName)

                    if (viewModel.state.appointment.id != null) {
                        Spacer(modifier = Modifier.height(20.dp))
                        HeadingText(heading = stringResource(id = R.string.upcoming_appointment))
                        AppointmentListItem(appointment = viewModel.state.appointment)
                    }


                }
            }
        }
    )


}
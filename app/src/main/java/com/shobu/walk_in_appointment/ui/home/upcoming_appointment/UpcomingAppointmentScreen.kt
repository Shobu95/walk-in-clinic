package com.shobu.walk_in_appointment.ui.home.upcoming_appointment

import NavDrawerItem
import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shobu.walk_in_appointment.R
import com.shobu.walk_in_appointment.ui.components.MyTopAppBar
import com.shobu.walk_in_appointment.ui.navigation.nav_drawer.getNavList
import kotlinx.coroutines.launch


@Preview
@Composable
fun UpcomingAppointmentScreenPrev() {
    val navController = rememberNavController()
    UpcomingAppointmentScreen(
        R.string.title_home, navController
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpcomingAppointmentScreen(
    @StringRes title: Int,
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
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
                items.forEach { item ->

                    NavDrawerItem(
                        item = item
                    ) {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    }
                    Spacer(Modifier.height(20.dp))

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
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                }
            }
        }
    )


}
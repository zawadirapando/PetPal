package com.example.petpal.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petpal.ui.screens.BookingsScreen
import com.example.petpal.ui.screens.FindScreen
import com.example.petpal.ui.screens.HomeScreen
import com.example.petpal.ui.screens.ProfileScreen
import com.example.petpal.ui.screens.RescueScreen

@Composable
fun MainScreen(
    onNavigatetoChats: () -> Unit
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(
                                text = item.title
                            )
                        },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.title){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ){
            composable(Screen.Home.route) { HomeScreen(onNavigateToChats = onNavigatetoChats) }
            composable(Screen.Find.route) { FindScreen() }
            composable(Screen.Bookings.route) { BookingsScreen() }
            composable(Screen.Rescue.route) { RescueScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
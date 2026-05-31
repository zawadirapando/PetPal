package com.example.petpal.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

//closed off list of screens
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Find : Screen("find")
    object Bookings : Screen("bookings")
    object Rescue : Screen("rescue")
    object Profile : Screen("profile")
}

//ui blueprint for buttons in navbar
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(title = "Home", icon = Icons.Default.Home, route = Screen.Home.route),
    BottomNavItem(title = "Find", icon = Icons.Default.Search, route = Screen.Find.route),
    BottomNavItem(title = "Bookings", icon = Icons.Default.DateRange, route = Screen.Bookings.route),
    BottomNavItem(title = "Rescue", icon = Icons.Default.Pets, route = Screen.Rescue.route),
    BottomNavItem(title = "Profile", icon = Icons.Default.Person, route = Screen.Profile.route)
)
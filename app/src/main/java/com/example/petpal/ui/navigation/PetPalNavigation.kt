package com.example.petpal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petpal.ui.screens.LoginScreen
import com.example.petpal.ui.screens.ResetPasswordScreen
import com.example.petpal.ui.screens.SignUpScreen

@Composable
fun PetPalNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate("signup") {
                        launchSingleTop = true
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToResetPassword = {navController.navigate("reset_password")}
            )
        }

        composable("signup"){
            SignUpScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        launchSingleTop = true
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        composable("reset_password"){
            ResetPasswordScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        launchSingleTop = true
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }
    }
}
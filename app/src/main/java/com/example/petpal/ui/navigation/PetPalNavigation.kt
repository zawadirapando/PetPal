package com.example.petpal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petpal.ui.screens.ChatListScreen
import com.example.petpal.ui.screens.HomeScreen
import com.example.petpal.ui.screens.LoginScreen
import com.example.petpal.ui.screens.ResetPasswordScreen
import com.example.petpal.ui.screens.SignUpScreen
import com.example.petpal.viewmodels.AuthViewModel

@Composable
fun PetPalNavigation(
    viewModel: AuthViewModel = hiltViewModel()
){
    val navController = rememberNavController()

    val startScreen = if (viewModel.isUserAuthenticated) "main_app" else "login"

    NavHost(
        navController = navController,
        startDestination = startScreen
    ){
        composable("login"){
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate("signup") {
                        launchSingleTop = true
                    }
                },
                onNavigateToResetPassword = {navController.navigate("reset_password")},
                onLoginSuccess = {
                    navController.navigate("main_app"){
                        popUpTo("login") {inclusive = true} //to prevent pressing back and going back to login page
                    }
                }
            )
        }

        composable("signup"){
            SignUpScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onSignUpSuccess = {
                    navController.navigate("main_app"){
                        popUpTo("login") {inclusive = true}
                    }
                }
            )
        }

        composable("reset_password"){
            ResetPasswordScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("home"){
            HomeScreen (
                onNavigateToChats = {
                    navController.navigate("chatList")
                }
            )
        }

        composable("chatList"){
            ChatListScreen (
                onNavigateToChatDetail = {threadId ->
                    println("Clicked chat: $threadId")
                }
            )
        }

        composable("main_app"){
            MainScreen(
                onNavigatetoChats = {
                    navController.navigate("chatList")
                }
            )
        }
    }
}
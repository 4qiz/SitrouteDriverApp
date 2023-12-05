package com.example.sitroutedriverapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sitroutedriverapp.Routes

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    Column {
        NavHost(navController = navController, startDestination = Routes.Login.route) {

            composable(Routes.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(Routes.Home.route) {
                HomeScreen(navController = navController)
                ScaffoldWithTopBar(navController)
            }
            composable(Routes.Status.route) {
                StatusScreen(navController = navController)
                ScaffoldWithTopBar(navController)
            }
        }
    }
}
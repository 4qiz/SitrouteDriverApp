package com.example.sitroutedriverapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sitroutedriverapp.R
import com.example.sitroutedriverapp.Routes

@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    val itemSources = remember { mutableStateOf(Routes.Login.route) }
    Column {
        if(itemSources.value != Routes.Login.route)
            ScaffoldWithTopBar(navController)
        NavHost(navController = navController, startDestination = Routes.Login.route) {
            composable(Routes.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(Routes.Home.route) {
                HomeScreen()
                itemSources.value = Routes.Home.route
            }
            composable(Routes.Status.route) {
                StatusScreen()
                itemSources.value = Routes.Status.route
            }
            composable(Routes.Chat.route) {
                ChatScreen()
                itemSources.value = Routes.Chat.route
            }
        }
    }
}
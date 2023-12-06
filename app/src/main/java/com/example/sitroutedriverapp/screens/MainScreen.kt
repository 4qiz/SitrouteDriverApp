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
    ScaffoldWithTopBar(navController = navController)
}
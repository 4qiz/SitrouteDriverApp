package com.example.sitroutedriverapp.screens

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.compose.rememberNavController


@Composable
fun ScreenMain(){

    val navController = rememberNavController()
    ScaffoldWithTopBar(navController = navController)
}




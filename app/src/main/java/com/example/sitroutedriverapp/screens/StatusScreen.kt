package com.example.sitroutedriverapp.screens

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery0Bar
import androidx.compose.material.icons.filled.Battery1Bar
import androidx.compose.material.icons.filled.Battery2Bar
import androidx.compose.material.icons.filled.Battery3Bar
import androidx.compose.material.icons.filled.Battery4Bar
import androidx.compose.material.icons.filled.Battery5Bar
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import java.util.jar.Manifest

@Composable
fun StatusScreen(){
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Column{
            var context = LocalContext.current
            var charge by remember { mutableStateOf(getCharge(context))}
            Icon(when(charge){
                in 0..5 -> Icons.Filled.Battery0Bar
                in 6..20 -> Icons.Filled.Battery1Bar
                in 21..35 -> Icons.Filled.Battery2Bar
                in 36..50-> Icons.Filled.Battery3Bar
                in 51..65-> Icons.Filled.Battery4Bar
                in 66..80-> Icons.Filled.Battery5Bar
                in 81..95-> Icons.Filled.Battery6Bar
                else -> Icons.Filled.BatteryFull
            }, contentDescription = null, Modifier.size(200.dp).align(Alignment.CenterHorizontally))
            Text("${charge}%", fontSize = 30.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

fun getCharge(context: Context) : Int {
    //Симуляция заряда
    val batteryManager = context.getSystemService(BATTERY_SERVICE) as BatteryManager
    return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}
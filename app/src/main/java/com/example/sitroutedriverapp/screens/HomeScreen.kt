package com.example.sitroutedriverapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sitroutedriverapp.Connection
import com.example.sitroutedriverapp.R
import com.example.sitroutedriverapp.Routes
import com.example.sitroutedriverapp.component.ButtonNavigation
import com.example.sitroutedriverapp.models.Schedule
import com.example.sitroutedriverapp.PressBackAgainToExit
import com.example.sitroutedriverapp.settingsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        RouteView()
    }
}

@Composable
fun RouteView() {
    var errorMessage by remember { mutableStateOf("Ошибка") }
    var schedules by remember { mutableStateOf<List<Schedule>>(emptyList()) }

    val listCall = settingsConnection().getSchedules(Connection.CurrentUser!!.idUser)
    listCall.enqueue(object : Callback<List<Schedule>> {
        override fun onResponse(call: Call<List<Schedule>>, response: Response<List<Schedule>>) {
            schedules = response.body() ?: emptyList()
        }

        override fun onFailure(call: Call<List<Schedule>>, t: Throwable) {

        }
    })
    Schedules(schedules)
}

@Composable
fun Schedules(schedules: List<Schedule>) {
    LazyColumn {
        items(schedules) { schedule ->
            val time = LocalDateTime.parse(schedule.time)
            var isNext = LocalDateTime.now() < time
            Text(
                "${time.toLocalTime()} - ${schedule.idBusStationNavigation.name}",
                fontWeight = if (isNext) FontWeight.Bold
                else FontWeight.Normal,
                fontSize = if (isNext) 20.sp else 16.sp
            )
        }
    }
}

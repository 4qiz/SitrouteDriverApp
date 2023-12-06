package com.example.sitroutedriverapp.screens

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sitroutedriverapp.Connection
import com.example.sitroutedriverapp.R
import com.example.sitroutedriverapp.Routes
import com.example.sitroutedriverapp.component.ButtonNavigation
import com.example.sitroutedriverapp.models.Message
import com.example.sitroutedriverapp.models.Route
import com.example.sitroutedriverapp.settingsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DrawerHeader()
        Text("Типо расписание")
        val listCall = settingsConnection().getRoutes(Connection.CurrentUser!!.idUser)
        listCall.enqueue(object : Callback<List<Route>> {
            override fun onResponse(call: Call<List<Route>>, response: Response<List<Route>>) {
                val routes = response.body() ?: emptyList()
                //Если запрос сработал
            }

            override fun onFailure(call: Call<List<Route>>, t: Throwable) {
                //Если запрос не сработал
            }
        })
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(navController: NavHostController) {
    val itemSources = remember { mutableStateOf(Routes.Login.route) }
    Scaffold(
        bottomBar = {if(itemSources.value != Routes.Login.route) ButtonNavigation(navigation = navController)}
    ){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Column {
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
    }
}

@Composable
private fun DrawerHeader() {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.sitroute_modile),
            contentDescription = null,
            //tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.height(50.dp)
        )
        Text(text = "Sitroute", fontSize = 28.sp)
    }
}

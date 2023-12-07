package com.example.sitroutedriverapp.screens

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Ro
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
        Text("--В разработке--")
             RoutesView()
    }
}

@Composable
fun RoutesView() {
    var errorMessage by remember{mutableStateOf("Ошибка")}
    var route by remember { mutableStateOf<Route?>(null) }
    val call = settingsConnection().getRoutes(Connection.CurrentUser!!.idUser)
    call.enqueue(object : Callback<Route> {
        override fun onResponse(call: Call<Route>, response: Response<Route>) {
            route = response.body()
            println()
            //Если запрос сработал
        }

        override fun onFailure(call: Call<Route>, t: Throwable) {
            errorMessage = "Пока"
            //Если запрос не сработал
        }
    })
    Text(route?.idRoute.toString())
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

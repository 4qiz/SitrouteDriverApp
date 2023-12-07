package com.example.sitroutedriverapp.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sitroutedriverapp.Routes
import javax.xml.transform.Source

@Composable
fun ButtonNavigation(navigation: NavHostController, modifier: Modifier = Modifier) {
    val selectedItem = rememberSaveable { mutableStateOf(Routes.Home.route) }
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Route , contentDescription = "") },
            label = { Text("Маршрут") },
            selected = selectedItem.value == Routes.Home.route,
            onClick = {if(selectedItem.value != Routes.Home.route)navigation.navigate(Routes.Home.route)
                selectedItem.value = Routes.Home.route }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Message, contentDescription = "") },
            label = { Text("Чат") },
            selected = selectedItem.value == Routes.Chat.route,
            onClick = {if(selectedItem.value != Routes.Chat.route) navigation.navigate(Routes.Chat.route)
                selectedItem.value = Routes.Chat.route}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.DirectionsTransit, contentDescription = "") },
            label = { Text("Состояние") },
            selected = selectedItem.value == Routes.Status.route,
            onClick = { if(selectedItem.value != Routes.Status.route)navigation.navigate(Routes.Status.route)
            selectedItem.value = Routes.Status.route}
        )
    }
}
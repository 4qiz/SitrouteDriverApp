package com.example.sitroutedriverapp.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsTransit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sitroutedriverapp.Routes


@Composable
fun NavButtonNavugation(navigation: NavHostController, modifier: Modifier = Modifier) {
    val selectedItem = remember { mutableStateOf(Routes.Home.route) }
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Email, contentDescription = "") },
            label = { Text("Карта") },
            selected = selectedItem.value == Routes.Home.route,
            onClick = { navigation.navigate(Routes.Home.route)
                selectedItem.value = Routes.Home.route }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Message, contentDescription = "") },
            label = { Text("Чат") },
            selected = selectedItem.value == Routes.Chat.route,
            onClick = { navigation.navigate(Routes.Chat.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.DirectionsTransit, contentDescription = "") },
            label = { Text("Состояние машина") },
            selected = selectedItem.value == Routes.Status.route,
            onClick = { navigation.navigate(Routes.Status.route)
            selectedItem.value = Routes.Status.route}
        )
    }
}
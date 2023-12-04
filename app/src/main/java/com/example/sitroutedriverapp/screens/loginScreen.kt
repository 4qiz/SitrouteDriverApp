package com.example.sitroutedriverapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.sitroutedriverapp.Routes
import com.example.sitroutedriverapp.models.User
import com.example.sitroutedriverapp.settingsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    Column {
        Text("Sitroute")
        Text("Логин")
        TextField(value = login, onValueChange = { login = it })
        Text("Пароль")
        TextField(
            value = password,
            onValueChange = { password = it },
            supportingText = { Text(errorMessage) })
        Button(onClick = { navController.navigate(Routes.Home.route)
            //val repos = settingsConnection().currentUser(login, password)
            //repos.enqueue(object : Callback<User> {
            //    override fun onResponse(call: Call<User>, response: Response<User>) {
            //        if (response.isSuccessful) {
            //            val currentUser = response.body()
                            //            if (currentUser?.idUser == 0) {
            //                errorMessage = "* Неправильный логин или пароль"
                                    //            } else if (currentUser?.driver != null) {
            //                 navController.navigate(Routes.Home.route)
                                    //            } else {
            //                errorMessage = "У вас нет прав для этого приложения"
                                    //            }
                                //        }
                        //    }
//
            //    override fun onFailure(call: Call<User>, t: Throwable) {
            //        errorMessage = "Ошибка"
                        //    }
            //})
        }) {
            Text("Войти")
        }
    }
}
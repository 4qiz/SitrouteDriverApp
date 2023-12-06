package com.example.sitroutedriverapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sitroutedriverapp.Connection
import com.example.sitroutedriverapp.R
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
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sitroute_modile),
            contentDescription = null,
            modifier = Modifier.padding(top = 80.dp).size(100.dp),

        )
        Text(
            "Sitroute",
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp),
            fontSize = 38.sp,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Логин",
                modifier = Modifier.padding(0.dp, 10.dp),
                fontSize = 28.sp,
            )
            TextField(value = login,
                onValueChange = { login = it },
                singleLine = true,)
            Text(
                "Пароль",
                modifier = Modifier.padding(0.dp, 10.dp),
                fontSize = 28.sp,
            )
//            TextField(
//                value = password,
//                onValueChange = { password = it },
//                supportingText = { Text(errorMessage) },
//                )

            TextField(
                value = password,
                onValueChange = { password = it },
                //label = { Text("Password") },
                singleLine = true,
                //placeholder = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                supportingText = { Text(errorMessage) },
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )


            Button(modifier = Modifier.width(270.dp).padding(0.dp,20.dp,0.dp,0.dp),
                onClick = {
                val user = settingsConnection().getCurrentUser(login, password)
                user.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val currentUser = response.body()
                            if (currentUser?.idUser == 0) {
                                errorMessage = "* Неправильный логин или пароль"
                            } else if (currentUser?.driver != null) {
                                Connection.CurrentUser = currentUser
                                navController.navigate(Routes.Home.route)
                            } else {
                                errorMessage = "У вас нет прав для этого приложения"
                            }
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        errorMessage = "Ошибка"
                    }
                },
                    )
            }) {
                Text("Войти",
                    fontSize = 28.sp,
                    )
            }
        }
    }
}
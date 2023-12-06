 package com.example.sitroutedriverapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sitroutedriverapp.Connection
import com.example.sitroutedriverapp.models.Message
import com.example.sitroutedriverapp.settingsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var newMessage by rememberSaveable { mutableStateOf("") }
    val messages = mutableListOf<Message>()
    Scaffold(
        bottomBar = {
            TextField(value = newMessage, onValueChange = { newMessage = it })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val listCall = settingsConnection().getMessages(Connection.CurrentUser!!.idUser)
            listCall.enqueue(object : Callback<List<Message>> {
                override fun onResponse(
                    call: Call<List<Message>>,
                    response: Response<List<Message>>
                ) {
                    val messagesList = response.body()
                    messages.addAll(messagesList!!)
                }

                override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                    messages.add(Message(1, "ошибка", null, null, "", null, null))
                }
            })
            Column {
                for (message in messages) {
                    Text(message.value)
                }
            }
        }
    }
}
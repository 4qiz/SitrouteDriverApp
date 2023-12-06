package com.example.sitroutedriverapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import java.time.LocalDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var newMessage by rememberSaveable { mutableStateOf("") }
    var messages by rememberSaveable { mutableStateOf<List<Message>>(emptyList()) }
    val listCall = settingsConnection().getMessages(Connection.CurrentUser!!.idUser)
    listCall.enqueue(object : Callback<List<Message>> {
        override fun onResponse(
            call: Call<List<Message>>,
            response: Response<List<Message>>
        ) {
            messages = response.body() ?: emptyList()
        }

        override fun onFailure(call: Call<List<Message>>, t: Throwable) {
            // messages.add(Message(1, "ошибка", null, null, "", null, null))
        }
    })
    Scaffold(
        bottomBar = {
            Row(Modifier.padding(horizontal = 5.dp)) {
                TextField(value = newMessage, onValueChange = { newMessage = it })
                IconButton(onClick = {
                    val message = Message(
                        value = newMessage,
                        idSender = Connection.CurrentUser!!.idUser
                    )
                    if (newMessage != "") {
                        val response = settingsConnection().sendMessage(message)
                        if(response.isSuccessful){
                        messages = messages + message
                        }
                    }
                }) { Icon(Icons.Filled.Send, contentDescription = "") }
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Messages(messages)
        }
    }
}

@Composable
fun Messages(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            Text(message.value)
        }
    }
}


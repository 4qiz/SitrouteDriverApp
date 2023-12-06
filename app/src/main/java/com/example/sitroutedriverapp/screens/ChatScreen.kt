package com.example.sitroutedriverapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sitroutedriverapp.Connection
import com.example.sitroutedriverapp.models.Message
import com.example.sitroutedriverapp.settingsConnection
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                TextField(
                    value = newMessage,
                    onValueChange = { newMessage = it },
                    Modifier.weight(0.9f)
                )
                IconButton(onClick = {
                    val message = Message(
                        value = newMessage.trim(),
                        idSender = Connection.CurrentUser!!.idUser
                    )
                    if (newMessage.trim() != "") {
                        val listCall = settingsConnection().sendMessage(message)
                        listCall.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                messages = messages + message
                                newMessage = ""
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {

                            }
                        })
                    }
                }, Modifier.weight(0.1f)) { Icon(Icons.Filled.Send, contentDescription = "") }
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
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyColumnListState) {
        items(messages) { message ->
            MessageListItem(message)
        }
    }
    LaunchedEffect(messages.size) {
        lazyColumnListState.scrollToItem(messages.size - 1)
    }
}

@Composable
fun MessageListItem(message: Message) {
    val isDriverMessage = message.idSender == Connection.CurrentUser!!.idUser
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = when { // 2
            isDriverMessage -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = СardShapeFor(message), // 3
            colors = CardDefaults.cardColors(
                when {
                    isDriverMessage -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.secondary
                }
            ),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = message.value,
                color = when {
                    isDriverMessage -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.onSecondary
                },
            )
        }
        Text(
            // 4
            text = when {
                isDriverMessage -> "Вы"
                else -> "Диспетчер"
            },
            fontSize = 12.sp,
        )
    }
}

@Composable
fun СardShapeFor(message: Message): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        message.idSender == Connection.CurrentUser!!.idUser ->
            roundedCorners.copy(bottomEnd = CornerSize(0))

        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}
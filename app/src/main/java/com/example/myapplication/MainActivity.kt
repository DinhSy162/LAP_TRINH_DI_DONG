package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen(context = this)
            }
        }
    }
}

@Composable
fun MainScreen(context: ComponentActivity) {
    var textInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = { Text("Nhập thông tin cần gửi...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16dp))

        Button(
            onClick = {
                // Tạo Intent để chuyển sang SecondActivity
                val intent = Intent(context, SecondActivity::class.java)

                // Sử dụng Bundle để đính kèm dữ liệu (extras)
                val bundle = Bundle().apply {
                    putString("KEY_DATA", textInput)
                }
                intent.putExtras(bundle)

                // Khởi chạy màn hình 2
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gửi sang Màn hình 2")
        }
    }
}
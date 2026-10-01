package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nhận dữ liệu từ Bundle/Intent được gửi sang
        val bundle = intent.extras
        val receivedData = bundle?.getString("KEY_DATA") ?: "Không có dữ liệu"

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(24dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dữ liệu nhận được:\n$receivedData",
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(24dp))

                    Button(onClick = {
                        // Quay lại màn hình 1 bằng lệnh finish()
                        finish()
                    }) {
                        Text("Quay lại Màn hình 1")
                    }
                }
            }
        }
    }
}
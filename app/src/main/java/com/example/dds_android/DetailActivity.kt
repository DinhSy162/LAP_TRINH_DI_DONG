package com.example.dds_android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Lấy dữ liệu từ Intent
        val fullName = intent.getStringExtra("fullName") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val studentId = intent.getStringExtra("studentId") ?: ""
        val className = intent.getStringExtra("className") ?: ""

        // Ánh xạ TextView
        val tvFullName = findViewById<TextView>(R.id.tvFullName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvStudentId = findViewById<TextView>(R.id.tvStudentId)
        val tvClassName = findViewById<TextView>(R.id.tvClassName)

        // Hiển thị dữ liệu
        tvFullName.text = "Họ tên: $fullName"
        tvEmail.text = "Email: $email"
        tvPhone.text = "Số điện thoại: $phone"
        tvStudentId.text = "MSV: $studentId"
        tvClassName.text = "Lớp: $className"

        // Nút quay lại
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
    }
}
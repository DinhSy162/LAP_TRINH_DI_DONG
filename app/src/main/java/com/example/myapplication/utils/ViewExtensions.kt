package com.example.myapplication.utils
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

class ViewExtensions {
}
fun Double.toAcademicRanking(): String = when {
    this >= 3.6 -> "Xuất sắc!!"
    this >= 3.2 -> "Giỏi"
    this >= 2.5 -> "Khá"
    else -> "Trung bình"
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
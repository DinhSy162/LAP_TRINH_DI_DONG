package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.model.Student
import com.example.myapplication.utils.toAcademicRanking
import com.example.myapplication.utils.toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.hideKeyboard


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentStudent = Student(
        id = "2415053122239",
        name = "Đỗ Đình Sỹ",
        className = "125DHPC01",
        email = "sydd@sv.ute.udn.vn",
        gpa = 3.8
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.getDouble("KEY_SAVED_GPA")?.let {
                savedGpa -> currentStudent = currentStudent.copy(gpa = savedGpa)
        }
        bindStudentData(currentStudent)
        setupListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun setupListeners() {
        binding.btnUpdateGpa.setOnClickListener { view ->
            view.hideKeyboard()

            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)

            with(binding.edtNewGpa) {
                error = null
                text?.clear()
                clearFocus()
            }
            toast("Cập nhật điểm thành công!")
        }
    }
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} | Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("KEY_SAVED_GPA", currentStudent.gpa)
    }
}


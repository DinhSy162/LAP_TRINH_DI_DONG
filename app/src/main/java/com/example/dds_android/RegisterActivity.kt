package com.example.dds_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.dds_android.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupListeners()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.scrollRegister) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = maxOf(systemBars.bottom, ime.bottom)
            binding.scrollRegister.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                bottomPadding
            )
            insets
        }
    }

    private fun setupListeners() {
        // Tự động xóa lỗi và khôi phục viền bình thường khi người dùng sửa dữ liệu
        binding.edtFullName.doAfterTextChanged {
            binding.edtFullName.error = null
            binding.edtFullName.setBackgroundResource(R.drawable.bg_edit_text_selector)
        }
        binding.edtEmail.doAfterTextChanged {
            binding.edtEmail.error = null
            binding.edtEmail.setBackgroundResource(R.drawable.bg_edit_text_selector)
        }
        binding.edtPhone.doAfterTextChanged {
            binding.edtPhone.error = null
            binding.edtPhone.setBackgroundResource(R.drawable.bg_edit_text_selector)
        }
        binding.edtStudentId.doAfterTextChanged {
            binding.edtStudentId.error = null
            binding.edtStudentId.setBackgroundResource(R.drawable.bg_edit_text_selector)
        }
        binding.edtClass.doAfterTextChanged {
            binding.edtClass.error = null
            binding.edtClass.setBackgroundResource(R.drawable.bg_edit_text_selector)
        }

        // Bàn phím: Khi ấn nút Done trên bàn phím tại trường Lớp -> Tự động kích hoạt nút Đăng ký
        binding.edtClass.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                binding.btnRegister.performClick()
                true
            } else {
                false
            }
        }

        // Sự kiện click Button ĐĂNG KÝ
        binding.btnRegister.setOnClickListener {
            hideKeyboard()

            val fullName = binding.edtFullName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()
            val studentId = binding.edtStudentId.text.toString().trim()
            val className = binding.edtClass.text.toString().trim()

            var isValid = true

            // 1. Họ và tên: không được để trống, không được chỉ chứa khoảng trắng
            if (fullName.isBlank()) {
                binding.edtFullName.error = "Vui lòng nhập họ và tên"
                binding.edtFullName.setBackgroundResource(R.drawable.bg_edit_text_error)
                isValid = false
            } else {
                binding.edtFullName.error = null
                binding.edtFullName.setBackgroundResource(R.drawable.bg_edit_text_selector)
            }

            // 2. Email: không được để trống, phải đúng định dạng email
            if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.error = "Email không hợp lệ"
                binding.edtEmail.setBackgroundResource(R.drawable.bg_edit_text_error)
                isValid = false
            } else {
                binding.edtEmail.error = null
                binding.edtEmail.setBackgroundResource(R.drawable.bg_edit_text_selector)
            }

            // 3. Số điện thoại: không được để trống, chỉ được nhập số, phải có đúng 10 chữ số, phải bắt đầu bằng số 0
            if (phone.isBlank() || !phone.matches(Regex("^0[0-9]{9}$"))) {
                binding.edtPhone.error = "Số điện thoại không hợp lệ"
                binding.edtPhone.setBackgroundResource(R.drawable.bg_edit_text_error)
                isValid = false
            } else {
                binding.edtPhone.error = null
                binding.edtPhone.setBackgroundResource(R.drawable.bg_edit_text_selector)
            }

            // 4. Mã sinh viên (MSV): không được để trống, không được chỉ chứa khoảng trắng
            if (studentId.isBlank()) {
                binding.edtStudentId.error = "Vui lòng nhập mã sinh viên"
                binding.edtStudentId.setBackgroundResource(R.drawable.bg_edit_text_error)
                isValid = false
            } else {
                binding.edtStudentId.error = null
                binding.edtStudentId.setBackgroundResource(R.drawable.bg_edit_text_selector)
            }

            // 5. Lớp: không được để trống, không được chỉ chứa khoảng trắng
            if (className.isBlank()) {
                binding.edtClass.error = "Vui lòng nhập lớp"
                binding.edtClass.setBackgroundResource(R.drawable.bg_edit_text_error)
                isValid = false
            } else {
                binding.edtClass.error = null
                binding.edtClass.setBackgroundResource(R.drawable.bg_edit_text_selector)
            }

            // Nếu có ít nhất một lỗi: không chuyển màn hình
            if (!isValid) {
                return@setOnClickListener
            }

            // Nếu tất cả hợp lệ thì chuyển DetailActivity bằng Intent
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("fullName", fullName)
                putExtra("email", email)
                putExtra("phone", phone)
                putExtra("studentId", studentId)
                putExtra("className", className)
            }
            startActivity(intent)
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        currentFocus?.let {
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}

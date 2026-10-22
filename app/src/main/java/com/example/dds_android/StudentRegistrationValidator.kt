package com.example.dds_android

import java.util.regex.Pattern

object StudentRegistrationValidator {

    val EMAIL_PATTERN: Pattern = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )

    val PHONE_PATTERN: Pattern = Pattern.compile("^0[0-9]{9}$")

    fun isFullNameValid(fullName: String): Boolean {
        return fullName.isNotBlank()
    }

    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && EMAIL_PATTERN.matcher(email.trim()).matches()
    }

    fun isPhoneValid(phone: String): Boolean {
        return phone.isNotBlank() && PHONE_PATTERN.matcher(phone.trim()).matches()
    }

    fun isStudentIdValid(studentId: String): Boolean {
        return studentId.isNotBlank()
    }

    fun isClassValid(className: String): Boolean {
        return className.isNotBlank()
    }
}

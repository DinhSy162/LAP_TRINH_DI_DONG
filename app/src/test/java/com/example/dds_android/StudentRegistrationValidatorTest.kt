package com.example.dds_android

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StudentRegistrationValidatorTest {

    /**
     * CASE 1 — Bỏ trống toàn bộ:
     * Họ tên: empty, Email: empty, SĐT: empty, MSV: empty, Lớp: empty
     * Expected: Tất cả 5 trường đều không hợp lệ.
     */
    @Test
    fun case1_allEmpty_allInvalid() {
        assertFalse(StudentRegistrationValidator.isFullNameValid(""))
        assertFalse(StudentRegistrationValidator.isFullNameValid("   "))

        assertFalse(StudentRegistrationValidator.isEmailValid(""))
        assertFalse(StudentRegistrationValidator.isEmailValid("   "))

        assertFalse(StudentRegistrationValidator.isPhoneValid(""))
        assertFalse(StudentRegistrationValidator.isPhoneValid("   "))

        assertFalse(StudentRegistrationValidator.isStudentIdValid(""))
        assertFalse(StudentRegistrationValidator.isStudentIdValid("   "))

        assertFalse(StudentRegistrationValidator.isClassValid(""))
        assertFalse(StudentRegistrationValidator.isClassValid("   "))
    }

    /**
     * CASE 2 — Email sai:
     * Họ tên: Nguyễn Văn A
     * Email: abc (hoặc student, student@, @gmail.com)
     * SĐT: 0912345678
     * MSV: 12345678
     * Lớp: CNTT01
     * Expected: Email không hợp lệ.
     */
    @Test
    fun case2_invalidEmail_rejected() {
        val invalidEmails = listOf("abc", "student", "student@", "@gmail.com", "student@@gmail.com")
        for (email in invalidEmails) {
            assertFalse("Expected invalid for email: $email", StudentRegistrationValidator.isEmailValid(email))
        }

        assertTrue(StudentRegistrationValidator.isFullNameValid("Nguyễn Văn A"))
        assertTrue(StudentRegistrationValidator.isPhoneValid("0912345678"))
        assertTrue(StudentRegistrationValidator.isStudentIdValid("12345678"))
        assertTrue(StudentRegistrationValidator.isClassValid("CNTT01"))
    }

    /**
     * CASE 3 — Số điện thoại sai:
     * Họ tên: Nguyễn Văn A
     * Email: abc@gmail.com
     * SĐT: 123 (hoặc abc, 09123abc, 1234567890)
     * MSV: 12345678
     * Lớp: CNTT01
     * Expected: Số điện thoại không hợp lệ.
     */
    @Test
    fun case3_invalidPhone_rejected() {
        val invalidPhones = listOf("123", "abc", "09123abc", "1234567890", "091234567", "09123456789")
        for (phone in invalidPhones) {
            assertFalse("Expected invalid for phone: $phone", StudentRegistrationValidator.isPhoneValid(phone))
        }

        assertTrue(StudentRegistrationValidator.isFullNameValid("Nguyễn Văn A"))
        assertTrue(StudentRegistrationValidator.isEmailValid("abc@gmail.com"))
        assertTrue(StudentRegistrationValidator.isStudentIdValid("12345678"))
        assertTrue(StudentRegistrationValidator.isClassValid("CNTT01"))
    }

    /**
     * CASE 4 — MSV / Lớp bỏ trống hoặc chứa khoảng trắng:
     * Expected: Hiển thị lỗi tương ứng.
     */
    @Test
    fun case4_emptyOrInvalidStudentIdAndClass_rejected() {
        // MSV rỗng
        assertFalse(StudentRegistrationValidator.isStudentIdValid(""))
        assertFalse(StudentRegistrationValidator.isStudentIdValid("   "))

        // Lớp rỗng
        assertFalse(StudentRegistrationValidator.isClassValid(""))
        assertFalse(StudentRegistrationValidator.isClassValid("   "))

        // Giá trị hợp lệ
        assertTrue(StudentRegistrationValidator.isStudentIdValid("12345678"))
        assertTrue(StudentRegistrationValidator.isStudentIdValid("B20DCCN001"))
        assertTrue(StudentRegistrationValidator.isClassValid("CNTT01"))
        assertTrue(StudentRegistrationValidator.isClassValid("D20CQCN01-B"))
    }

    /**
     * CASE 5 — Tất cả hợp lệ:
     * Họ tên: Nguyễn Văn A
     * Email: nguyenvana@gmail.com
     * SĐT: 0912345678
     * MSV: 12345678
     * Lớp: CNTT01
     * Expected: Cả 5 trường đều hợp lệ -> Chuyển Intent sang DetailActivity.
     */
    @Test
    fun case5_allFieldsValid_accepted() {
        val fullName = "Nguyễn Văn A"
        val email = "nguyenvana@gmail.com"
        val phone = "0912345678"
        val studentId = "12345678"
        val className = "CNTT01"

        assertTrue(StudentRegistrationValidator.isFullNameValid(fullName))
        assertTrue(StudentRegistrationValidator.isEmailValid(email))
        assertTrue(StudentRegistrationValidator.isPhoneValid(phone))
        assertTrue(StudentRegistrationValidator.isStudentIdValid(studentId))
        assertTrue(StudentRegistrationValidator.isClassValid(className))
    }
}

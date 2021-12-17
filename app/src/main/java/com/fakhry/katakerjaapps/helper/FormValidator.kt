package com.fakhry.katakerjaapps.helper

import android.util.Patterns
import com.fakhry.katakerjaapps.R
import com.google.android.material.textfield.TextInputLayout

object FormValidator {
    fun TextInputLayout.editTextIsEmpty(text: String = editText?.text.toString()) =
        if (text == ""){
            error = editText?.context?.getString(R.string.error_empty_edit_text)
            editText?.requestFocus()
            false
        } else {
            error = null
            true

        }

    fun TextInputLayout.emailFormat(email: String = editText?.text.toString()) =
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = editText?.context?.getString(R.string.error_email_format)
            editText?.requestFocus()
            false
        } else {
            error = null
            true
        }

    fun TextInputLayout.passwordFormat(password: String = editText?.text.toString()) =
        if (password.length < 6) {
            error = editText?.context?.getString(R.string.error_password_format)
            editText?.requestFocus()
            false
        } else {
            error = null
            true
        }
}
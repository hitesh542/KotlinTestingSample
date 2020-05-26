package com.hb.sampleapplicationdemo.ui.login

object Validator {

    fun validateUsername(username: String): Boolean {
        return username.length > 4
    }

    fun validatePass(pass: String): Boolean {
        return pass.length > 5
    }
}
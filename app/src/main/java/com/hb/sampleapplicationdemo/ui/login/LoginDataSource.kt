package com.hb.sampleapplicationdemo.ui.login

import com.hb.sampleapplicationdemo.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

open class LoginDataSource {

   open suspend fun callLoginApi(userName: String, pass: String): LoginModel =
        withContext(Dispatchers.IO) {
            delay(5000)
            LoginModel(true)
        }
}
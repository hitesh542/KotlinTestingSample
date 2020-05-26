package com.hb.sampleapplicationdemo.ui.login

import com.hb.sampleapplicationdemo.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class LoginRepository(private val loginDataSource: LoginDataSource) {

    open suspend fun callLoginApi(userName: String, pass: String): LoginModel =
        withContext(Dispatchers.IO) {
            println("Hello I am here")

            loginDataSource.callLoginApi(userName, pass)
        }
}
package com.hb.sampleapplicationdemo.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hb.sampleapplicationdemo.ui.login.LoginRepository
import com.hb.sampleapplicationdemo.ui.login.LoginViewModel


class LoginModelFactory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}

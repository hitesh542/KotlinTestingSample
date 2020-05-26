package com.hb.sampleapplicationdemo

data class LoginModel(val isSuccess: Boolean)

data class ApiResponse<T>(val isSuccess: Boolean, val result: T?, val message: String?)

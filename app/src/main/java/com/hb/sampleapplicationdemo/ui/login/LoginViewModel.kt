package com.hb.sampleapplicationdemo.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.sampleapplicationdemo.ApiResponse
import com.hb.sampleapplicationdemo.LoginModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val actionObservable = MutableLiveData<LoginAction>()
    val loginApiObservable = MutableLiveData<ApiResponse<LoginModel>>()

    enum class LoginAction {
        WRONG_USERNAME,
        WRONG_PASS,
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        SUCCESS
    }

    /**
     * This method is used ot login with [userName] and [pass]
     *
     * @param userName: User name entered by user as String
     * @param pass: Password entered by user as String
     */
    fun login(userName: String, pass: String) {
        if (!Validator.validateUsername(userName)) {
            postActionToView(LoginAction.WRONG_USERNAME)
            return
        }
        if (!Validator.validatePass(pass)) {
            postActionToView(LoginAction.WRONG_PASS)
            return
        }

        //call api here
        postActionToView(LoginAction.SHOW_PROGRESS)
        viewModelScope.launch {
            val loginModel = loginRepository.callLoginApi(userName, pass)
            postActionToView(LoginAction.HIDE_PROGRESS)
            if (loginModel.isSuccess) {
                loginApiObservable.postValue(
                    ApiResponse(
                        isSuccess = true,
                        result = loginModel,
                        message = null
                    ))
            } else {
                loginApiObservable.postValue(
                    ApiResponse(
                        isSuccess = false,
                        result = loginModel,
                        message = null
                    )
                )
            }
        }
    }

    private fun postActionToView(action: LoginAction) {
        actionObservable.value = (action)
    }

    suspend fun testCallLogin(userName: String, pass: String) =
        loginRepository.callLoginApi(userName, pass)
}
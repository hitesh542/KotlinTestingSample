package com.hb.sampleapplicationdemo.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hb.sampleapplicationdemo.R
import com.hb.sampleapplicationdemo.core.BaseActivity
import com.hb.sampleapplicationdemo.ui.common.LoginModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel =
            ViewModelProvider(this, LoginModelFactory(LoginRepository(LoginDataSource()))).get(
                LoginViewModel::class.java
            )
        observeInputs()
    }

    private fun observeInputs() {
        viewModel.actionObservable.observe(this, Observer {
            if (it != null) {
                when (it) {
                    LoginViewModel.LoginAction.WRONG_USERNAME -> {
                        username.error = "Please enter a valid username"
                    }
                    LoginViewModel.LoginAction.WRONG_PASS -> {
                        password.error = "Please enter a valid password"
                    }
                    LoginViewModel.LoginAction.SHOW_PROGRESS -> showProgress()
                    LoginViewModel.LoginAction.HIDE_PROGRESS -> hideProgress()
                    LoginViewModel.LoginAction.SUCCESS -> {
                        //Login success
                        showError("Loggedin successfully")
                    }
                }
            }
        })

        viewModel.loginApiObservable.observe(this, Observer {
            //show error
            showError("Login error")
        })
        username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkInputs()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkInputs()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        login.setOnClickListener {
            val un = username.text.toString()
            val pass = password.text.toString()

            viewModel.login(un, pass)
        }
    }

    private fun checkInputs() {
        username.error = null
        password.error = null
        val un = username.text.toString()
        val pass = password.text.toString()
        login.isEnabled = !(un.isBlank() || pass.isBlank())
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        loading.visibility = View.GONE
    }
}
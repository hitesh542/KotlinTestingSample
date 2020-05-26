package com.hb.sampleapplicationdemo.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hb.sampleapplicationdemo.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Coroutine Google IO = https://www.youtube.com/watch?v=B8ppnjGPAGE&vl=en
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Mock
    lateinit var loginDataSource: LoginDataSource
    @Mock
    lateinit var loginRepository: LoginRepository

    @get:Rule
    var instantRule = InstantTaskExecutorRule()

    //coroutines
    private val mainThreadSurrogate = newSingleThreadContext("TestingThread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun validate_fields_empty() {
        Assert.assertEquals(false, Validator.validatePass(""))
        Assert.assertEquals(false, Validator.validateUsername(""))
    }

    @Test
    fun validate_fields_blank() {
        Assert.assertEquals(false, Validator.validatePass(" "))
        Assert.assertEquals(false, Validator.validateUsername(" "))
    }

    @Test
    fun validate_fields_short() {
        Assert.assertEquals(false, Validator.validatePass("a"))
        Assert.assertEquals(false, Validator.validateUsername("b"))
    }


    @Test
    fun validate_fields_special_characters() {
        Assert.assertEquals(false, Validator.validatePass("@@"))
        Assert.assertEquals(false, Validator.validateUsername("!!"))
    }

    @Test
    fun validate_fields_ok() {
        Assert.assertEquals(true, Validator.validatePass("Hitesh"))
        Assert.assertEquals(true, Validator.validateUsername("password"))
    }

    @Test
    fun login() {
        runBlocking {
            val username = "test123"
            val password = "password"
            val loginModel = LoginModel(isSuccess = true)

            Mockito.`when`(loginRepository.callLoginApi(username, password)).thenReturn(loginModel)
            val viewModel = LoginViewModel(loginRepository)

            viewModel.login(username, password)

            //verification
            Mockito.verify(loginRepository).callLoginApi(username, password)
            Assert.assertEquals(true, viewModel.loginApiObservable.value!!.isSuccess)
        }
    }
}
package com.hb.sampleapplicationdemo.ui.login

import com.hb.sampleapplicationdemo.LoginModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryTest {

    @Mock
    lateinit var loginDataSource: LoginDataSource
    lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRepository = Mockito.spy(LoginRepository(loginDataSource))
    }

    @Test
    fun callLoginApi() {
        runBlocking {
            //Mockito.`when`(loginDataSource.callLoginApi(Mockito.anyString(), Mockito.anyString())).thenReturn(LoginModel(isSuccess = true))
            Mockito.doReturn(LoginModel(isSuccess = true))
                .`when`(loginRepository).callLoginApi(Mockito.anyString(), Mockito.anyString())

            Assert.assertEquals(
                true,
                loginRepository.callLoginApi(Mockito.anyString(), Mockito.anyString()).isSuccess
            )
        }
    }
}
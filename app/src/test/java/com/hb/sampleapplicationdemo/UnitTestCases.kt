package com.hb.sampleapplicationdemo

import com.hb.sampleapplicationdemo.ui.login.LoginRepositoryTest
import com.hb.sampleapplicationdemo.ui.login.LoginViewModelTest
import org.junit.FixMethodOrder
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.junit.runners.Suite

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Suite::class)
@Suite.SuiteClasses(LoginViewModelTest::class, LoginRepositoryTest::class)
class UnitTestCases
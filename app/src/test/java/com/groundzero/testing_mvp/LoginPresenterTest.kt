package com.groundzero.testing_mvp

import com.groundzero.testing_mvp.data.login.Credential
import com.groundzero.testing_mvp.data.login.LoginRepository
import com.groundzero.testing_mvp.data.login.ValidationService
import com.groundzero.testing_mvp.ui.authentication.LoginContract
import com.groundzero.testing_mvp.ui.authentication.LoginPresenter
import com.groundzero.testing_mvp.utils.LOGIN_ERROR_MESSAGE
import com.groundzero.testing_mvp.utils.WELCOME_MESSAGE
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    private lateinit var loginView: LoginContract.LoginView

    private val loginRepository = LoginRepository()
    private val validationService = ValidationService(loginRepository)
    private lateinit var loginPresenter: LoginPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginPresenter = LoginPresenter(loginView, validationService)
        loginRepository.addCredentials(Credential(VALID_USERNAME, VALID_PASSWORD))
    }

    @Test
    fun test_on_view_create_should_show_toast() {
        loginPresenter.onViewCreated()
        verify(loginView).showToastMessage(WELCOME_MESSAGE)
    }

    @Test
    fun test_on_login_should_return_success() {
        loginPresenter.onLogin(VALID_USERNAME, VALID_PASSWORD)
        verify(loginView).proceedToNextFragment()
    }

    @Test
    fun test_on_login_should_return_false() {
        loginPresenter.onLogin(INVALID_USERNAME, INVALID_PASSWORD)
        verify(loginView).showToastMessage(LOGIN_ERROR_MESSAGE)
    }

    companion object {
        private const val VALID_USERNAME = "John"
        private const val VALID_PASSWORD = "J31"
        private const val INVALID_USERNAME = "Michael"
        private const val INVALID_PASSWORD = "aBceE"
    }
}
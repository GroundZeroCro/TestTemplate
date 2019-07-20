package com.groundzero.testing_mvp.ui.authentication

import com.groundzero.testing_mvp.data.login.Credential
import com.groundzero.testing_mvp.data.login.ValidationService
import com.groundzero.testing_mvp.utils.LOGIN_ERROR_MESSAGE
import com.groundzero.testing_mvp.utils.WELCOME_MESSAGE

class LoginPresenter(private val view: LoginContract.LoginView, private val validationService: ValidationService) :
    LoginContract.LoginPresenter {

    override fun onViewCreated() {
        view.showToastMessage(WELCOME_MESSAGE)
    }

    override fun onLogin(username: String, password: String) {
        if (validationService.areCredentialsValid(Credential(username, password))) {
            onLoginSuccess()
        } else {
            onLoginError()
        }
    }

    private fun onLoginSuccess() {
        view.proceedToNextFragment()
    }

    private fun onLoginError() {
        view.showToastMessage(LOGIN_ERROR_MESSAGE)
    }
}
package com.groundzero.testing_mvp.ui.authentication

import com.groundzero.testing_mvp.data.login.Credential
import com.groundzero.testing_mvp.data.login.ValidationService

class LoginPresenter(private val view: LoginContract.LoginView, private val validationService: ValidationService) :
    LoginContract.LoginPresenter {

    override fun onViewCreated() {
        view.showToastMessage("Welcome")
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
        view.showToastMessage("Entering the application.")
    }

    private fun onLoginError() {
        view.showToastMessage("Your credentials are wrong. Please try again!")
    }
}
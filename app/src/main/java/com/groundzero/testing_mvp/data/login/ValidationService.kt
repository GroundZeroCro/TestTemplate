package com.groundzero.testing_mvp.data.login

class ValidationService(private val loginRepository: LoginRepository) {

    fun areCredentialsValid(credential: Credential): Boolean {
        return if (isLoginInputCorrect(credential)) {
            loginRepository.getCredentials().contains(credential)
        } else {
            false
        }
    }

    private fun isLoginInputCorrect(credential: Credential): Boolean {
        return !credential.username.isBlank() || !credential.password.isBlank()
    }
}
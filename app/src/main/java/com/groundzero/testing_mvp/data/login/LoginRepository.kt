package com.groundzero.testing_mvp.data.login

class LoginRepository {

    private val credentials = mutableListOf<Credential>()

    init {
        credentials.add(Credential("d", "d"))
        credentials.add(Credential("Anna", "annasPassword"))
        credentials.add(Credential("John", "31at"))
    }

    fun getCredentials(): List<Credential> {
        return credentials
    }
}
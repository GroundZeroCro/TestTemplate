package com.groundzero.testing_mvp.ui.authentication

import com.groundzero.testing_mvp.base.BaseView

interface LoginContract {
    interface LoginPresenter {
        fun onViewCreated()
        fun onLogin(username: String, password: String)
    }

    interface LoginView : BaseView {
        fun proceedToNextFragment()
    }
}
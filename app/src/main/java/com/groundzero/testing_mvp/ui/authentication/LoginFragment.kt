package com.groundzero.testing_mvp.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.groundzero.testing_mvp.data.login.LoginRepository
import com.groundzero.testing_mvp.data.login.ValidationService
import com.groundzero.testing_mvp.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), LoginContract.LoginView {

    private lateinit var presenter: LoginContract.LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = LoginPresenter(this,
            ValidationService(LoginRepository())
        )
        presenter.onViewCreated()
        login_button.setOnClickListener { presenter.onLogin(username.text.toString(), password.text.toString()) }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun proceedToNextFragment() {
        this.findNavController().navigate(R.id.postFragment)
    }
}
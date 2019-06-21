package com.app.neosoft.login.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.neosoft.contract.IView
import com.app.neosoft.contract.IViewModel
import com.app.neosoft.R
import com.app.neosoft.databinding.ActivityLoginNewBinding
import com.app.neosoft.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), IView {


    private val loginViewModel by lazy {
        LoginViewModel()
    }

    lateinit var activityLoginBinding: ActivityLoginNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView<ActivityLoginNewBinding>(
            this,
            R.layout.activity_login_new
        )
        activityLoginBinding.viewmodel = this.loginViewModel


        loginViewModel.ioStatus.observe(this, Observer { ioStatus ->
            ioStatus?.action(this@LoginActivity);

        })

    }


    override fun getAppCompactActivity(): AppCompatActivity {
        return this
    }

    override fun getViewModel(): IViewModel {
        return loginViewModel
    }

}
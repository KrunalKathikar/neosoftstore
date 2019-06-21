package com.app.neosoft.login.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import com.android.databinding.library.baseAdapters.BR
import com.app.neosoft.contract.IViewModel
import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.login.httpcallmanagement.LoginNetworkIOImpl
import com.app.neosoft.entity.LoginRequest

class LoginViewModel : BaseObservable(), IViewModel {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    @Bindable
    var message : String = ""

    private var loginNetworkIOImpl = LoginNetworkIOImpl();

    val ioStatus = MutableLiveData<INetworkIOStatus>();

    fun login(){
        if(TextUtils.isEmpty(email.value)){
            message("Please Enter Valid Credentials")
            return
        }
        if(TextUtils.isEmpty(password.value)) {
            message("Please Enter Valid Credentials")
            return
        }
        else{
            loginNetworkIOImpl.request(
                LoginRequest(
                    email.value.toString(),
                    password.value.toString()
                ),ioStatus)
        }
    }

    override fun message(string : String){
        message = string
        notifyPropertyChanged(BR.message)
    }

}
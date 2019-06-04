package com.app.neosoft.mvvm

import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.contract.IView
import com.app.neosoft.ProgressDialogFragment
import com.app.neosoft.entity.LoginResponse
import retrofit2.Call

class FailNetworkIOStatus : INetworkIOStatus {

    val call: Call<LoginResponse>
    val t: Throwable

    constructor(call: Call<LoginResponse>, t: Throwable){
        this.call = call
        this.t = t
    }

    override fun action(view: IView) {
        ProgressDialogFragment.dismiss(view.getAppCompactActivity())
        view.getViewModel().message("Please Enter Valid Credentials")
    }
}
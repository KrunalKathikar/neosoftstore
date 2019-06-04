package com.app.neosoft.NetworkIOStatus

import com.app.neosoft.contract.IView
import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.ProgressDialogFragment
import com.app.neosoft.entity.LoginResponse
import retrofit2.Call
import retrofit2.Response

class SuccessNetworkIOStatus  : INetworkIOStatus {

    val call: Call<LoginResponse>

    val response: Response<LoginResponse>

    constructor(call: Call<LoginResponse>,
        response: Response<LoginResponse>) {
        this.call = call
        this.response = response
    }

    override fun action(view: IView) {
        ProgressDialogFragment.dismiss(view.getAppCompactActivity())
        when(response.code()){
            401 -> view.getViewModel().message(response.message())
            200 -> ""
        }
    }

}
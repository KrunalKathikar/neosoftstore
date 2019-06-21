package com.app.neosoft.NetworkIOStatus

import com.app.neosoft.ProgressDialogFragment
import com.app.neosoft.contract.IView
import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.entity.LoginResponse

class SuccessNetworkIOStatus : INetworkIOStatus {

    var response : LoginResponse? = null;

    var code: Int = 0;

    var message : String = "";

    constructor()

    constructor(code: Int, message: String, response: LoginResponse?){
        this.code = code
        this.message = message
        this.response = response
    }


    override fun action(view: IView) {
        ProgressDialogFragment.dismiss(view.getAppCompactActivity())
        when(code){
            401 -> view.getViewModel().message("Enter Valid Credentials.")
            200 -> ""
        }
    }

}
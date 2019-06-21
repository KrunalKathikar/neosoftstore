package com.app.neosoft.mvvm

import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.contract.IView
import com.app.neosoft.ProgressDialogFragment

class FailNetworkIOStatus : INetworkIOStatus {

    val error: String

    constructor(error: String){
        this.error = error
    }

    override fun action(view: IView) {
        ProgressDialogFragment.dismiss(view.getAppCompactActivity())
        view.getViewModel().message(error.toString())
    }
}
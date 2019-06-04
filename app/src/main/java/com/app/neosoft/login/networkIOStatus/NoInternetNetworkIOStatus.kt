package com.app.neosoft.NetworkIOStatus

import com.app.neosoft.contract.IView
import com.app.neosoft.contract.INetworkIOStatus

class NoInternetNetworkIOStatus : INetworkIOStatus {
    override fun action(view: IView) {
        view.getViewModel().message("Please Check Internet Connection")
    }
}
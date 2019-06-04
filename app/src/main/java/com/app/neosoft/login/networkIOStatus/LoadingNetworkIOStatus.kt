package com.app.neosoft.mvvm

import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.contract.IView
import com.app.neosoft.ProgressDialogFragment


class LoadingNetworkIOStatus : INetworkIOStatus {

    override fun action(view: IView) {
        ProgressDialogFragment.show(view.getAppCompactActivity())
    }

}
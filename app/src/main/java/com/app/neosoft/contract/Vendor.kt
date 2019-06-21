package com.app.neosoft.contract

import android.arch.lifecycle.MutableLiveData

interface Vendor<RequestObj> {
    fun execute(
        requestObj: RequestObj,
        ioStatus: MutableLiveData<INetworkIOStatus>
    )
}
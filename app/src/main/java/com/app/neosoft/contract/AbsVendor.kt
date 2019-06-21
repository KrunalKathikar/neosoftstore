package com.app.neosoft.contract

import android.arch.lifecycle.MutableLiveData

abstract class AbsVendor<RequestObj> {

    fun execute(requestObj: RequestObj, ioStatus: MutableLiveData<INetworkIOStatus>){

        var loadingNetworkIOStatus = provideLoadingNetworkIoStatus()

        if(loadingNetworkIOStatus!=null){

            ioStatus.postValue(loadingNetworkIOStatus)

        }

        performNetworkCall(requestObj, ioStatus)

    }

    abstract fun provideLoadingNetworkIoStatus() : INetworkIOStatus

    abstract fun performNetworkCall(requestObj: RequestObj, ioStatus: MutableLiveData<INetworkIOStatus>)

}
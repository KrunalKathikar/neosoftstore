package com.app.neosoft.contract

import android.arch.lifecycle.MutableLiveData
import com.app.neosoft.utility.InternetManager

abstract class AbsNetworkIO<RequestObj,ResponseObj> {

    fun request(requestObj : RequestObj,ioStatus : MutableLiveData<INetworkIOStatus>){

        val im : InternetManager = provideInternetManager()

        if(im!=null && !im.isConnected()){

            var noInternetNetworkIOStatus =provideNoInternetIOStatus()

            if(noInternetNetworkIOStatus!=null && ioStatus!=null){

                ioStatus.postValue(noInternetNetworkIOStatus)

                return

            }
        }

        var vendor = provideHttpVendor()

        if (vendor!=null){

            vendor.execute(requestObj,ioStatus)

        }

    }

    abstract protected fun provideNoInternetIOStatus() : INetworkIOStatus

    abstract protected fun provideHttpVendor() : AbsVendor<RequestObj>

    abstract protected fun provideInternetManager() : InternetManager

}
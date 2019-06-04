package com.app.neosoft.login.httpcall

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.ConnectivityManager
import com.app.neosoft.AndroidApplication
import com.app.neosoft.contract.INetworkIOStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class AbsNetworkIO<RequestObj,ResponseObj> {

    fun request(requestObj : RequestObj,ioStatus : MutableLiveData<INetworkIOStatus>){

        val connectivityManager = AndroidApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        val activeNetworkInfo = connectivityManager?.activeNetworkInfo

        if(!(activeNetworkInfo != null && activeNetworkInfo.isConnected)){

            var noInternetNetworkIOStatus =provideNoInternetIOStatus()

            if(noInternetNetworkIOStatus!=null && ioStatus!=null){

                ioStatus.postValue(noInternetNetworkIOStatus)

                return

            }
        }

        var loadingNetworkIOStatus =provideLoadingNetworkIOStatus()

        if(loadingNetworkIOStatus!=null && ioStatus!=null){

            ioStatus.postValue(loadingNetworkIOStatus)

        }

        var call = provideHttpCall(requestObj);

        if(call!=null) {

            call.enqueue(object : Callback<ResponseObj> {

                override fun onFailure(call: Call<ResponseObj>, t: Throwable) {

                    var failNetworkIOStatus = provideFailNetWorkIOStatus(call,t)

                    if (failNetworkIOStatus != null && ioStatus != null) {

                        ioStatus.postValue(failNetworkIOStatus)

                    }

                }

                override fun onResponse(call: Call<ResponseObj>, response: Response<ResponseObj>) {

                    var sucessNetworkIOStatus = provideSuccessNetWorkIOStatus(call,response)

                    if (sucessNetworkIOStatus != null && ioStatus != null) {

                        ioStatus.postValue(sucessNetworkIOStatus)

                    }
                }
            })

        }

    }

    abstract protected fun provideNoInternetIOStatus() : INetworkIOStatus

    abstract protected fun provideLoadingNetworkIOStatus() : INetworkIOStatus

    abstract protected fun provideSuccessNetWorkIOStatus(
        call: Call<ResponseObj>,
        response: Response<ResponseObj>
    ): INetworkIOStatus

    abstract protected fun provideFailNetWorkIOStatus(call: Call<ResponseObj>, t: Throwable): INetworkIOStatus

    abstract protected fun provideHttpCall(requestObj : RequestObj) : Call<ResponseObj>

    //abstract protected fun onFailureHttpCall(call: Call<ResponseObj>, t: Throwable)

    //abstract protected fun onResponseCall(call: Call<ResponseObj>, response: Response<ResponseObj>)

}
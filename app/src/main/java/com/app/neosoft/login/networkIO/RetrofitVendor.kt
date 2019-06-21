package com.app.neosoft.login.httpcallmanagement

import android.arch.lifecycle.MutableLiveData
import com.app.neosoft.NetworkIOStatus.SuccessNetworkIOStatus
import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.entity.LoginRequest
import com.app.neosoft.entity.LoginResponse
import com.app.neosoft.contract.INetworkService
import com.app.neosoft.contract.AbsVendor
import com.app.neosoft.mvvm.FailNetworkIOStatus
import com.app.neosoft.mvvm.LoadingNetworkIOStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitVendor : AbsVendor<LoginRequest>() {

    private val loginUrl: String = "http://staging.php-dev.in:8844/trainingapp/api/users/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
    }

    override fun provideLoadingNetworkIoStatus(): INetworkIOStatus {
        return LoadingNetworkIOStatus()
    }

    override fun performNetworkCall(requestObj: LoginRequest, ioStatus: MutableLiveData<INetworkIOStatus>) {

        val retrofit = retrofitBuilder.baseUrl(loginUrl).addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<INetworkService>(INetworkService::class.java)

        service.updateUser(requestObj.email, requestObj.password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    ioStatus.postValue(FailNetworkIOStatus(t.message.toString()))
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                    ioStatus.postValue(SuccessNetworkIOStatus(response.code(), response.message(), response.body()))

                }

            })

    }

}
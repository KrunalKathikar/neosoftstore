package com.app.neosoft.login.httpcall

import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.NetworkIOStatus.*
import com.app.neosoft.entity.LoginRequest
import com.app.neosoft.entity.LoginResponse
import com.app.neosoft.login.networkCallContract.INetworkLoginService
import com.app.neosoft.mvvm.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginNetworkIOImpl : AbsNetworkIO<LoginRequest, LoginResponse>() {


    private val loginUrl : String = "http://staging.php-dev.in:8844/trainingapp/api/users/"

    private val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
    }

    override fun provideNoInternetIOStatus(): INetworkIOStatus {
        return NoInternetNetworkIOStatus()
    }

    override fun provideLoadingNetworkIOStatus(): INetworkIOStatus {
        return LoadingNetworkIOStatus()
    }

    override fun provideSuccessNetWorkIOStatus(call: Call<LoginResponse>, response: Response<LoginResponse>): INetworkIOStatus {
        return SuccessNetworkIOStatus(call,response)
    }

    override fun provideFailNetWorkIOStatus(call: Call<LoginResponse>, t: Throwable): INetworkIOStatus {
        return FailNetworkIOStatus(call,t)
    }

    override fun provideHttpCall(requestObj: LoginRequest): Call<LoginResponse> {

        val retrofit = retrofitBuilder.
            baseUrl(loginUrl).
            addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<INetworkLoginService>(INetworkLoginService::class.java)

        return service.updateUser(requestObj.email,requestObj.password)

    }

}
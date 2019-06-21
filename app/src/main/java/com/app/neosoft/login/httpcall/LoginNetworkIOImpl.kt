package com.app.neosoft.login.httpcall

import com.app.neosoft.contract.INetworkIOStatus
import com.app.neosoft.NetworkIOStatus.*
import com.app.neosoft.contract.AbsNetworkIO
import com.app.neosoft.entity.LoginRequest
import com.app.neosoft.entity.LoginResponse
import com.app.neosoft.utility.InternetManager
import com.app.neosoft.contract.Vendor

class LoginNetworkIOImpl : AbsNetworkIO<LoginRequest, LoginResponse>() {

    override fun provideNoInternetIOStatus(): INetworkIOStatus {
        return NoInternetNetworkIOStatus()
    }

    override fun provideHttpVendor(): Vendor<LoginRequest> {
        return RetrofitVendor()
    }

    override fun provideInternetManager(): InternetManager {
        return InternetManager()
    }
}
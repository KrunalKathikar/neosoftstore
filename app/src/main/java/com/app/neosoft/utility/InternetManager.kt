package com.app.neosoft.utility

import android.content.Context
import android.net.ConnectivityManager
import com.app.neosoft.AndroidApplication

class InternetManager {

    fun isConnected() : Boolean {

        val connectivityManager : ConnectivityManager = AndroidApplication.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var activeNetworkInfo = connectivityManager.activeNetworkInfo

        if(activeNetworkInfo == null){

            return false

        }else{

            return activeNetworkInfo.isConnected

        }

    }

}
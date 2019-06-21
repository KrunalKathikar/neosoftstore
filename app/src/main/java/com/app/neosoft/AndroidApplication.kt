package com.app.neosoft

import android.app.Application
import android.content.Context

class AndroidApplication : Application() {

    companion object{
        var context : Context? = null
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}
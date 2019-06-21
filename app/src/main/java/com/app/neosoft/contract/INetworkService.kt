package com.app.neosoft.contract

import com.app.neosoft.entity.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface INetworkService {

    @FormUrlEncoded
    @POST("login")
    fun updateUser(@Field("email") email: String,
                   @Field("password") password: String): Call<LoginResponse>



}
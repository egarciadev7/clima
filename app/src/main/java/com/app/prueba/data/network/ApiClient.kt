package com.app.prueba.data.network

import com.app.prueba.constants.Constants
import com.app.prueba.data.network.interfaces.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var SERVAPI: ApiInterface? = null

    fun getServApi():ApiInterface? {
        val urlBase = Constants.API_URL
        if (SERVAPI == null) {
            val retr = Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient())
                .build()
            SERVAPI = retr.create(ApiInterface::class.java)
        }
        return SERVAPI
    }

    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}
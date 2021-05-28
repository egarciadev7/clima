package com.app.prueba.data.network.interfaces

import com.app.prueba.ui.model.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("location/search/")
    fun getAllRecipes(@Query("query") term:String): Call<List<Location>>

    @GET("location/{woeid}")
    fun getLocationDetail(@Path("woeid") woeid:String): Call<Location>
}
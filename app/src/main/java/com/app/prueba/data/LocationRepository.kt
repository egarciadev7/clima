package com.app.prueba.data

import com.app.prueba.constants.HttpCodes
import com.app.prueba.data.network.ApiClient
import com.app.prueba.data.network.ResponseWrapper
import com.app.prueba.helpers.ErrorMessage
import kotlinx.coroutines.*
import retrofit2.awaitResponse


class LocationRepository(){

    private val retrofit = ApiClient.getServApi()

    suspend  fun searchLocations(term: String): ResponseWrapper = withContext(Dispatchers.IO){

        val wrapper = ResponseWrapper()

        try {
            val response = retrofit?.getAllRecipes(term)!!.awaitResponse()

            if(response.isSuccessful) {
                wrapper.locations = response.body()!!
            } else {
                wrapper.error = ErrorMessage("ha ocurrido un error", HttpCodes.CODE_500)
            }

        } catch (e: Exception) {
            wrapper.error = ErrorMessage("ha ocurrido un error", HttpCodes.CODE_500)
        }


        wrapper

    }

    suspend  fun getLocationDetail(woeid: String): ResponseWrapper = withContext(Dispatchers.IO){

        val wrapper = ResponseWrapper()

        try {
            val response = retrofit?.getLocationDetail(woeid)!!.awaitResponse()

            if(response.isSuccessful) {
                wrapper.location = response.body()!!
            } else {
                wrapper.error = ErrorMessage("ha ocurrido un error", HttpCodes.CODE_500)
            }

        } catch (e: Exception) {
            wrapper.error = ErrorMessage("ha ocurrido un error", HttpCodes.CODE_500)
        }


        wrapper

    }



}
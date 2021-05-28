package com.app.prueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.prueba.domain.LocationsUseCase
import com.app.prueba.helpers.ErrorMessage
import com.app.prueba.helpers.SingleLiveEvent
import com.app.prueba.ui.model.ConsolidatedWeather
import com.app.prueba.ui.model.Location
import kotlinx.coroutines.launch


class LocationDetailViewModel(application: Application): AndroidViewModel(application) {

    private val locationsUseCase = LocationsUseCase()
    private val locationResponse =  SingleLiveEvent<Location>()
    private val locationResponseError = SingleLiveEvent<ErrorMessage>()
    private val showProgress =  SingleLiveEvent<Boolean>()
    private var currentWeather: ConsolidatedWeather? = null

    fun getLocationsByTerm(woeid: String) {

        onShowProgress(true)

        viewModelScope.launch {
            val response = locationsUseCase.getLocationDetail(woeid)
            onShowProgress(false)

            if(response.error == null) {

                response.location?.let {
                    setCurrentWeather(it)
                    locationResponse.value = it
                }

            } else {
                locationResponseError.value = response.error
            }
        }
    }

    fun getLocationResponse(): SingleLiveEvent<Location> {
        return locationResponse
    }

    fun getLocationErrorResponse(): SingleLiveEvent<ErrorMessage> {
        return locationResponseError
    }

    fun getShowLoader(): SingleLiveEvent<Boolean> {
        return showProgress
    }

    private fun onShowProgress(show:Boolean) {
        showProgress.value = show
    }

    fun getConsolidatedWeather() : ArrayList<ConsolidatedWeather>? {

        val weatherList = locationResponse.value?.consolidatedWeather

        weatherList?.removeAt(0)

        return locationResponse.value?.consolidatedWeather
    }

    private fun setCurrentWeather(location:Location)  {
        currentWeather =  location.consolidatedWeather?.get(0)
    }

    fun getCurrentWeather() : ConsolidatedWeather? {
        return currentWeather
    }
}
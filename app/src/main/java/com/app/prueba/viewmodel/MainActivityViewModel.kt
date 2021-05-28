package com.app.prueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.prueba.domain.LocationsUseCase
import com.app.prueba.helpers.ErrorMessage
import com.app.prueba.helpers.SingleLiveEvent
import com.app.prueba.ui.model.Location
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val locationsUseCase = LocationsUseCase()
    private val locationResponse =  SingleLiveEvent<List<Location>>()
    private val locationResponseError = SingleLiveEvent<ErrorMessage>()
    private val showProgress =  SingleLiveEvent<Boolean>()

    fun getLocationsByTerm(term: String) {

        onShowProgress(true)

        viewModelScope.launch {
            val response = locationsUseCase.searchLocations(term)
            onShowProgress(false)

            if(response.error == null) {
                locationResponse.value = response.locations
            } else {
                locationResponseError.value = response.error
            }
        }
    }

    fun getLocationsResponse(): SingleLiveEvent<List<Location>> {
        return locationResponse
    }

    fun getLocationsErrorResponse(): SingleLiveEvent<ErrorMessage> {
        return locationResponseError
    }

    fun getShowLoader(): SingleLiveEvent<Boolean> {
        return showProgress
    }

    private fun onShowProgress(show:Boolean) {
        showProgress.value = show
    }
}
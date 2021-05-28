package com.app.prueba.domain

import com.app.prueba.data.LocationRepository
import com.app.prueba.data.network.ResponseWrapper

class LocationsUseCase() {

    private val locationRepository = LocationRepository()

    suspend fun searchLocations(term: String): ResponseWrapper {
        return  locationRepository.searchLocations(term)
    }

    suspend fun getLocationDetail(woeid: String): ResponseWrapper {
        return locationRepository.getLocationDetail(woeid)
    }

}
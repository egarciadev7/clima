package com.app.prueba

import com.app.prueba.data.network.ResponseWrapper
import com.app.prueba.domain.LocationsUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LocationsUseCaseTest {

    val locationUseCase =  LocationsUseCase()

    @Test
    fun shouldSearchLocation() = runBlocking {
        val response = locationUseCase.searchLocations("San")
        assertThat(response.locations).isNotNull()
    }

    @Test
    fun shouldNotSearchLocation() = runBlocking {
        val response: ResponseWrapper = locationUseCase.searchLocations("")
        assertThat(response.error).isNotNull()
    }

    @Test
    fun shouldGetLocationDetail() = runBlocking {
        val woied = "2487956"
        val response: ResponseWrapper = locationUseCase.getLocationDetail(woied)
        assertThat(response.location?.woeid).isEqualTo(woied)
    }

    @Test
    fun shouldNotGetLocationDetail() = runBlocking {
        val woied = "unwoiednoexistente"
        val response: ResponseWrapper = locationUseCase.getLocationDetail(woied)
        assertThat(response.location).isNull()
    }
}
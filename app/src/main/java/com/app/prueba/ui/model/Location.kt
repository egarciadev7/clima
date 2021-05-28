package com.app.prueba.ui.model

import com.google.gson.annotations.SerializedName

class Location() {

    @SerializedName("location_type")
    val locationType:String? = ""
    val title: String = ""
    val woeid:String? = ""

    @SerializedName("consolidated_weather")
    val consolidatedWeather: ArrayList<ConsolidatedWeather>? = null
}
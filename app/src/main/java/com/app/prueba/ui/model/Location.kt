package com.app.prueba.ui.model

import com.google.gson.annotations.SerializedName

class Location(val title: String = "", val woeid:String? = "") {

    @SerializedName("location_type")
    val locationType:String? = ""

    @SerializedName("consolidated_weather")
    val consolidatedWeather: ArrayList<ConsolidatedWeather>? = null
}
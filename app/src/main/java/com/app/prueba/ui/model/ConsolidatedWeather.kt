package com.app.prueba.ui.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.app.prueba.BR

class ConsolidatedWeather: BaseObservable() {

    @SerializedName("weather_state_name")
    @Bindable
    var weatherStateName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weatherStateName)
        }

    @SerializedName("the_temp")
    @Bindable
    var theTemp: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.theTemp)
        }

    @SerializedName("applicable_date")
    val applicableDate: String? = ""

    @SerializedName("min_temp")
    val minTemp:Double = 0.0

    @SerializedName("max_temp")
    val maxTemp:Double = 0.0

    @SerializedName("weather_state_abbr")
    val weahterStateAbbr: String? = ""

}
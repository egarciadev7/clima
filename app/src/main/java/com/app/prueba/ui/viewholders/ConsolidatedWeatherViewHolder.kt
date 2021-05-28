package com.app.prueba.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.consolidated_weather_item.view.*


class ConsolidatedWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txtState: TextView
    var txtDate: TextView
    var temp: TextView
    var container: ConstraintLayout
    var weatherIcon: ImageView

    init {
        txtState = itemView.txtStateName
        txtDate = itemView.txtDate
        container = itemView.container
        weatherIcon = itemView.weatherIcon
        temp = itemView.temp
    }
}
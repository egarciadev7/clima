package com.app.prueba.ui.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.prueba.R
import com.app.prueba.constants.Constants
import com.app.prueba.ui.model.ConsolidatedWeather
import com.app.prueba.ui.viewholders.ConsolidatedWeatherViewHolder
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class ConsolidatedWeatherAdapter(context:Context) : RecyclerView.Adapter<ConsolidatedWeatherViewHolder>() {

    private var listener: ConsolidatedWeatherAdapterListener? = null
    private var weatherList = emptyList<ConsolidatedWeather>()
    private var context:Context = context

    interface ConsolidatedWeatherAdapterListener {
        fun onWeatherTap(weather: ConsolidatedWeather)
    }

    fun setListener(listener: ConsolidatedWeatherAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsolidatedWeatherViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.consolidated_weather_item, parent, false)

        return ConsolidatedWeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConsolidatedWeatherViewHolder, position: Int) {
        val weather: ConsolidatedWeather = weatherList[position]

        holder.txtDate.text = weather.applicableDate
        holder.txtState.text = weather.weatherStateName
        holder.temp.text = "${String.format("%.1f",weather.theTemp)}  °C"

        holder.container.setOnClickListener(View.OnClickListener {
            listener?.let {
                it.onWeatherTap(weather)
            }
        })

        GlideToVectorYou
            .init()
            .with(context)
            .load(Uri.parse(Constants.STATIC_URL + weather.weahterStateAbbr + ".svg"), holder.weatherIcon)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    internal fun setWeatherList(weatherList: List<ConsolidatedWeather>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }
}
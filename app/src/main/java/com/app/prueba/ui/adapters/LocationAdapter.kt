package com.app.prueba.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.prueba.R
import com.app.prueba.ui.model.Location
import com.app.prueba.ui.viewholders.LocationsViewHolder


class LocationAdapter : RecyclerView.Adapter<LocationsViewHolder>() {

    private var listener: LocationAdapterListener? = null
    private var locations = emptyList<Location>()

    interface LocationAdapterListener {
        fun onLocationTap(location: Location)
    }

    fun setListener(listener: LocationAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.dir_item, parent, false)

        return LocationsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location: Location = locations[position]

        holder.txtTitle.text = location.title
        holder.txtLocationType.text = location.locationType

        holder.container.setOnClickListener(View.OnClickListener {
            listener?.let {
                it.onLocationTap(location)
            }
        })

    }

    override fun getItemCount(): Int {
        return locations.size
    }

    internal fun setLocations(locations: List<Location>) {

        this.locations = locations
        notifyDataSetChanged()
    }
}
package com.app.prueba.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dir_item.view.*


class LocationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txtLocationType: TextView
    var txtTitle: TextView
    var container: ConstraintLayout

    init {
        txtLocationType = itemView.txtLocationType
        txtTitle = itemView.txtTitle
        container = itemView.container
    }
}
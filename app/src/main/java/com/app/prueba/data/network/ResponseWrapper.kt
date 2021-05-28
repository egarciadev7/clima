package com.app.prueba.data.network

import com.app.prueba.helpers.ErrorMessage
import com.app.prueba.ui.model.Location

class ResponseWrapper {
    var error:ErrorMessage? = null
    var locations: List<Location>? = null
    var location:Location? = null
}
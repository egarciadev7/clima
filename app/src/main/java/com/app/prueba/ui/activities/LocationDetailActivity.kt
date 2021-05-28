package com.app.prueba.ui.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.prueba.R
import com.app.prueba.constants.Constants
import com.app.prueba.databinding.ActivityLocationDetailBinding
import com.app.prueba.ui.adapters.ConsolidatedWeatherAdapter
import com.app.prueba.viewmodel.LocationDetailViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_location_detail.*


class LocationDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: LocationDetailViewModel
    lateinit var binding: ActivityLocationDetailBinding
    lateinit var adapter: ConsolidatedWeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupObservers()
        fetchData()
    }

    private fun setupView() {
        viewModel = ViewModelProvider(this).get(LocationDetailViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityLocationDetailBinding>(
            this,
            R.layout.activity_location_detail
        ).apply {
            this.lifecycleOwner = this@LocationDetailActivity
        }

        adapter = ConsolidatedWeatherAdapter(this)
        weatherList.layoutManager = LinearLayoutManager(this)
        weatherList.adapter = adapter

    }

    private fun setupObservers() {

        viewModel.getLocationResponse().observe(this, {

            viewModel.getConsolidatedWeather()?.let { weatherList ->

                if(weatherList.isNotEmpty()) {
                    binding.viewmodel = viewModel
                    adapter.setWeatherList(weatherList)
                    setupCurrentLocation()
                }
            }
        })

        viewModel.getLocationErrorResponse().observe(this, {
            Toast.makeText(this, resources.getString(R.string.ocurrio_un_error), Toast.LENGTH_LONG).show()
        })

        viewModel.getShowLoader().observe(this, {
            showLoader(it)
        })
    }

    private fun setupCurrentLocation() {
        GlideToVectorYou
            .init()
            .with(this)
            .load(Uri.parse(Constants.STATIC_URL + viewModel.getCurrentWeather()?.weahterStateAbbr + ".svg"), currentWeatherIcon);
    }

    private fun showLoader(show:Boolean) {
        if(show) {
            loader.visibility = View.VISIBLE
        } else {
            loader.visibility = View.GONE
        }
    }

    private fun fetchData() {
        intent.extras?.let {
            val woeid = it.getString("woeid", null)

            woeid?.let { id ->
                viewModel.getLocationsByTerm(id)
            }
        }
    }
}
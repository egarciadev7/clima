package com.app.prueba.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.prueba.R
import com.app.prueba.ui.adapters.LocationAdapter
import com.app.prueba.ui.adapters.LocationAdapter.LocationAdapterListener
import com.app.prueba.ui.model.Location
import com.app.prueba.viewmodel.MainActivityViewModel
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainActivityViewModel
    lateinit var adapter: LocationAdapter
    var skeleton: RecyclerViewSkeletonScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupObservers()
        setupListeners()

        fetchData(" ")
    }

    override fun onResume() {
        super.onResume()
        showKeyboard()
    }

    private fun setupView() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        adapter = LocationAdapter()
        locationsList.layoutManager = LinearLayoutManager(this)
        locationsList.adapter = adapter

        skeleton = Skeleton.bind(locationsList)
            .adapter(adapter)
            .count(20)
            .load(R.layout.list_skeleton)
            .show();
    }

    private fun showKeyboard() {
        txtSearch.requestFocus()
        txtSearch.postDelayed(Runnable {
            val keyboard: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(txtSearch, 0)
        }, 200)
    }

    private fun hideKeyBoard() {
        txtSearch.clearFocus()
        val _in = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        _in.hideSoftInputFromWindow(txtSearch.windowToken, 0)
    }

    private fun setupObservers() {

        viewModel.getLocationsResponse().observe(this, {

            adapter.setLocations(it)

            if (it.isEmpty()) {
                noResults.visibility = View.VISIBLE
            } else {
                noResults.visibility = View.GONE
            }
        })

        viewModel.getLocationsErrorResponse().observe(this, {
            Toast.makeText(this, resources.getString(R.string.ocurrio_un_error), Toast.LENGTH_LONG)
                .show()
        })

        viewModel.getShowLoader().observe(this, {
            showLoader(it)
        })
    }

    private fun setupListeners() {

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                p0?.let {
                    fetchData(p0.toString())
                }
            }
        })

        txtSearch?.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val term = v.text.toString()
                fetchData(term)
                return@OnEditorActionListener true
            }
            false
        })

        adapter.setListener(object : LocationAdapterListener {
            override fun onLocationTap(location: Location) {
                goToLocationDetail(location)
            }
        })
    }

    private fun goToLocationDetail(location: Location) {
        hideKeyBoard()
        val intent = Intent(this, LocationDetailActivity::class.java)
        intent.putExtra("woeid", location.woeid)
        startActivity(intent)
    }

    private fun showLoader(show: Boolean) {
        if(show) {
            skeleton?.show()
            loader.visibility = View.VISIBLE
        } else {
            skeleton?.hide()
            loader.visibility = View.GONE
        }
    }

    private fun fetchData(term: String) {
        if(term.isNotEmpty()) {
            viewModel.getLocationsByTerm(term)
        }
    }
}
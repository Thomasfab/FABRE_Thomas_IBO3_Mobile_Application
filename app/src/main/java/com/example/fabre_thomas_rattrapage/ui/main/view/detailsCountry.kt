package com.example.fabre_thomas_rattrapage.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fabre_thomas_rattrapage.R
import com.example.fabre_thomas_rattrapage.data.api.ApiHelper
import com.example.fabre_thomas_rattrapage.data.api.RetrofitBuilder
import com.example.fabre_thomas_rattrapage.data.model.GlobalCases
import com.example.fabre_thomas_rattrapage.ui.main.viewmodel.MainViewModel
import com.example.fabre_thomas_rattrapage.ui.main.viewmodel.ViewModelFactory
import com.example.fabre_thomas_rattrapage.utils.Status
import kotlinx.android.synthetic.main.detailscountry_activity.*

class detailsCountry :  AppCompatActivity()  {

    val COUNTRY_NAME = "country name"
    private lateinit var viewModel: MainViewModel
    private var mCountry:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailscountry_activity)
        mCountry = intent.getStringExtra(COUNTRY_NAME)
        setupViewModel()
        setupObservers()
    }


    private fun setupViewModel() {

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(null, RetrofitBuilder.apiCountries, mCountry))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getSpecificData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        container2.visibility = View.VISIBLE
                        progressBar2.visibility = View.GONE
                        resource.data?.let { globalsCases -> setText(globalsCases) }
                    }
                    Status.ERROR -> {
                        container2.visibility = View.VISIBLE
                        progressBar2.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar2.visibility = View.VISIBLE
                        container2.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setText(item : GlobalCases)
    {
        country_name?.text = item.country
        cases?.append(item.cases.toString())
        todays_cases?.append(item.cases.toString())
        active_cases?.append(item.active.toString())
        deaths?.append(item.deaths.toString())
        todays_deaths?.append(item.todayDeaths.toString())
        recovered?.append(item.recovered.toString())
        critical2?.append(item.critical.toString())
    }
}
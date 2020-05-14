package com.example.fabre_thomas_rattrapage.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fabre_thomas_rattrapage.R
import com.example.fabre_thomas_rattrapage.data.api.ApiHelper
import com.example.fabre_thomas_rattrapage.data.api.RetrofitBuilder
import com.example.fabre_thomas_rattrapage.data.model.GlobalCases
import com.example.fabre_thomas_rattrapage.ui.main.adapter.MainAdapter
import com.example.fabre_thomas_rattrapage.ui.main.viewmodel.MainViewModel
import com.example.fabre_thomas_rattrapage.ui.main.viewmodel.ViewModelFactory
import com.example.fabre_thomas_rattrapage.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val COUNTRY_NAME = "country name"

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService, null, null))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf()){country -> countrieOnClickListener(country)}
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun countrieOnClickListener(country : GlobalCases){
        val intent = Intent(this, detailsCountry::class.java)
        intent.putExtra(COUNTRY_NAME, country.country)
        startActivity(intent)
    }



    private fun setupObservers() {
        viewModel.getGlobalData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { globalsCases -> retrieveList(globalsCases) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(globalsCases: List<GlobalCases>) {
        adapter.apply {
            addCountries(globalsCases)
            notifyDataSetChanged()
        }
    }
}
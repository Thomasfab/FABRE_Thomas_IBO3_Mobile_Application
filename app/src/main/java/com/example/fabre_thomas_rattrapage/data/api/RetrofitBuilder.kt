package com.example.fabre_thomas_rattrapage.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BASE_URL = "https://coronavirus-19-api.herokuapp.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService = getRetrofit().create(GlobalDataRequest::class.java)
    val apiCountries = getRetrofit().create(SpecificCountriesrequest::class.java)
}
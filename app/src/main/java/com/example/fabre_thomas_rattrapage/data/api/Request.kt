package com.example.fabre_thomas_rattrapage.data.api

import com.example.fabre_thomas_rattrapage.data.model.GlobalCases
import retrofit2.http.GET
import retrofit2.http.Url

interface GlobalDataRequest{
    @GET("/countries")
    suspend fun getGlobalData(): List<GlobalCases>
}

interface SpecificCountriesrequest{
    @GET
    suspend fun getCountriesData(@Url url : String?) : GlobalCases
}
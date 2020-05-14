package com.example.fabre_thomas_rattrapage.data.repository

import com.example.fabre_thomas_rattrapage.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getGlobalData() = apiHelper.getGlobalData()
    suspend fun getSpecificData() = apiHelper.getcountriesData()
}
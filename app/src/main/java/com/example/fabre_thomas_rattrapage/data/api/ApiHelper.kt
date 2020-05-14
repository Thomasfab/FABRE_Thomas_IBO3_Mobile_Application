package com.example.fabre_thomas_rattrapage.data.api

class ApiHelper( private val apiService: GlobalDataRequest ?,
                 private val apiCountries: SpecificCountriesrequest ?, private val countries : String ?) {

    private val url = RetrofitBuilder.BASE_URL+"countries/"+countries
    suspend fun getGlobalData() = apiService?.getGlobalData()
    suspend fun getcountriesData() = apiCountries?.getCountriesData(url)

}
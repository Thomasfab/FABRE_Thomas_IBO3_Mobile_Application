package com.example.fabre_thomas_rattrapage.data.model

data class GlobalCases (
    val country: String,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths : Int,
    val recovered : Int,
    val active : Int,
    val critical : Int,
    val casesPerOneMillion : Int,
    val deathsPerOneMillion : Int,
    val totalTests : Int,
    val testsPerOneMillion : Int
)
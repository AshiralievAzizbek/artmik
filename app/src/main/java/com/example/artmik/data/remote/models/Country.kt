package com.example.artmik.data.remote.models

data class Country(
    val name: String,
    val capital: String,
    val region: String,
    val timezones: List<String>,
    val flags: List<String>,
    val currencies: List<Currency>,
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String,
)
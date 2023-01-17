package com.example.artmik.data.remote.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("cioc")
    val id: String,
    val name: String,
    val capital: String,
    val region: String,
    val timezones: List<String>,
    val flags: Flag,
    val currencies: List<Currency>,
)

data class Currency(
    val name: String,
    val symbol: String,
)

data class Flag(
    val png: String,
)
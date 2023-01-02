package com.example.appyanu2.network

import com.google.gson.annotations.SerializedName

//TODO 2 : Atribut data dari value result
/*
* data class result merupakan model objek yang akan ditampilkan dari API*/
data class Result (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    )
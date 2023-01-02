package com.example.appyanu2.network

import com.google.gson.annotations.SerializedName

//TODO 1 : Atribut Data Film themoviedb
data class Film(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Result>,
    @SerializedName("total_pages") val pages: Int
)
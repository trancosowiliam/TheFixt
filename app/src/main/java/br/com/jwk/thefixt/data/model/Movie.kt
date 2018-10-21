package br.com.jwk.thefixt.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("imdbID") val imdbID: String,
        @SerializedName("Title") val title: String,
        @SerializedName("Poster") val img: String,
        @SerializedName("Year") val year: String,
        @SerializedName("Runtime") val duration: String
)
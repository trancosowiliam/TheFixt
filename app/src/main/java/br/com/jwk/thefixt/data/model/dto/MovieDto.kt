package br.com.jwk.thefixt.data.model.dto

import br.com.jwk.thefixt.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
        @SerializedName("imdbID") val imdbID: String,
        @SerializedName("Title") val title: String,
        @SerializedName("Poster") val img: String,
        @SerializedName("Runtime") val duration: String?
) : OMDbData(true, null) {
    fun toMovie() = Movie(
            imdbID,
            title,
            img,
            duration ?: "")
}
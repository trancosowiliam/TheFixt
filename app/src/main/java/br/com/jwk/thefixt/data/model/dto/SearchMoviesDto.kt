package br.com.jwk.thefixt.data.model.dto

import com.google.gson.annotations.SerializedName

data class SearchMoviesDto(
        @SerializedName("Search") val movies: List<MovieDto>,
        @SerializedName("totalResults") val totalResults: Int
) : OMDbData(true, null)
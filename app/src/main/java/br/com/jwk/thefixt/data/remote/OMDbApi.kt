package br.com.jwk.thefixt.data.remote

import br.com.jwk.thefixt.data.model.dto.MovieDto
import br.com.jwk.thefixt.data.model.dto.SearchMoviesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApi {

    @GET("/")
    fun searchMovie(
            @Query("i") omdbId: String,
            @Query("apikey") apikey: String
    ): Call<MovieDto>

    @GET("/")
    fun searchMovies(
            @Query("s") title: String,
            @Query("page") page: Int,
            @Query("apikey") apikey: String
    ): Call<SearchMoviesDto>
}
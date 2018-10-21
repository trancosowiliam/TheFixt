package br.com.jwk.thefixt.data.remote

import br.com.jwk.thefixt.data.model.Movie


interface OMDbService {
    fun searchMovie(imdbId: String, onSuccess: (Movie) -> Unit, onError: (String) -> Unit)
    fun searchMovies(title: String, page: Int = 1, onSuccess: (movies: List<Movie>, totalResults: Int) -> Unit, onError: (String) -> Unit)
}
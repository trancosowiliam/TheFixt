package br.com.jwk.thefixt.data.remote


import br.com.jwk.thefixt.BuildConfig
import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.data.model.dto.OMDbData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class OMDbServiceImpl(private val retrofit: Retrofit) : OMDbService {

    private val api by lazy { retrofit.create(OMDbApi::class.java) }

    override fun searchMovie(title: String, onSuccess: (Movie) -> Unit, onError: (String) -> Unit) {
        api.searchMovie(title, BuildConfig.OMDB_API_KEY).exec(onError) { movieDto ->
            onSuccess(movieDto.toMovie())
        }
    }

    override fun searchMovies(title: String, page: Int, onSuccess: (List<Movie>, Int) -> Unit, onError: (String) -> Unit) {
        api.searchMovies(title, page, BuildConfig.OMDB_API_KEY).exec(onError) { searchMoviesDto ->
            onSuccess(searchMoviesDto.movies.map { it.toMovie() }, searchMoviesDto.totalResults)
        }
    }
}

fun <T : OMDbData> Call<T>.exec(onError: (String) -> Unit, onSuccess: (T) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if (response?.isSuccessful == true && response?.body()?.response == true && response?.body() != null) {
                onSuccess(response!!.body()!!)
            } else {
                onError(response?.body()?.error ?: "Erro inesperado")
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable) {
            onError("Erro inesperado! Verifique sua conexão")
        }
    })
}
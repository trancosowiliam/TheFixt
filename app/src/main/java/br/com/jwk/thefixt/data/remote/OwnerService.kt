package br.com.jwk.thefixt.data.remote

import br.com.jwk.thefixt.data.model.Movie

interface OwnerService {
    fun register(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun login(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun save(movie: Movie, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun loadRemoteStoreMovies(onSuccess: (List<Movie>) -> Unit, onError: (String) -> Unit)
    fun loadLocalStoreMovies(onSuccess: (List<Movie>) -> Unit, onError: (String) -> Unit)
    fun isLogged(): Boolean
}
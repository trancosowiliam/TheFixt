package br.com.jwk.thefixt.data.remote

import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.ext.toMovie
import br.com.jwk.thefixt.ext.toParseObject
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.ParseQuery
import com.parse.ParseObject


class OwnerServiceImpl : OwnerService {

    override fun register(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        ParseUser().apply {
            username = login
            setPassword(password)
        }.signUpInBackground { e ->
            e.handle({
                ParseUser.logOut()
                onError(it)
            }, onSuccess)
        }
    }

    override fun login(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        ParseUser.logInInBackground(login, password) { _, e ->
            e.handle({
                ParseUser.logOut()
                onError(it)
            }, onSuccess)
        }
    }

    override fun save(movie: Movie, onSuccess: () -> Unit, onError: (String) -> Unit) {
        movie.toParseObject().apply {
            pinInBackground {
                saveEventually()
                it.handle(onError, onSuccess)
            }
        }
    }

    override fun loadRemoteStoreMovies(onSuccess: (List<Movie>) -> Unit, onError: (String) -> Unit) {
        val query = ParseQuery.getQuery<ParseObject>("Movie")
        query.whereEqualTo("belongTo", ParseUser.getCurrentUser())
        query.findInBackground { movies, e ->
            e.handle(onError) {
                onSuccess(movies.map { it.toMovie() })
            }
        }
    }

    override fun loadLocalStoreMovies(onSuccess: (List<Movie>) -> Unit, onError: (String) -> Unit) {
        val query = ParseQuery.getQuery<ParseObject>("Movie")
        query.whereEqualTo("belongTo", ParseUser.getCurrentUser())
        query.fromLocalDatastore()
        query.ignoreACLs()
        query.findInBackground { movies, e ->
            e.handle(onError) {
                onSuccess(movies.map { it.toMovie() })
            }
        }
    }

    override fun isLogged() =
            ParseUser.getCurrentUser() != null
}

private fun ParseException?.handle(onError: (String) -> Unit, onSuccess: () -> Unit) {
    if (this == null) {
        onSuccess()
    } else {
        onError(localMessage)
    }
}

private val ParseException.localMessage: String
    get() {
        return when (code) {
            201 -> "Senha invalida"
            else -> "Erro inesperado"
        }
    }

package br.com.jwk.thefixt.data.remote

import com.parse.ParseException
import com.parse.ParseUser

class OwnerServiceImpl : OwnerService {

    override fun register(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        ParseUser().apply {
            username = login
            setPassword(password)
        }.signUpInBackground { parseException ->
            if (parseException == null) {
                onSuccess()
            } else {
                ParseUser.logOut()
                onError(parseException.localMessage)
            }
        }
    }

    override fun login(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        ParseUser.logInInBackground(login, password) { parseUser, parseException ->
            if (parseUser != null) {
                onSuccess()
            } else {
                ParseUser.logOut()
                onError(parseException.localMessage)
            }
        }
    }

    override fun isLogged() =
        ParseUser.getCurrentUser() != null
}

private val ParseException.localMessage: String
    get() {
        return when (code) {
            201 -> "Senha invalida"
            else -> "Erro inesperado"
        }
    }

package br.com.jwk.thefixt.data.remote

interface OwnerService {
    fun register(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun login(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun isLogged(): Boolean
}
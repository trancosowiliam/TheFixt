package br.com.jwk.thefixt.base

interface BasePresenter<T> {
    var view: T

    fun init()

    operator fun invoke(view: T) {
        this.view = view
        init()
    }
}
package br.com.jwk.thefixt.base

interface BasePresenter<T> {
    var view: T

    operator fun invoke(view: T) {
        this.view = view
    }
}
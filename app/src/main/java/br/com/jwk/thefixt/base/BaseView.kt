package br.com.jwk.thefixt.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}
package br.com.jwk.thefixt.module

import br.com.jwk.thefixt.view.movies.MoviesContract
import br.com.jwk.thefixt.view.movies.MoviesPresenter
import org.koin.dsl.module.applicationContext

val applicationModule = applicationContext {
    factory { MoviesPresenter(service = get()) as MoviesContract.Presenter }
}
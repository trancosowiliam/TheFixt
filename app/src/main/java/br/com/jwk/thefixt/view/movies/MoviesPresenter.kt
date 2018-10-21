package br.com.jwk.thefixt.view.movies

import br.com.jwk.thefixt.data.remote.OMDbService

class MoviesPresenter(val service: OMDbService) : MoviesContract.Presenter {
    override lateinit var view: MoviesContract.View
}
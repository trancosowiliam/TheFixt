package br.com.jwk.thefixt.view.movies

import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.data.remote.OMDbService

class MoviesPresenter(val service: OMDbService) : MoviesContract.Presenter {

    override lateinit var view: MoviesContract.View

    override fun loadRemoteStoreMovies() {
    }

    override fun loadLocalStoreMovies() {
    }

    override fun searchMovies(title: String, page: Int) {
    }

    override fun save(movie: Movie) {
    }
}
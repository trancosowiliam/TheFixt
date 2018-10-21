package br.com.jwk.thefixt.view.movies

import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.data.remote.OMDbService
import br.com.jwk.thefixt.data.remote.OwnerService

class MoviesPresenter(val service: OMDbService, val ownerService: OwnerService) : MoviesContract.Presenter {

    override lateinit var view: MoviesContract.View

    override fun init() {
        view.showLoading()

        if (!ownerService.isLogged()) {
            ownerService.login("thefixt", "123456", {
                view.hideLoading()
                view.loginLoaded()
            }, {
                view.showMessage("Falhou", it)
            })
        }
    }

    override fun loadRemoteStoreMovies() {
    }

    override fun loadLocalStoreMovies() {
    }

    override fun searchMovies(title: String, page: Int) {
        view.showLoading()

        service.searchMovies(title, page, { movies, _ ->
            view.hideLoading()
            if (movies.isEmpty()) {
                view.showMessage("Falhou!", "Nenhum filme foi encontrado")
            } else {
                view.searchMoviesLoaded(movies)
            }
        }, { error ->
            view.hideLoading()
            view.showMessage("Falhou!", error)
        })
    }

    override fun save(movie: Movie) {
    }
}
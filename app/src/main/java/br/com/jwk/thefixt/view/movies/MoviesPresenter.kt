package br.com.jwk.thefixt.view.movies

import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.data.remote.OMDbService
import br.com.jwk.thefixt.data.remote.OwnerService

class MoviesPresenter(val service: OMDbService, val ownerService: OwnerService) : MoviesContract.Presenter {

    override lateinit var view: MoviesContract.View
    private val onDefaultError = { message: String ->
        view.hideLoading()
        view.showMessage("Falhou!", message)
    }

    override fun init() {
        if (ownerService.isLogged()) {
            view.loginLoaded()
        } else {
            view.showLoading()

            ownerService.login("thefixt", "123456", {
                view.hideLoading()
                view.loginLoaded()
            }, {
                view.onLoginError()
            })
        }
    }

    override fun loadRemoteStoreMovies() {
        view.showLoading()

        ownerService.loadRemoteStoreMovies({
            view.hideLoading()
            view.storeMoviesLoaded(it)
        }, onDefaultError)
    }

    override fun loadLocalStoreMovies() {
        ownerService.loadLocalStoreMovies({
            view.hideLoading()
            view.storeMoviesLoaded(it)
        }, onDefaultError)
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
        }, onDefaultError)
    }

    override fun save(partialMovie: Movie) {
        view.showLoading()

        service.searchMovie(partialMovie.imdbID, { movie ->
            ownerService.save(movie, {
                view.hideLoading()
                view.moviewSaved(movie)
            }, onDefaultError)
        }, onDefaultError)
    }
}
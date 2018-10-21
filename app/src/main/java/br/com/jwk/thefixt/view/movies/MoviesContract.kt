package br.com.jwk.thefixt.view.movies

import br.com.jwk.thefixt.base.BasePresenter
import br.com.jwk.thefixt.base.BaseView
import br.com.jwk.thefixt.data.model.Movie

interface MoviesContract {
    interface Presenter : BasePresenter<View> {
        fun loadRemoteStoreMovies()
        fun loadLocalStoreMovies()
        fun searchMovies(title: String, page: Int)
        fun save(movie: Movie)
    }

    interface View : BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun showMessage(title: String, message: String)
        fun loginLoaded()
        fun storeMoviesLoaded(movies: List<Movie>)
        fun searchMoviesLoaded(movies: List<Movie>)
        fun moviewSaved(movie: Movie)
    }
}
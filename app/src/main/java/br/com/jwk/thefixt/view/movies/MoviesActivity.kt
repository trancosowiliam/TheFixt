package br.com.jwk.thefixt.view.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.jwk.thefixt.R
import br.com.jwk.thefixt.data.model.Movie
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity(), MoviesContract.View {

    override val presenter by inject< MoviesContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        presenter(this)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(title: String, message: String) {
    }

    override fun storeMoviesLoaded(movies: List<Movie>) {
    }

    override fun searchMoviesLoaded(movies: List<Movie>) {
    }

    override fun moviewSaved(movie: Movie) {
    }
}

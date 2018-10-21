package br.com.jwk.thefixt.view.movies

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import br.com.jwk.thefixt.R
import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.ext.logi
import kotlinx.android.synthetic.main.activity_movies.mvsEdtSearch as edtSearch
import org.koin.android.ext.android.inject

class MoviesActivity : AppCompatActivity(), MoviesContract.View {

    override val presenter by inject< MoviesContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        presenter(this)

        edtSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchMovies(v.text.toString(), 1)
                removeEditTextFocus()
            }

            false
        }
    }

    override fun showLoading() {
        "show loading".logi()
    }

    override fun hideLoading() {
        "hide loading".logi()
    }

    override fun showMessage(title: String, message: String) {
        message.logi()
    }

    override fun storeMoviesLoaded(movies: List<Movie>) {
        movies.toString().logi()
    }

    override fun searchMoviesLoaded(movies: List<Movie>) {
        movies.toString().logi()
    }

    override fun moviewSaved(movie: Movie) {
        movie.toString().logi()
    }

    private fun removeEditTextFocus() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)

        edtSearch.isCursorVisible = false
    }
}

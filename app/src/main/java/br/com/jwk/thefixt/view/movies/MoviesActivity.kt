package br.com.jwk.thefixt.view.movies

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import br.com.jwk.thefixt.R
import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.ext.isNetworkConnected
import br.com.jwk.thefixt.ext.isVisible
import br.com.jwk.thefixt.ext.logi
import br.com.jwk.thefixt.ext.makeDialog
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.customListAdapter
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.activity_movies.mvsEdtSearch as edtSearch
import kotlinx.android.synthetic.main.activity_movies.mvsPbLoading as pbLoading

class MoviesActivity : AppCompatActivity(), MoviesContract.View {

    override val presenter by inject<MoviesContract.Presenter>()

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

    override fun loginLoaded() {
        if (isNetworkConnected) {
            presenter.loadRemoteStoreMovies()
        } else {
            presenter.loadLocalStoreMovies()
        }
    }

    override fun showLoading() {
        pbLoading.isVisible = true
    }

    override fun hideLoading() {
        pbLoading.isVisible = false
    }

    override fun showMessage(title: String, message: String) {
        makeDialog(title, message)
                .positiveButton(text = "Ok")
                .show()
    }

    override fun storeMoviesLoaded(movies: List<Movie>) {
        movies.toString().logi()
    }

    override fun searchMoviesLoaded(movies: List<Movie>) {
        val adapter = MoviesDialogAdapter(this, movies)
        val dialog = MaterialDialog(this).customListAdapter(adapter)

        adapter.onItemClick = {
            it.title.logi()
            dialog.dismiss()
        }

        dialog.show()
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

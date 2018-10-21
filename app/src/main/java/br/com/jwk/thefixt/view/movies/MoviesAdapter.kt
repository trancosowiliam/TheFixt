package br.com.jwk.thefixt.view.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jwk.thefixt.R
import br.com.jwk.thefixt.data.model.Movie
import br.com.jwk.thefixt.ext.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(context: Context, val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.Holder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(layoutInflater.inflate(R.layout.item_movie, parent, false))


    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(movies[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(movie: Movie) {
            itemView.imovTxtTitle.text = movie.title
            itemView.imovTxtDuration.text = movie.duration
            itemView.imovImgPoster.loadImage(movie.img)
        }
    }
}
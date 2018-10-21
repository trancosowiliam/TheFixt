package br.com.jwk.thefixt.view.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jwk.thefixt.R
import br.com.jwk.thefixt.data.model.Movie
import kotlinx.android.synthetic.main.item_movie_dialog.view.*

class MoviesDialogAdapter(context: Context, val movies: List<Movie>) : RecyclerView.Adapter<MoviesDialogAdapter.Holder>() {

    private val layoutInflater = LayoutInflater.from(context)
    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(layoutInflater.inflate(R.layout.item_movie_dialog, parent, false))


    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(movies[position])
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.imdImgDownload.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }

        fun render(movie: Movie) {
            itemView.imdTxtName.text = movie.title
        }
    }
}
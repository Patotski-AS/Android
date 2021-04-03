package com.example.android.mymovies.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mymovies.R
import com.example.android.mymovies.pogo.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var movies: ArrayList<Movie>
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {
    var onPosterClickListener: OnPosterClickListener? = null
    var onReachEndListener: OnReachEndListener? = null

    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
        private const val SMALL_POSTER_SIZE = "w185"
        private const val BIG_POSTER_SIZE = "w780"
    }


    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewSmallPoster: ImageView = itemView.findViewById(R.id.imageViewSmallPoster)

        init {
            if (onPosterClickListener != null) {
                itemView.setOnClickListener(View.OnClickListener {
                    onPosterClickListener?.onPosterClick(adapterPosition)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        if ((position > movies.size - 4) and (onReachEndListener != null)) {
            onReachEndListener?.onReachEnd()
        }

        val movie = movies[position]
        Picasso.get().load("$BASE_POSTER_URL$SMALL_POSTER_SIZE${movie.poster_path}")
            .into(holder.imageViewSmallPoster)
    }

    override fun getItemCount() = movies.size

    fun addMovies(movies: MutableList<Movie>?) {
        movies?.let { this.movies.addAll(it) }
        notifyDataSetChanged()
    }

    fun setMovies(movies: ArrayList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }


}
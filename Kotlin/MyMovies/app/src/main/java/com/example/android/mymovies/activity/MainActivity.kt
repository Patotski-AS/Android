package com.example.android.mymovies.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.mymovies.MovieViewModel
import com.example.android.mymovies.R
import com.example.android.mymovies.databinding.ActivityMainBinding
import com.example.android.mymovies.pogo.Movie
import com.example.android.mymovies.recyclerView.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movies: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movies = ArrayList()
        movieAdapter = MovieAdapter(movies as ArrayList<Movie>)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.fetchMovies()
        movieViewModel.popularMoviesLiveData.observe(this, Observer {
            movieAdapter.addMovies(it)
        })

        binding.recyclerViewPosters.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewPosters.adapter = movieAdapter
    }


}

package com.example.android.mymovies.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.mymovies.AppConstants
import com.example.android.mymovies.mvvm.MovieViewModel
import com.example.android.mymovies.R
import com.example.android.mymovies.databinding.ActivityMainBinding
import com.example.android.mymovies.pogo.Movie
import com.example.android.mymovies.recyclerView.MovieAdapter
import com.example.android.mymovies.recyclerView.OnPosterClickListener
import com.example.android.mymovies.recyclerView.OnReachEndListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movies: List<Movie>

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        movies = ArrayList()
        movieAdapter = MovieAdapter(movies as ArrayList<Movie>)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieAdapter.onPosterClickListener = object : OnPosterClickListener {
            override fun onPosterClick(position: Int) {
                Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        movieAdapter.onReachEndListener = object : OnReachEndListener{
            override fun onReachEnd() {
                Toast.makeText(applicationContext, "Конец списка", Toast.LENGTH_SHORT).show()
            }

        }
        movieViewModel.fetchMovies()
        binding.switchSort.isChecked = true
        movieViewModel.popularMoviesLiveData.observe(this, Observer {
            movieAdapter.setMovies(it as ArrayList<Movie>)
        })

        binding.recyclerViewPosters.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewPosters.adapter = movieAdapter

        binding.switchSort.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                movieViewModel.fetchMovies(sorted = AppConstants.SORT_BY_POPULARITY)
            } else {
                movieViewModel.fetchMovies(sorted = AppConstants.SORT_BY_TOP_RATED)
            }
        })
    }

    fun onClickSetPopularity(view: View) {
        binding.switchSort.isChecked = false
    }

    fun onClickSetTopRated(view: View) {
        binding.switchSort.isChecked = true

    }


}

package com.example.cinemoapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cinemoapp.R
import com.example.cinemoapp.adapter.MovieListAdapter
import com.example.cinemoapp.databinding.FragmentMovieListBinding
import com.example.cinemoapp.viewmodel.MovieAvailableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    val TAG: String = "MovieListFragment"
    val viewModel: MovieAvailableViewModel by viewModels()
    lateinit var binding: FragmentMovieListBinding

    val adapter by lazy {
        MovieListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecyclerView.adapter = adapter
        getMovies()
    }

    private fun getMovies(){
        viewModel.getMovies()
        viewModel.responseMovies.observe(viewLifecycleOwner){
            Log.e(TAG, it.movies.toString())
            adapter.updateList(it.movies!!.toMutableList())
        }
    }

}
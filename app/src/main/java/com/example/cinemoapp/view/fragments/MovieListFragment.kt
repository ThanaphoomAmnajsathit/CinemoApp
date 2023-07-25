package com.example.cinemoapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cinemoapp.R
import com.example.cinemoapp.adapter.MovieListAdapter
import com.example.cinemoapp.databinding.FragmentMovieListBinding
import com.example.cinemoapp.utils.UiState
import com.example.cinemoapp.utils.hide
import com.example.cinemoapp.utils.show
import com.example.cinemoapp.utils.toast
import com.example.cinemoapp.viewmodel.MovieAvailableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val TAG: String = "MovieListFragment"
    private val viewModel: MovieAvailableViewModel by viewModels({requireActivity()})
    private lateinit var binding: FragmentMovieListBinding

    val adapter by lazy {
        MovieListAdapter(this,
            onItemClicked = { pos, item ->
                val fragment = MovieDetailFragment.newInstance(item)
                findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment,fragment.arguments)
            }
        )
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
        binding.ivFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_movieFavoriteFragment)
        }
        getMovies()
    }

    private fun getMovies() {
        viewModel.getMovies()
        viewModel.responseMovies.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.d(TAG, "Movie List Loading")
                    binding.movieListProgressbar.show()
                }

                is UiState.Success -> {
                    //Log.d(TAG, "Size of movie list :"+ state.data.movies!!.size.toString())
                    binding.movieListProgressbar.hide()
                    adapter.updateList(state.data.movies!!.toMutableList())
                }

                is UiState.Failure -> {
                    binding.movieListProgressbar.hide()
                    toast(state.error)
                    Log.e(TAG, state.error)
                }
            }
        }
    }

}
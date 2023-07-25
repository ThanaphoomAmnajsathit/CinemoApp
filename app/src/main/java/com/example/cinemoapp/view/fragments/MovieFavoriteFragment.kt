package com.example.cinemoapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cinemoapp.R
import com.example.cinemoapp.adapter.MovieListAdapter
import com.example.cinemoapp.databinding.FragmentMovieFavoriteBinding
import com.example.cinemoapp.databinding.FragmentMovieListBinding
import com.example.cinemoapp.viewmodel.MovieAvailableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoriteFragment : Fragment() {

    private val TAG: String = "MovieFavoriteFragment"
    private val viewModel: MovieAvailableViewModel by viewModels({requireActivity()})
    private lateinit var binding: FragmentMovieFavoriteBinding

    val adapter by lazy {
        MovieListAdapter(this,
            onItemClicked = { pos, item ->
                val fragment = MovieDetailFragment.newInstance(item)
                findNavController().navigate(R.id.action_movieFavoriteFragment_to_movieDetailFragment,fragment.arguments)
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
        binding = FragmentMovieFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecyclerView.adapter = adapter
        getFavorite()
    }

    private fun getFavorite(){
        adapter.updateList(viewModel.getFavoriteMovies())
    }

}
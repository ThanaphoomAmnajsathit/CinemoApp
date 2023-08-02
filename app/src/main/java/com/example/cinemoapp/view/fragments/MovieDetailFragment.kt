package com.example.cinemoapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cinemoapp.R
import com.example.cinemoapp.databinding.FragmentMovieDetailBinding
import com.example.cinemoapp.databinding.FragmentMovieListBinding
import com.example.cinemoapp.models.MovieAvaiableModel
import com.example.cinemoapp.models.MoviesItem
import com.example.cinemoapp.utils.toast
import com.example.cinemoapp.viewmodel.MovieAvailableViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MovieDetailFragment() : Fragment() {

    private val TAG: String = "MovieListFragment"
    private var moviesItem: MoviesItem? = null
    private var fragmentTag: String? = null
    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: MovieAvailableViewModel by viewModels({requireActivity()})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moviesItem = it.getParcelable("movie_item")
            fragmentTag = it.getString("fragment_tag")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //toast(moviesItem.toString())
        Glide.with(this).load(moviesItem?.posterUrl).into(binding.ivMovie)
        binding.tvMovieType.text = moviesItem?.genre
        binding.tvMovieName.text = moviesItem?.titleEn
        binding.tvMovieDiscription.text = moviesItem?.synopsisEn
        bindButton()
    }

    private fun bindButton(){
        if (fragmentTag.equals("MovieFavoriteFragment")){
            binding.favoriteBtn.text = getString(R.string.remove_favorite)
            setRemoveFavoriteClickListener()
        }else{
            setAddToFavoritesClickListener()
        }
    }

    private fun setRemoveFavoriteClickListener() {
        if (moviesItem != null) {
            binding.favoriteBtn.setOnClickListener {
                viewModel.removeFavorite(moviesItem!!)
                findNavController().popBackStack()
            }
        }
    }

    private fun setAddToFavoritesClickListener() {
        if (moviesItem != null) {
            binding.favoriteBtn.setOnClickListener {
                viewModel.addToFavorites(moviesItem!!)
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            moviesItem: MoviesItem,
            fragmentTag: String
        ) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("movie_item",moviesItem)
                    putString("fragment_tag",fragmentTag)
                }
            }
    }
}
package com.example.cinemoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemoapp.databinding.ItemMovieLayoutBinding
import com.example.cinemoapp.models.MoviesItem
import com.example.cinemoapp.view.fragments.MovieListFragment

class MovieListAdapter(val fragment : MovieListFragment): RecyclerView.Adapter<MovieListAdapter.MyViewHolder>(){

    private var list: MutableList<MoviesItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    fun updateList(list: MutableList<MoviesItem>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemMovieLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : MoviesItem){
            Glide.with(fragment).load(item.posterUrl).into(binding.ivMovie)
            binding.tvMovieType.text = item.genre
            binding.tvMovieName.text = item.titleEn
            binding.tvMovieDate.text = item.releaseDate
        }
    }
}
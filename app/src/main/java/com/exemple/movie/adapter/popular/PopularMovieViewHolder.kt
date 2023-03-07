package com.exemple.movie.adapter.popular

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.model.response.popular.Result

class PopularMovieViewHolder(val binding: FirstSubItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bindData(data:Result){
        binding.title.text = data.title
        val imageUrl = "https://image.tmdb.org/t/p/original"+data.posterPath
        binding.image.load(imageUrl)
    }
}
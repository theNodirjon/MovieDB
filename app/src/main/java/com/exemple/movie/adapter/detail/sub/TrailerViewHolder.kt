package com.exemple.movie.adapter.detail.sub

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.exemple.movie.databinding.TrailerItemBinding
import com.exemple.movie.model.response.details.trailer.Result

class TrailerViewHolder(val binding: TrailerItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: Result){
        val imageURl = "http://img.youtube.com/vi/".plus(data.key).plus("/default.jpg")

        binding.image.load(imageURl)
    }
}
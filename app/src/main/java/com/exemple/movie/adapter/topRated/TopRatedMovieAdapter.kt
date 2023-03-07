package com.exemple.movie.adapter.topRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.model.response.topRated.Result

class TopRatedMovieAdapter:RecyclerView.Adapter<TopRatedMovieViewHolder>() {

    var data = ArrayList<Result>()

    fun setTopRatedMovieData(data:List<Result>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieViewHolder {
        return TopRatedMovieViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TopRatedMovieViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}
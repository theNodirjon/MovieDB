package com.exemple.movie.adapter.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.model.response.upcoming.Result

class UpcomingMovieAdapter:RecyclerView.Adapter<UpcomingMovieViewHolder>() {

    var data = ArrayList<Result>()

    fun setUpcomingMovieData(data:List<Result>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        return UpcomingMovieViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}
package com.exemple.movie.adapter.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.model.response.popular.Result

class PopularMovieAdapter:RecyclerView.Adapter<PopularMovieViewHolder>() {

    var data = ArrayList<Result>()
        set(value) {
            field.addAll(value)
            notifyItemRangeInserted(data.size - value.size, value.size)
        }
    fun setPopularMovieData(data:List<Result>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}
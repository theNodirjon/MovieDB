package com.exemple.movie.adapter.nowPlaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.SecondSubItemBinding
import com.exemple.movie.model.response.nowPlaying.Result

class NowPlayingMovieAdapter:RecyclerView.Adapter<NowPlayingMovieViewHolder>() {

    var data = ArrayList<Result>()

    fun setNowPlayingMovieData(data:List<Result>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMovieViewHolder {
        return NowPlayingMovieViewHolder(SecondSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NowPlayingMovieViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}
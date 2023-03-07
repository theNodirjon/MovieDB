package com.exemple.movie.adapter.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.TrailerItemBinding
import com.exemple.movie.model.response.trailer.Result

class TraileAdapter:RecyclerView.Adapter<TrailerViewHolder>() {

    var data = ArrayList<Result>()
    fun setTrailerData(data:List<Result>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        return TrailerViewHolder(TrailerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}
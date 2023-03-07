package com.exemple.movie.adapter.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.model.response.similar.Result

class SimilarAdapter:RecyclerView.Adapter<SimilarViewHolder>() {

    var data = ArrayList<Result>()
    fun setSimilarData(data:List<Result>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
       return SimilarViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size
}
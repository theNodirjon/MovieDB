package com.exemple.movie.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.SearchScreenItemBinding
import com.exemple.movie.model.response.search.Result

class SearchMovieAdapter:RecyclerView.Adapter<SearchMovieViewHolder>() {

    var onSearchItemClick:((movie_id:Int)->Unit)? = null
    var data = ArrayList<Result>()

    fun setSearchData(data:List<Result>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder(SearchScreenItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.binding.root.setOnClickListener {
            onSearchItemClick?.invoke(data[position].id)
        }
    }

    override fun getItemCount(): Int = data.size
}
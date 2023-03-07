package com.exemple.movie.adapter.home.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.MainListItemBinding
import com.exemple.movie.model.HomeListData.MainListData

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var onMainListClick: OnMainListClick? = null

    var allButtonClick:((title:String)->Unit)? = null

    var data = ArrayList<MainListData>()
        set(value) {
            field.addAll(value)
            notifyItemRangeInserted(field.size-value.size,value.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(data[position], onMainListClick)
        holder.binding.allBtn.setOnClickListener {
            allButtonClick?.invoke(data[position].title)
        }
    }

    override fun getItemCount(): Int = data.size

    interface OnMainListClick {
        fun onMainClick(movie_id: Int)
    }

}
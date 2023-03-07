package com.exemple.movie.adapter.home.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.adapter.home.sub.SubAdapter
import com.exemple.movie.databinding.MainListItemBinding
import com.exemple.movie.model.HomeListData.MainListData

class MainViewHolder(val binding: MainListItemBinding):RecyclerView.ViewHolder(binding.root) {

    private val adapter = SubAdapter()



    fun bindData(data:MainListData,onMainListClick:MainAdapter.OnMainListClick?) {

        binding.title.text = data.title

        binding.subList.adapter = adapter
        binding.subList.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)

        adapter.setMovieData(data.subData)

        adapter.onSubItemClik = object :SubAdapter.OnSubListClick{
            override fun onSubClick(movie_id: Int) {
                onMainListClick?.onMainClick(movie_id)
            }

        }

    }
}
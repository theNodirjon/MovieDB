package com.exemple.movie.adapter.detail.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.adapter.detail.sub.DetailSubAdapter
import com.exemple.movie.databinding.DetailMainListBinding
import com.exemple.movie.model.DetaiListData.DetailListData
import com.exemple.movie.model.response.base.BaseType

class DetailViewHolder(val binding: DetailMainListBinding):RecyclerView.ViewHolder(binding.root) {

    private val adapter = DetailSubAdapter()

    fun bindData(data:DetailListData,onDetailListClick: DetailAdapter.OnDetailListClick?){

        binding.title.text = data.title

        binding.subList.adapter = adapter
        binding.subList.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)

        adapter.setData(data.subItem)

        adapter.onSubItemClick = object :DetailSubAdapter.OnSubListClick{
            override fun onSubClick(subData: BaseType) {
                onDetailListClick?.onDetailClick(subData)
            }

        }


    }
}

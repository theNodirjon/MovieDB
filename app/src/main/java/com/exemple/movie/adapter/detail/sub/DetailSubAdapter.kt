package com.exemple.movie.adapter.detail.sub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.ActorsListItemBinding
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.databinding.TrailerItemBinding
import com.exemple.movie.model.response.base.BaseType
import com.exemple.movie.model.response.details.actors.Cast
import com.exemple.movie.model.response.details.similar.Result

class DetailSubAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onSubItemClick : OnSubListClick?=null

    var data = ArrayList<BaseType>()

    fun setData(data:List<BaseType>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder = when (viewType){
            BaseType.TYPE_Actor->{
                ActorsViewHolder(ActorsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            BaseType.TYPE_Similar->{
                SimilarViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else->{
                TrailerViewHolder(TrailerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        when(viewType){
            BaseType.TYPE_Actor->{
                (holder as ActorsViewHolder).bindData(data[position] as Cast)
                holder.binding.root.setOnClickListener {
                    onSubItemClick?.let {
                        it.onSubClick(data[position])
                    }
                }
            }
            BaseType.TYPE_Similar->{
                (holder as SimilarViewHolder).bindData(data[position] as Result)
                holder.binding.root.setOnClickListener {
                    onSubItemClick?.let {
                        it.onSubClick(data[position])
                    }
                }
            }
            BaseType.TYPE_Trailer->{
                (holder as TrailerViewHolder).bindData(data[position] as com.exemple.movie.model.response.details.trailer.Result)
                holder.binding.root.setOnClickListener {
                    onSubItemClick?.let {
                        it.onSubClick(data[position])
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int= data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    interface OnSubListClick{
        fun onSubClick(subData:BaseType)
    }
}
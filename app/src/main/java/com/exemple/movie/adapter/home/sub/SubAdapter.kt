package com.exemple.movie.adapter.home.sub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.databinding.FirstSubItemBinding
import com.exemple.movie.databinding.SecondSubItemBinding
import com.exemple.movie.model.response.base.BaseType
import com.exemple.movie.model.response.home.popular.Result

class SubAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onSubItemClik : OnSubListClick?=null

    var data = ArrayList<BaseType>()

    fun setMovieData(data:List<BaseType>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder = when (viewType){
            BaseType.TYPE_Popular->{
                PopularMovieViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            BaseType.TYPE_TopRated->{
                TopRatedMovieViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            BaseType.TYPE_NowPlaying->{
                NowPlayingMovieViewHolder(SecondSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else->{
                UpcomingMovieViewHolder(FirstSubItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        when(viewType){
            BaseType.TYPE_Popular->{
                (holder as PopularMovieViewHolder).bindData(data[position] as Result)
                holder.binding.root.setOnClickListener {
                    onSubItemClik?.let {
                        it.onSubClick((data[position] as Result).id)
                    }
                }
            }
            BaseType.TYPE_TopRated->{
                (holder as TopRatedMovieViewHolder).bindData(data[position] as com.exemple.movie.model.response.home.topRated.Result)
                holder.binding.root.setOnClickListener {
                    onSubItemClik?.let {
                        it.onSubClick((data[position] as com.exemple.movie.model.response.home.topRated.Result).id)
                    }
                }
            }
            BaseType.TYPE_Upcoming->{
                (holder as UpcomingMovieViewHolder).bindData(data[position] as com.exemple.movie.model.response.home.upcoming.Result)
                holder.binding.root.setOnClickListener {
                    onSubItemClik?.let {
                        it.onSubClick((data[position] as com.exemple.movie.model.response.home.upcoming.Result).id)
                    }
                }
            }
            BaseType.TYPE_NowPlaying->{
                (holder as NowPlayingMovieViewHolder).bindData(data[position] as com.exemple.movie.model.response.home.nowPlaying.Result)
                holder.binding.root.setOnClickListener {
                    onSubItemClik?.let {
                        it.onSubClick((data[position] as com.exemple.movie.model.response.home.nowPlaying.Result).id)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    interface OnSubListClick{
        fun onSubClick(movie_id:Int)
    }
}
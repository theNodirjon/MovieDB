package com.exemple.movie.adapter.allMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.movie.adapter.nowPlaying.NowPlayingMovieViewHolder
import com.exemple.movie.databinding.AllMoviesItemBinding
import com.exemple.movie.model.response.base.BaseType
import com.exemple.movie.model.response.home.popular.Result

class AllMoviesAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onAllMovieItemClick:((movie_id:Int)->Unit)?=null
    var data = ArrayList<BaseType>()

    fun setAllMovieData(data:List<BaseType>){
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder = when (viewType){
                BaseType.TYPE_Popular->{
                    PopularMovieViewHolder(AllMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                }
            BaseType.TYPE_TopRated->{
                TopRatedMovieViewHolder(AllMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else->{
                UpcomingMovieViewHolder(AllMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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
                    onAllMovieItemClick?.invoke((data[position] as Result).id)
                }
            }
            BaseType.TYPE_TopRated->{
                (holder as TopRatedMovieViewHolder).bindData(data[position] as com.exemple.movie.model.response.home.topRated.Result)
                holder.binding.root.setOnClickListener {
                    onAllMovieItemClick?.invoke((data[position] as com.exemple.movie.model.response.home.topRated.Result).id)
                }
            }
            BaseType.TYPE_Upcoming->{
                (holder as UpcomingMovieViewHolder).bindData(data[position] as com.exemple.movie.model.response.home.upcoming.Result)
                holder.binding.root.setOnClickListener {
                    onAllMovieItemClick?.invoke((data[position] as com.exemple.movie.model.response.home.upcoming.Result).id)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

}
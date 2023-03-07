package com.exemple.movie.presenter.interfaces

import com.exemple.movie.model.response.home.popular.Result

interface MainPresenter {
    interface Presenter{
        fun loadData()
        fun refreshData()
        fun cancel()
        fun destroy()
    }
    interface View{
        fun dataState(isLoading: Boolean)
        fun showPopularData(popularData: List<Result>)
        fun showTopRatedData(topRatedData:List<com.exemple.movie.model.response.home.topRated.Result>)
        fun showNowPlayingData(nowPlayingData:List<com.exemple.movie.model.response.home.nowPlaying.Result>)
        fun showUpcoming(upcomingData:List<com.exemple.movie.model.response.home.upcoming.Result>)
        fun showError(message: String)
    }
}
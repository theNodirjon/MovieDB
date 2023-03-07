package com.exemple.movie.presenter.interfaces

import com.exemple.movie.model.response.base.BaseType

interface AllMoviePresenter {
    interface Presenter{
        fun loadData(movieType:String)
        fun cancel()
        fun destroy()
    }

    interface View{
        fun dataState(isLoading: Boolean)
        fun showData(data:List<BaseType>)
        fun showError(message: String)
    }
}
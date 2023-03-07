package com.exemple.movie.presenter.impl

import com.exemple.movie.model.network.MovieClient
import com.exemple.movie.model.response.base.BaseType
import com.exemple.movie.model.response.home.popular.PopularMovieResponse
import com.exemple.movie.model.response.home.topRated.TopRatedMovieResponse
import com.exemple.movie.model.response.home.upcoming.UpcomingMovieResponse
import com.exemple.movie.presenter.interfaces.AllMoviePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterAllMovie(private val view: AllMoviePresenter.View): AllMoviePresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    val call = MovieClient.getApiMethods()

    override fun loadData(movieType:String) {
        when(movieType){
            "Popular"->{
                loadPopularMovies()
            }
            "Top Rated"->{
                loadTopRatedMovies()
            }
            "Upcoming"->{
                loadUpcomingMovies()
            }
            "Now Playing"->{

            }
        }
    }
    override fun cancel() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    private fun loadUpcomingMovies() {

        compositeDisposable.add(
            call.getUpcomingMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UpcomingMovieResponse>(){
                    override fun onSuccess(t: UpcomingMovieResponse) {
                        view.showData(t.results as List<BaseType>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )

    }


    private fun loadTopRatedMovies() {

        compositeDisposable.add(
            call.getTopRated()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TopRatedMovieResponse>(){
                    override fun onSuccess(t: TopRatedMovieResponse) {
                        view.showData(t.results as List<BaseType>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )
    }

    private fun loadPopularMovies() {

        compositeDisposable.add(
        call.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PopularMovieResponse>(){
                    override fun onSuccess(t: PopularMovieResponse) {
                        view.showData(t.results as List<BaseType>)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )

    }
}
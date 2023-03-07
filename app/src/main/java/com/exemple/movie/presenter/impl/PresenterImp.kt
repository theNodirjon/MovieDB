package com.exemple.movie.presenter.impl

import com.exemple.movie.model.network.MovieClient
import com.exemple.movie.model.response.home.nowPlaying.NowPlayingMoviesResponse
import com.exemple.movie.model.response.home.popular.PopularMovieResponse
import com.exemple.movie.model.response.home.topRated.TopRatedMovieResponse
import com.exemple.movie.model.response.home.upcoming.UpcomingMovieResponse
import com.exemple.movie.presenter.interfaces.MainPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterImp(private val view: MainPresenter.View): MainPresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()
    private val call = MovieClient.getApiMethods()

    override fun loadData() {
//        loadData()
        loadPopularMovies()
        loadNowPlayingMovies()
        loadTopRatedMovies()
        loadUpcomingMovies()
    }

    override fun refreshData() {
        // TODO:
//        loadPopularMovies()
//        loadNowPlayingMovies()
//        loadTopRatedMovies()
//        loadUpcomingMovies()
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
                .subscribeWith(object :DisposableSingleObserver<UpcomingMovieResponse>(){
                    override fun onSuccess(t: UpcomingMovieResponse) {
                        view.showUpcoming(t.results)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }
                }
                )
        )
    }

    private fun loadNowPlayingMovies() {

        compositeDisposable.add(
            call.getNowPaying()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<NowPlayingMoviesResponse>(){
                    override fun onSuccess(t: NowPlayingMoviesResponse) {
                        view.showNowPlayingData(t.results)
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
                .subscribeWith(object :DisposableSingleObserver<TopRatedMovieResponse>(){
                    override fun onSuccess(t: TopRatedMovieResponse) {
                        view.showTopRatedData(t.results)
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
                .subscribeWith(object :DisposableSingleObserver<PopularMovieResponse>(){
                    override fun onSuccess(t: PopularMovieResponse) {
                        view.showPopularData(t.results)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )
    }
}
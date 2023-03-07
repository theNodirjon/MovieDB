package com.exemple.movie.presenter.impl

import com.exemple.movie.model.network.MovieClient
import com.exemple.movie.model.response.details.detail.DetailResponse
import com.exemple.movie.model.response.details.similar.SimilarMovieResponse
import com.exemple.movie.model.response.details.trailer.TrailerResponse
import com.exemple.movie.presenter.interfaces.DetailPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class PresenterDetail(private val view: DetailPresenter.View):DetailPresenter.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadData(movie_id:Int) {
        loadTrailData(movie_id)
        loadActors(movie_id)
        loadSimilar(movie_id)
        loadDetailsData(movie_id)
    }

    private fun loadDetailsData(movieId: Int) {
        val call = MovieClient.getApiMethods().getDetails(movieId)

        compositeDisposable.add(
            call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DetailResponse>(){
                    override fun onSuccess(t: DetailResponse) {
                        view.showDetails(t)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )
    }

    private fun loadSimilar(movieId: Int) {
        val call = MovieClient.getApiMethods().getSimilarMovies(movieId)

        compositeDisposable.add(
            call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SimilarMovieResponse>(){
                    override fun onSuccess(t: SimilarMovieResponse) {
                        view.showSimilarMovies(t.results)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )
    }

    private fun loadTrailData(movieId: Int) {
        val call = MovieClient.getApiMethods().getMovieTrailer(movieId)

        compositeDisposable.add(
            call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TrailerResponse>(){
                    override fun onSuccess(t: TrailerResponse) {
                        view.showTrailers(t.results)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.message!!)
                    }

                })
        )
    }
    private fun loadActors(movieId: Int) {

    }

    override fun refreshData() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
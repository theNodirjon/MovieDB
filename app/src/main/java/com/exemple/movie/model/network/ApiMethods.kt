package com.exemple.movie.model.network

import com.exemple.movie.model.response.details.detail.DetailResponse
import com.exemple.movie.model.response.home.nowPlaying.NowPlayingMoviesResponse
import com.exemple.movie.model.response.home.popular.PopularMovieResponse
import com.exemple.movie.model.response.search.SearchMovieResponse
import com.exemple.movie.model.response.details.similar.SimilarMovieResponse
import com.exemple.movie.model.response.home.topRated.TopRatedMovieResponse
import com.exemple.movie.model.response.details.trailer.TrailerResponse
import com.exemple.movie.model.response.home.upcoming.UpcomingMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMethods {
    @GET("3/movie/popular?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun getPopularMovies():Single<PopularMovieResponse>
    @GET("/3/movie/top_rated?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun getTopRated(): Single<TopRatedMovieResponse>
    @GET("/3/movie/upcoming?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun getUpcomingMovies():Single<UpcomingMovieResponse>
    @GET("/3/movie/now_playing?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun getNowPaying():Single<NowPlayingMoviesResponse>
    @GET("/3/search/movie?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun searchMovie(
        @Query("query") name:String
    ):Single<SearchMovieResponse>
    @GET("/3/movie/{movie_id}/similar?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-US")
    fun getSimilarMovies(
        @Path("movie_id") movieId:Int
    ):Single<SimilarMovieResponse>
    @GET("/3/movie/{movie_id}/videos?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun getMovieTrailer(
        @Path("movie_id") movieId: Int
    ):Single<TrailerResponse>
    @GET("/3/movie/{movie_id}?api_key=8eda88bccffae411c490a756b5e6cb0b&language=en-EN")
    fun getDetails(
        @Path("movie_id") movieId:Int
    ):Single<DetailResponse>
}
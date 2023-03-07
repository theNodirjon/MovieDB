package com.exemple.movie.model.response.similar


import com.google.gson.annotations.SerializedName

data class SimilarMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
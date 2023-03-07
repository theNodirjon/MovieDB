package com.exemple.movie.model.response.details.trailer


import com.google.gson.annotations.SerializedName
import com.exemple.movie.model.response.base.BaseType

data class Result(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("id")
    val id: String
):BaseType() {
    override fun getType(): Int {
        return TYPE_Trailer
    }
}
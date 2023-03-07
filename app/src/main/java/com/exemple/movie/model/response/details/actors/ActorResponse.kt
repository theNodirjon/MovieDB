package com.exemple.movie.model.response.details.actors


import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>
)
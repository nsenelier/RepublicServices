package com.example.republicservices.domain.model


import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)
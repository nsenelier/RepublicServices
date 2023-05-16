package com.example.republicservices.domain.model


import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
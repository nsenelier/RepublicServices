package com.example.republicservices.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "route_table")
data class RouteEntity(
    @PrimaryKey  val id: Int,
    val name: String,
    val type: String
)

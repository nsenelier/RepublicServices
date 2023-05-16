package com.example.republicservices.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_table")
data class DriverEntity(
    @PrimaryKey val id: Int,
    var name: String
)
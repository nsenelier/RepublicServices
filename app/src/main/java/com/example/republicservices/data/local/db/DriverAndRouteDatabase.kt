package com.example.republicservices.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.republicservices.data.local.DAO.DriverDao
import com.example.republicservices.data.local.DAO.RoutesDao
import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity

@Database(
    entities = [DriverEntity::class, RouteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class DriverAndRouteDatabase: RoomDatabase(){
    abstract fun driverDao(): DriverDao
    abstract fun routeDao(): RoutesDao
}
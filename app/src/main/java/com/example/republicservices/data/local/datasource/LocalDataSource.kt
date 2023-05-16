package com.example.republicservices.data.local.datasource

import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun deleteRouteById(routeId: Int)
    suspend fun deleteDriverById(driverId: Int)

    suspend fun insertDriverToDb(driver: List<DriverEntity>)
    suspend fun insertRouteToDb(route: List<RouteEntity>)

    suspend fun clearDriversFromDb()
    suspend fun clearRoutesFromDb()

    suspend fun searchDriver(query: String): List<DriverEntity>
    suspend fun searchRoute(query: String): List<RouteEntity>

    suspend fun getSingleDriverFromDB(Id: Int): DriverEntity
    suspend fun getSingleRouteFromDB(Id: Int): RouteEntity

    suspend fun getAllDrivers(): List<DriverEntity>
    suspend fun getAllRoutes(): List<RouteEntity>
}
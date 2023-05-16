package com.example.republicservices.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.republicservices.data.local.entity.RouteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(routeEntity: List<RouteEntity>)

    @Query("DELETE FROM route_table")
    suspend fun clearRoutes()

    @Query("SELECT * FROM route_table WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == name")
    suspend fun searchRoute(query: String): List<RouteEntity>

    @Query("DELETE FROM route_table WHERE id = :routeId")
    suspend fun deleteRouteById(routeId: Int)

    @Query("SELECT * FROM route_table WHERE route_table.id == :routeId")
    suspend fun getSingleRouteFromDB(routeId: Int): RouteEntity

    @Query("SELECT * FROM route_table")
    suspend fun getAllRoutes(): List<RouteEntity>
}
package com.example.republicservices.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.republicservices.data.local.entity.DriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDriver(driverEntity: List<DriverEntity>)

    @Query("DELETE FROM driver_table")
    suspend fun clearDrivers()

    @Query("SELECT * FROM driver_table WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == name")
    suspend fun searchDriver(query: String): List<DriverEntity>

    @Query("DELETE FROM driver_table WHERE id = :driverId")
    suspend fun deleteDriverById(driverId: Int)

    @Query("SELECT * FROM driver_table WHERE driver_table.id == :driverId")
    suspend fun getSingleDriverFromDB(driverId: Int): DriverEntity

    @Query("SELECT * FROM driver_table")
    suspend fun getAllDriver(): List<DriverEntity>
}
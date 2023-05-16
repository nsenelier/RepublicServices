package com.example.republicservices.domain.repository

import com.example.republicservices.data.local.datasource.LocalDataSource
import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity
import com.example.republicservices.data.remote.datasource.RemoteDataSource
import com.example.republicservices.domain.model.DataResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

interface RepublicServiceRepository{
     suspend fun getDriversAndRoutes(): Response<DataResponse>
     suspend fun saveDriversAndRoutes(
         driver: List<DriverEntity>,
         route: List<RouteEntity>)

    suspend fun deleteRouteById(routeId: Int)
    suspend fun deleteDriverById(driverId: Int)

    suspend fun clearDrivers()
    suspend fun clearRoutes()

    suspend fun searchDriver(query: String): List<DriverEntity>
    suspend fun searchRoute(query: String): List<RouteEntity>

    suspend fun getSingleDriver(Id: Int): DriverEntity
    suspend fun getSingleRoute(Id: Int): RouteEntity

    suspend fun getAllDrivers(): List<DriverEntity>
    suspend fun getAllRoutes(): List<RouteEntity>

}
@Singleton
class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): RepublicServiceRepository {
    override suspend fun getDriversAndRoutes(): Response<DataResponse> {
        return remoteDataSource.getDriversAndRoutesFromApi()
    }

    override suspend fun saveDriversAndRoutes(driver: List<DriverEntity>, route: List<RouteEntity>) {
        localDataSource.insertDriverToDb(driver) ;
        localDataSource.insertRouteToDb(route)
    }

    override suspend fun deleteRouteById(routeId: Int) =
        localDataSource.deleteRouteById(routeId)

    override suspend fun deleteDriverById(driverId: Int) =
        localDataSource.deleteDriverById(driverId)

    override suspend fun clearDrivers() =
        localDataSource.clearDriversFromDb()

    override suspend fun clearRoutes() =
        localDataSource.clearRoutesFromDb()

    override suspend fun searchDriver(query: String): List<DriverEntity> {
        return localDataSource.searchDriver(query)
    }

    override suspend fun searchRoute(query: String): List<RouteEntity> {
        return localDataSource.searchRoute(query)
    }

    override suspend fun getSingleDriver(Id: Int): DriverEntity {
        return localDataSource.getSingleDriverFromDB(Id)
    }

    override suspend fun getSingleRoute(Id: Int): RouteEntity {
        return localDataSource.getSingleRouteFromDB(Id)
    }

    override suspend fun getAllDrivers(): List<DriverEntity> {
        return localDataSource.getAllDrivers()
    }

    override suspend fun getAllRoutes(): List<RouteEntity> {
        return localDataSource.getAllRoutes()
    }


}
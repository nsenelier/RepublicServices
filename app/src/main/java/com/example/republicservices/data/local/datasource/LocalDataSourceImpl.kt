package com.example.republicservices.data.local.datasource

import com.example.republicservices.data.local.DAO.DriverDao
import com.example.republicservices.data.local.DAO.RoutesDao
import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val driverDao: DriverDao,
    private val routeDao: RoutesDao
): LocalDataSource {
    override suspend fun deleteRouteById(routeId: Int) =
        routeDao.deleteRouteById(routeId)

    override suspend fun deleteDriverById(driverId: Int) =
        driverDao.deleteDriverById(driverId)

    override suspend fun insertDriverToDb(driver: List<DriverEntity>) =
        driverDao.insertDriver(driver)

    override suspend fun insertRouteToDb(route: List<RouteEntity>) =
        routeDao.insertRoute(route)

    override suspend fun clearDriversFromDb() =
        driverDao.clearDrivers()

    override suspend fun clearRoutesFromDb() =
        routeDao.clearRoutes()

    override suspend fun searchDriver(query: String): List<DriverEntity> =
        driverDao.searchDriver(query)

    override suspend fun searchRoute(query: String): List<RouteEntity> =
        routeDao.searchRoute(query)

    override suspend fun getSingleDriverFromDB(Id: Int): DriverEntity =
        driverDao.getSingleDriverFromDB(Id)

    override suspend fun getSingleRouteFromDB(Id: Int): RouteEntity =
        routeDao.getSingleRouteFromDB(Id)

    override suspend fun getAllDrivers(): List<DriverEntity> =
        driverDao.getAllDriver()

    override suspend fun getAllRoutes(): List<RouteEntity> =
        routeDao.getAllRoutes()

}
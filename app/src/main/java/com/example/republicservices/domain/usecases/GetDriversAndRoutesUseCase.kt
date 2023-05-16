package com.example.republicservices.domain.usecases

import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity
import com.example.republicservices.data.mapper.toDriverEntity
import com.example.republicservices.data.mapper.toRouteEntity
import com.example.republicservices.domain.model.DataResponse
import com.example.republicservices.domain.repository.RepublicServiceRepository
import com.example.republicservices.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class GetDriversAndRoutesUseCase @Inject constructor(
    private val repository: RepublicServiceRepository,
    private val dispatcher: CoroutineDispatcher
) {
    private val driversList: MutableList<DriverEntity> = mutableListOf()
    private val routesList: MutableList<RouteEntity> = mutableListOf()

    operator fun invoke(): Flow<UIState<DataResponse>> = flow {
        emit(UIState.Loading)

        try {
            val response = repository.getDriversAndRoutes()
            if(response.isSuccessful){
                 response.body()?.let{
                     it.drivers.forEach { driver ->
                         driversList.add(driver.toDriverEntity())
                     }
                     it.routes.forEach {route ->
                         routesList.add(route.toRouteEntity())
                     }
                     val localList = repository.getAllRoutes()
                     val localListD = repository.getAllDrivers()
                     if(localList.isEmpty() && localListD.isEmpty()){
                         repository.saveDriversAndRoutes(
                             driver = driversList,
                             route = routesList
                         )
                     }

                     emit(UIState.Success(it))
                 } ?: throw Exception("Response is null")

            } else {
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: Exception){
            emit(UIState.Failure(errorMessage = e))
        }
    }.flowOn(dispatcher)


}
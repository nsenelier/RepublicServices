package com.example.republicservices.data.remote.datasource

import com.example.republicservices.data.remote.api.RSApi
import com.example.republicservices.domain.model.DataResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val driversAndRoutesApi: RSApi
): RemoteDataSource {

    override suspend fun getDriversAndRoutesFromApi(): Response<DataResponse> =
            driversAndRoutesApi.getDriversAndRoutes()

}
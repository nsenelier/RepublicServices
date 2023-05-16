package com.example.republicservices.data.remote.datasource

import com.example.republicservices.domain.model.DataResponse

import retrofit2.Response

interface RemoteDataSource {
    suspend fun getDriversAndRoutesFromApi(): Response<DataResponse>
}
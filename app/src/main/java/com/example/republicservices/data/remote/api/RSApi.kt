package com.example.republicservices.data.remote.api

import com.example.republicservices.domain.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface RSApi {

    @GET(ENDPOINT)
    suspend fun getDriversAndRoutes(): Response<DataResponse>

    companion object{
        const val BASE_URL = "https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/"
        const val ENDPOINT = "data"
    }
}
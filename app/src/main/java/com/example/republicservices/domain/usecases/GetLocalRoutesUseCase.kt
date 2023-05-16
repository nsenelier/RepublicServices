package com.example.republicservices.domain.usecases

import com.example.republicservices.data.local.entity.RouteEntity
import com.example.republicservices.domain.repository.RepublicServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalRoutesUseCase @Inject constructor(
    private val repository: RepublicServiceRepository
) {
    suspend fun getData(): List<RouteEntity>{
        return repository.getAllRoutes()
    }
}
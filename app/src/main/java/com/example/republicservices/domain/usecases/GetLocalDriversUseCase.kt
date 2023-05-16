package com.example.republicservices.domain.usecases

import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.domain.repository.RepublicServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalDriversUseCase @Inject constructor(
    private val repository: RepublicServiceRepository
) {
    suspend fun getData(): List<DriverEntity> {
        return repository.getAllDrivers()
    }

}
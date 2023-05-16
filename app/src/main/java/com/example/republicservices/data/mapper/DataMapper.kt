package com.example.republicservices.data.mapper

import com.example.republicservices.data.local.entity.DriverEntity
import com.example.republicservices.data.local.entity.RouteEntity
import com.example.republicservices.domain.model.Driver
import com.example.republicservices.domain.model.Route

fun Driver.toDriverEntity(): DriverEntity{
    return  DriverEntity(
        id = id.toInt(),
        name = name
    )
}

fun Route.toRouteEntity(): RouteEntity{
    return RouteEntity(
        id = id,
        name = name,
        type = type
    )
}
package com.example.sitroutedriverapp.models

data class Route(
    val idRoute: Int,
    val name: String,
    val isBacked: Boolean,
    val startTime: String,
    val endTime: String,
    val buses: List<Bus>,
    val routeByBusStations: List<RouteByBusStations>
)
package com.example.sitroutedriverapp.models

data class RouteByBusStations (
    val idRoute: Int,
    val idBusStation: Int,
    val serialNumberBusStation: Int,
    val standardArrivalTime: String,
    val busStation: BusStation,
    val route: Route
)
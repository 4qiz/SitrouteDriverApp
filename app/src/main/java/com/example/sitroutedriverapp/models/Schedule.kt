package com.example.sitroutedriverapp.models

data class Schedule(
    var idSchedule: Int,
    var idBus: Int,
    var idBusStation: Int,
    var time: String,
    var peopleCountBoardingBus: Int?,
    var peopleCountGettingOffBus: Int?,
    var idBusNavigation: Bus,
    var idBusStationNavigation: BusStation
)
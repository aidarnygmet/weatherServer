package com.example.weatherServer

import jakarta.persistence.*
@Entity
@Table(name = "work_hourly")
data class WorkHourly(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val lat: Double,
        val lon: Double,
        val date: String,
        val time: String,
        val temp: Double,
        val feels_like: Double,
        val pressure: Int,
        val humidity: Int,
        val dew_point: Double,
        val uvi: Double,
        val clouds: Int,
        val visibility: Int,
        val wind_speed: Double,
        val wind_deg: Int,
        val pop: Double
)

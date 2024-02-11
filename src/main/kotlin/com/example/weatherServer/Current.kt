package com.example.weatherServer

import jakarta.persistence.*

@Entity
data class Current(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "forecast_id")
        var forecast: ForecastData?,
        val dt: Long,
        val sunrise: Long,
        val sunset: Long,
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
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "current", orphanRemoval = true)
        var weather: List<CurrentWeather>,
)

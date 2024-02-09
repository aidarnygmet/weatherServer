package com.example.weatherServer

import jakarta.persistence.*

@Entity
data class Daily(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "forecast_id")
        var forecast: ForecastData,
        val dt: Long,
        val sunrise: Long,
        val sunset: Long,
        val moonrise: Long,
        val moonset: Long,
        val moon_phase: Double,
        val summary: String,
        @OneToOne(cascade = [CascadeType.ALL], mappedBy = "daily", orphanRemoval = true)
        val temp: Temp,
        @OneToOne(cascade = [CascadeType.ALL], mappedBy = "daily", orphanRemoval = true)
        val feels_like: FeelsLike,
        val pressure: Int,
        val humidity: Int,
        val dew_point: Double,
        val wind_speed: Double,
        val wind_deg: Int,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "daily", orphanRemoval = true)
        val weather: List<DailyWeather>,
        val clouds: Int,
        val pop: Double,
        val rain: Double,
        val uvi: Double,
)

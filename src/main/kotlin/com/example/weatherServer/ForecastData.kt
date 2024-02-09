package com.example.weatherServer

import jakarta.persistence.*


@Entity
@Table(name = "forecast_data")
data class ForecastData(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(nullable = false)
        val lat: Float,
        @Column(nullable = false)
        val lon: Float,
        @Column(nullable = false)
        val timezone: String,
        @Column(nullable = false)
        val timezone_offset: String,
        @OneToOne(cascade = [CascadeType.ALL], mappedBy = "forecast", orphanRemoval = true)
        val current: Current,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "forecast", orphanRemoval = true)
        val hourly: List<Hourly>,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "forecast", orphanRemoval = true)
        val daily: List<Daily>,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "forecast", orphanRemoval = true)
        val alerts: List<Alerts>
)

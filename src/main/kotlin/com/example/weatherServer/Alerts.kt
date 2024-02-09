package com.example.weatherServer

import jakarta.persistence.*

@Entity
data class Alerts (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "forecast_id")
        var forecast: ForecastData,
        val sender_name: String,
        val event: String,
        val start: Long,
        val end: Long,
        val description: String,
        val tags: List<String>
)

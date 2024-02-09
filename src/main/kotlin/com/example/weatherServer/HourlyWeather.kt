package com.example.weatherServer

import jakarta.persistence.*

@Entity
data class HourlyWeather(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val entry_id: Long?=null,

        val id: Int,
        val main: String,
        val description: String,
        val icon: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "hourly_id")
        var hourly: Hourly,
)

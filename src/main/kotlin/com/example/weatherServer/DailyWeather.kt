package com.example.weatherServer

import jakarta.persistence.*

@Entity
data class DailyWeather(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val entry_id: Long?=null,

        val id: Int,
        val main: String,
        val description: String,
        val icon: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "daily_id")
        var daily: Daily?,
)

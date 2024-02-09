package com.example.weatherServer

import jakarta.persistence.*

@Entity
data class Temp(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "daily_id")
        var daily: Daily,
        val day: Double,
        val min: Double,
        val max: Double,
        val night: Double,
        val eve: Double,
        val morn: Double,

)

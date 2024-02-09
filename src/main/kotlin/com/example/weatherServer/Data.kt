package com.example.weatherServer

import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "data")
data class Data(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "timestamp_id")
        var timestamp: TimestampData,
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
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "data", orphanRemoval = true)
        var weather: List<Weather>,
)
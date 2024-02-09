package com.example.weatherServer

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "location")
data class Location(
        @Id
        @Column(name = "name", nullable = false, unique = true)
        val name: String,
        @Column(name = "lat", nullable = false)
        val lat: Double,
        @Column(name = "lon", nullable = false)
        val lon: Double
)

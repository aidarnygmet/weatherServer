package com.example.weatherServer


import jakarta.persistence.*

@Entity
@Table(name = "timestamp_data")
data class TimestampData(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val lat: Double,
        @Column(nullable = false)
        val lon: Double,
        @Column(nullable = false)
        val timezone: String,
        @Column(nullable = false)
        val timezone_offset: String,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "timestamp", orphanRemoval = true)
        var data: List<Data>
)

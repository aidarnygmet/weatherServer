package com.example.weatherServer

import org.springframework.data.jpa.repository.JpaRepository

interface ForecastDataRepository: JpaRepository<ForecastData, Long>
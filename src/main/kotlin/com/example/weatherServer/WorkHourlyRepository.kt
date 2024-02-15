package com.example.weatherServer

import org.springframework.data.jpa.repository.JpaRepository

interface WorkHourlyRepository : JpaRepository<WorkHourly, Long> {
}
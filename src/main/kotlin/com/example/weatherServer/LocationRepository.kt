package com.example.weatherServer

import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository: JpaRepository<Location, Long>
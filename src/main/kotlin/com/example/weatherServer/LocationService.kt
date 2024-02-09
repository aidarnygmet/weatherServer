package com.example.weatherServer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationService {
    @Autowired
    private lateinit var repo : LocationRepository
    fun save(entity: Location): Location {
        return repo.save(entity)
    }
    fun getAll(): List<Location>{
        return repo.findAll()
    }
}
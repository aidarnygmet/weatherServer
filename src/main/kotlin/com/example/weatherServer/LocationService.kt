package com.example.weatherServer

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationService {
    @Autowired
    private lateinit var repo : LocationRepository
    @Autowired
    private lateinit var entityManager : EntityManager
    fun save(entity: Location): Location {
        return repo.save(entity)
    }
    fun getAll(): List<Location>{
        return repo.findAll()
    }

    fun getByName(name: String): Location{
        return try {
            val query = entityManager.createQuery("SELECT loc FROM Location loc WHERE loc.name = :name")
            query.setParameter("name", name)
            query.singleResult as Location
        } catch (e: Exception){
            println("error in location: $e")
           Location("", 0.0, 0.0)
        }
    }
}
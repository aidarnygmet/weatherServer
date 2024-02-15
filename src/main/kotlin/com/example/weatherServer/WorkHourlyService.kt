package com.example.weatherServer

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service

@Service
class WorkHourlyService {
    @Autowired
    private lateinit var repo: WorkHourlyRepository
    @Autowired
    private lateinit var entityManager: EntityManager

    fun save(entity: List<WorkHourly>){
        repo.deleteAll()
        entity.forEach {
            repo.save(it)
        }
    }
    fun getByCoordinates(@Param("lat") lat: Double, @Param("lon") lon: Double): List<WorkHourly>?{
        return try {

            val query = entityManager.createQuery("SELECT w FROM WorkHourly w WHERE w.lat = :lat AND w.lon = :lon")
            query.setParameter("lat", lat)
            query.setParameter("lon", lon)
            query.resultList as List<WorkHourly>
        } catch (e: Exception){
            println("error in forecast: $e")
            null
        }
    }
}
package com.example.weatherServer

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimestampEntityService {
    @Autowired
    private lateinit var repo : TimestampDataRepository
    @Autowired
    private lateinit var entityManager : EntityManager
    fun save(entity: TimestampData): TimestampData {
        entity.data.forEach{thisData->
            thisData.weather.forEach{thisWeather ->
                thisWeather.data = thisData
            }
            thisData.timestamp = entity
        }

        return repo.save(entity)
    }
    fun getLastdt(lat: Double, lon: Double): Long{
        return try {
            val query = entityManager.createQuery("SELECT d.dt FROM Data d JOIN d.timestamp t WHERE t.lat = :lat AND t.lon = :lon ORDER BY d.id DESC LIMIT 1")
            query.setParameter("lat", lat)
            query.setParameter("lon", lon)
            query.singleResult as Long
        } catch (e: Exception){
            1704092400
        }

    }
}
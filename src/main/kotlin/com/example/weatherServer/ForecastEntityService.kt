package com.example.weatherServer

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service

@Service
class ForecastEntityService {
    @Autowired
    private lateinit var repo : ForecastDataRepository
    @Autowired
    private lateinit var entityManager: EntityManager
    fun save(entity: ForecastData): ForecastData {

        entity.alerts?.forEach { alert->
            alert.forecast = entity
        }
        entity.current.forecast = entity
        entity.current.weather.forEach { currentWeather ->
            currentWeather.current = entity.current
        }
        entity.hourly.forEach { hourly ->
            hourly.forecast = entity
            hourly.weather.forEach { hourlyWeather ->
                hourlyWeather.hourly = hourly
            }
        }
        entity.daily.forEach { daily->
            daily.forecast = entity
            daily.weather.forEach { dailyWeather ->
                dailyWeather.daily = daily
            }
            daily.feels_like?.daily = daily
            daily.temp?.daily = daily
        }
        //println(entity)
        return repo.save(entity)
    }
    fun deleteAll(){
        repo.deleteAll()
    }
    fun findByLatAndLon(@Param("lat") lat: Double, @Param("lon") lon: Double): List<ForecastData>?{
        return try {
            val query = entityManager.createQuery("SELECT fd FROM ForecastData fd WHERE fd.lat = :lat AND fd.lon = :lon")
            query.setParameter("lat", lat)
            query.setParameter("lon", lon)
            query.resultList as List<ForecastData>
        } catch (e: Exception){
            println("error in forecast: $e")
            null
        }
    }
}
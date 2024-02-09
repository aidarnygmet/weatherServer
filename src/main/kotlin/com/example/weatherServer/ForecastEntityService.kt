package com.example.weatherServer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ForecastEntityService {
    @Autowired
    private lateinit var repo : ForecastDataRepository
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
            daily.feels_like.daily = daily
            daily.temp.daily = daily
        }
        //println(entity)
        return repo.save(entity)
    }
    fun deleteAll(){
        repo.deleteAll()
    }
}
package com.example.weatherServer

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class ForecastEntityService {
    @Autowired
    private lateinit var repo : ForecastDataRepository
    @Autowired
    private lateinit var entityManager: EntityManager
    @Autowired
    private lateinit var workHourlyService: WorkHourlyService

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
    fun findByLatAndLon(@Param("lat") lat: Double, @Param("lon") lon: Double): List<WorkHourly>?{
        return try {

            val query = entityManager.createQuery("SELECT h FROM ForecastData f JOIN f.hourly h JOIN (" +
                    "SELECT h2.dt AS dt, MAX(h2.forecast.id) AS max_forecast_id " +
                    "FROM Hourly h2 GROUP BY h2.dt) AS max_ids " +
                    "ON h.dt = max_ids.dt AND h.forecast.id = max_ids.max_forecast_id " +
                    "WHERE f.lat = :lat AND f.lon = :lon")
            query.setParameter("lat", lat)
            query.setParameter("lon", lon)
            hourlyToWorkHourly(lat, lon, query.resultList as List<Hourly>)
        } catch (e: Exception){
            println("error in forecast: $e")
            null
        }
    }
    fun findByLatAndLonDirectly(@Param("lat") lat: Double, @Param("lon") lon: Double): List<Hourly>?{
        return try {

            val query = entityManager.createQuery("SELECT h " +
                    "FROM ForecastData f " +
                    "JOIN f.hourly h  " +
                    "JOIN (" +
                    "   SELECT h2.dt AS dt, MAX(h2.forecast.id) AS max_forecast_id " +
                    "   FROM Hourly h2 " +
                    "   JOIN ForecastData f2 ON h2.forecast.id = f2.id " +
                    "   WHERE f2.lat = :lat AND f2.lon = :lon " +
                    "   GROUP BY h2.dt" +
                    ") AS max_ids ON h.dt = max_ids.dt AND h.forecast.id = max_ids.max_forecast_id " +
                    "WHERE f.lat = :lat AND f.lon = :lon")
            query.setParameter("lat", lat)
            query.setParameter("lon", lon)
            query.resultList as List<Hourly>
        } catch (e: Exception){
            println("error in forecastDirectly: $e")
            null
        }
    }
    fun hourlyToWorkHourly(lat: Double, lon: Double, hourly: List<Hourly>?): List<WorkHourly>?{
        return hourly?.map { WorkHourly(lat = lat, lon = lon,
                temp = it.temp,
                feels_like = it.feels_like,
                pressure = it.pressure,
                humidity = it.humidity,
                dew_point = it.dew_point,
                uvi = it.uvi,
                clouds = it.clouds,
                visibility = it.visibility,
                wind_deg = it.wind_deg,
                wind_speed = it.wind_speed,
                pop = it.pop,
                date = convertTimestampToDate(it.dt),
                time = convertTimestampToTime(it.dt)
                ) }
    }
    fun convertTimestampToDate(timestamp: Long): String {
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return dateTime.format(formatter)
    }
    fun convertTimestampToTime(timestamp: Long): String {
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return dateTime.format(formatter)
    }
}
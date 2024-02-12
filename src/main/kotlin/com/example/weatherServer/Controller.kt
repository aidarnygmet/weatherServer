package com.example.weatherServer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller (private val weatherApiService: WeatherService,
        private val timestampEntityService: TimestampEntityService,
        private val forecastEntityService: ForecastEntityService,
        private val locationService: LocationService
        ){
        private val apiKey = "48dd284900f5f0d8e2116acda4cfbdb0"
        private val exclude = "minutely"
        private val lang = "ru"
        private val units = "metric"



    suspend fun saveTimestampData(lat: Double, lon: Double, dt: Long){
        val data = weatherApiService.getTimestampData(lat, lon, dt, units, lang,apiKey)
        timestampEntityService.save(data)
    }

    suspend fun saveForecastData(lat: Double, lon: Double){
        val data = weatherApiService.getForecastData(lat,lon,exclude, units,lang,apiKey)
        forecastEntityService.save(data)
    }

    suspend fun getLastdt(lat: Double, lon: Double): Long{
//        return 1704092400
        return timestampEntityService.getLastdt(lat, lon)
    }
    @PostMapping("save/location")
    suspend fun saveLocation(loc: Location){
        locationService.save(loc)
    }

    suspend fun getAllLocation():List<Location>{
        return locationService.getAll()
    }

    @GetMapping("get/forecast/{name}")
    suspend fun getForecast(@PathVariable name: String):List<Hourly>?{
        val loc = locationService.getByName(name)
        val forecast = forecastEntityService.findByLatAndLon(loc.lat, loc.lon)
        forecast?.forEach {
            it.current.forecast = null
            it.current.weather.forEach { w->
                w.current = null
            }
            it.hourly.forEach { h->
                h.forecast = null
                h.weather.forEach { w->
                    w.hourly = null
                }
            }
            it.daily.forEach { d->
                d.forecast = null
                d.weather.forEach { w->
                    w.daily = null
                }
                d.temp?.daily = null
                d.feels_like?.daily = null
            }
        }
        if(forecast != null){
        val selectedHourlyList: List<Hourly> = forecast.dropLast(1) // Exclude the last forecast object
                .flatMap { f ->
                    f.hourly.take(12) // Take 12 hourly objects from each forecast except the last one
                } + forecast.last().hourly
            return selectedHourlyList
        }
        return null
    }


}
package com.example.weatherServer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
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


    @PostMapping("/save/timestamp")
    suspend fun saveTimestampData(lat: Double, lon: Double, dt: Long){
        val data = weatherApiService.getTimestampData(lat, lon, dt, units, lang,apiKey)
        timestampEntityService.save(data)
    }
    @PostMapping("/save/forecast")
    suspend fun saveForecastData(lat: Double, lon: Double){
        val data = weatherApiService.getForecastData(lat,lon,exclude, units,lang,apiKey)
        forecastEntityService.save(data)
    }
    @GetMapping("get/timestamp/last")
    suspend fun getLastdt(lat: Double, lon: Double): Long{
//        return 1704092400
        return timestampEntityService.getLastdt(lat, lon)
    }
    @PostMapping("save/location")
    suspend fun saveLocation(loc: Location){
        locationService.save(loc)
    }
    @GetMapping("getAll/location")
    suspend fun getAllLocation():List<Location>{
        return locationService.getAll()
    }


}
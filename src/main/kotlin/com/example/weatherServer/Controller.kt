package com.example.weatherServer

import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.*

@RestController
class Controller (private val weatherApiService: WeatherService,
        private val timestampEntityService: TimestampEntityService,
        private val forecastEntityService: ForecastEntityService,
        private val locationService: LocationService,
        private val workHourlyService: WorkHourlyService
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
        runBlocking {
            val data = weatherApiService.getForecastData(lat,lon,exclude, units,lang,apiKey)
            forecastEntityService.save(data)
            val workHourly = forecastEntityService.findByLatAndLon(lat, lon)
            if (workHourly != null) {
                println("saveForecastData: workHourly size: ${workHourly.size}")
                workHourlyService.save(workHourly)
            }

        }

    }

    suspend fun getLastdt(lat: Double, lon: Double): Long{
//        return 1704092400
        return timestampEntityService.getLastdt(lat, lon)
    }
    @PostMapping("save/location")
    suspend fun saveLocation(loc: Location){
        locationService.save(loc)
    }
    @GetMapping("get/locations")
    suspend fun getAllLocation():List<Location>{
        return locationService.getAll()
    }
    @CrossOrigin(origins = ["http://localhost:3002"])
    @GetMapping("get/forecast/{name}")
    suspend fun getForecast(@PathVariable name: String):List<WorkHourly>?{
        val loc = locationService.getByName(name)
        println(loc)
        return workHourlyService.getByCoordinates(loc.lat, loc.lon)
    }



}
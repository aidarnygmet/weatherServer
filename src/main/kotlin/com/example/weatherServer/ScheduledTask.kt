package com.example.weatherServer

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTask(
        private val weatherService: WeatherService, private val timestampEntityService: TimestampEntityService,
        private val forecastEntityService: ForecastEntityService,
        private val locationService: LocationService
) {
    val controller = Controller(weatherService, timestampEntityService, forecastEntityService, locationService)
    lateinit var locations: List<Location>
    @Scheduled(cron = "0 0 8 * * *")
    suspend fun morningTask(){
        locations = controller.getAllLocation()
        locations.forEach { loc->
            controller.saveForecastData(loc.lat, loc.lon)
        }
    }
    @Scheduled(cron = "0 0 20 * * *")
    suspend fun eveningTask(){
        locations = controller.getAllLocation()
        locations.forEach { loc->
            controller.saveForecastData(loc.lat, loc.lon)
        }
    }
}
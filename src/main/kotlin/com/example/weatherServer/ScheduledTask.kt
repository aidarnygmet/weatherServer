package com.example.weatherServer

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTask(
        private val weatherService: WeatherService, private val timestampEntityService: TimestampEntityService,
        private val forecastEntityService: ForecastEntityService,
        private val locationService: LocationService,
        private val workHourlyService: WorkHourlyService
) {
    val controller = Controller(weatherService, timestampEntityService, forecastEntityService, locationService, workHourlyService)
    lateinit var locations: List<Location>
//    @Scheduled(cron = "0 0 8 * * *", zone = "Asia/Almaty")
//    suspend fun morningTask(){
//        locations = controller.getAllLocation()
//        locations.forEach { loc->
//            controller.saveForecastData(loc.lat, loc.lon)
//        }
//    }
    @Scheduled(cron = "0 0 8 * * *", zone = "Asia/Almaty")
    suspend fun eveningTask(){
        locations = controller.getAllLocation()
        locations.forEach { loc->
            controller.saveForecastData(loc.lat, loc.lon)
        }
        println(controller.getForecast("Astana1"))
    }
}
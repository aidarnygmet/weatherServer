package com.example.weatherServer

import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(private val weatherService: WeatherService, private val timestampEntityService: TimestampEntityService,
        private val forecastEntityService: ForecastEntityService,
        private val locationService: LocationService): CommandLineRunner {
    override fun run(vararg args: String?) {
//        val controller = Controller(weatherService, timestampEntityService, forecastEntityService, locationService)
//        lateinit var locations: List<Location>


        runBlocking {
//            locations = controller.getAllLocation()
//            locations.forEach { loc->
////                var start = controller.getLastdt(loc.lat, loc.lon)+3600L
////
////                val end = System.currentTimeMillis()/1000
//                controller.saveForecastData(loc.lat, loc.lon)
////                for(dt in start until end step step){
////                    controller.saveTimestampData(loc.lat, loc.lon, dt)
////                }
//            }
//            val l = controller.getForecast("Astana1")
//            if (l != null) {
//                l.forEach {
//                    println("forecast: $it")
//                }
//            } else {
//                println("forecast null")
//            }

        }
//        runBlocking {
//            for (dt in start until end step 3000)
//            controller.saveTimestampData(city.first, city.second, dt)
//        }

    }
}
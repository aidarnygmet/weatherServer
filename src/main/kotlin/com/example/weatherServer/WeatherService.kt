package com.example.weatherServer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Retrofit

@Service
class WeatherService(private val retrofit: Retrofit):WeatherAPIService {

//    private val apiKey = ""
//    private val exclude = "minutely"
//    private val lang = "ru"
//    private val units = "metric"


//    suspend fun fetchTimestampData(lat: Double, lon: Double, dt: Long): TimestampData {
//        return weatherApiService.getTimestampData(lat, lon, dt, units, lang, apiKey)
//    }
    override suspend fun getTimestampData(lat: Double, lon: Double, dt: Long, units:String, lang: String, apiKey: String): TimestampData {
        val api = retrofit.create(WeatherAPIService::class.java)
        val data = api.getTimestampData(lat, lon, dt, units, lang, apiKey)

        //println(data)
        return data
    }

    override suspend fun getForecastData(lat: Double, lon: Double, exclude: String, units: String, lang: String, apiKey: String): ForecastData {
        val api = retrofit.create(WeatherAPIService::class.java)
        val data = api.getForecastData(lat, lon, exclude, units, lang, apiKey)
        println(data)
        return data
    }
//    suspend fun fetchForecastData(lat: Double, lon: Double): ForecastData {
//        return weatherApiService.getForecastData(lat, lon, exclude, units, lang,apiKey)
//    }
}
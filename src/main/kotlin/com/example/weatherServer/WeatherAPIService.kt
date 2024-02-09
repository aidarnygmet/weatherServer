package com.example.weatherServer

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    @GET("data/3.0/onecall/timemachine")
    suspend fun getTimestampData(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("dt") dt: Long,
            @Query("units") units: String,
            @Query("lang") lang: String,
            @Query("appid") apiKey: String
    ): TimestampData

    @GET("data/3.0/onecall")
    suspend fun getForecastData(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("exclude") exclude: String,
            @Query("units") units: String,
            @Query("lang") lang: String,
            @Query("appid") apiKey: String
    ): ForecastData
}
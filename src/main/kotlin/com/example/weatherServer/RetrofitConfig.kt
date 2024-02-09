package com.example.weatherServer

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class RetrofitConfig {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    @Bean
    fun retrofit(): Retrofit {
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/") // Set your base URL here
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    @Bean
    fun weatherApiService(retrofit: Retrofit): WeatherAPIService {
        return retrofit.create(WeatherAPIService::class.java)
    }

//    @Bean
//    fun weatherApiServiceImpl(retrofit: Retrofit): WeatherService {
//        return WeatherService(retrofit)
//    }
}

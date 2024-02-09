package com.example.weatherServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherServerApplication

fun main(args: Array<String>) {
	runApplication<WeatherServerApplication>(*args)
}
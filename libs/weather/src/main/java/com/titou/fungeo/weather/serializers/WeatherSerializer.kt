package com.titou.fungeo.weather.serializers

import com.titou.database.models.*
import com.titou.fungeo.weather.cqrs.dtos.*
import java.lang.Exception
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


fun WeatherResponseDto.toWeatherModel(): Weather {
        return Weather(
            city = "",
            latitude = latitude,
            longitude = longitude,
            currentWeather = currentWeather?.toCurrentWeatherModel(),
            hourlyWeatherForecast = hourlyWeatherForecast?.map { it?.toHourlyWeatherModel() }
                ?.requireNoNulls() ?: throw Exception("No hourly weather forecast available"),
            weatherForecast = dailyWeatherForecast?.map { it?.toDatedWeatherForecastModel() }
                ?.requireNoNulls() ?: throw Exception("No weather forecast available")
        )
    }

    //TODO: centralize and handle exceptions
    // display toaster when exception is thrown
//    fun LocationResponseDto.toLocationModel(): LocationWithName {
//        return LocationWithName(
//            longitude = longitude ?: throw Exception("Location longitude cannot be null"),
//            latitude = latitude ?: throw Exception("Location latitude cannot be null")
//        )
//    }

    fun CurrentWeatherResponseDto.toCurrentWeatherModel(): CurrentWeather {
        return CurrentWeather(
            dateTime = LocalDateTime.now(),
            weatherDescriptions = weatherDescriptions?.map { it?.toWeatherDescriptionModel() }
                ?.requireNoNulls() ?: throw Exception("Weather descriptions cannot be null"),
            temperature = temperature ?: throw Exception("Temperature cannot be null"),
            perceivedTemperature = feelsLike
                ?: throw Exception("Perceived temperature cannot be null")
        )
    }

    fun WeatherDescriptionResponseDto.toWeatherDescriptionModel(): WeatherDescription {
        return WeatherDescription(
            main = main,
            description = description
        )
    }

    fun TemperatureForecastResponseDto.toTemperatureForecastModel(): TemperatureForecast {
        return TemperatureForecast(
            day, min, max, night
        )
    }

    fun PerceivedTemperatureForecastResponseDto.toPerceivedTemperatureModel(): PerceivedTemperatureForecast {
        return PerceivedTemperatureForecast(day, night)
    }

    fun DailyWeatherResponseDto.toDatedWeatherForecastModel(): DatedWeatherForecast {
        return DatedWeatherForecast(
            date = dateTime?.toLong()
                ?.let { Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()).toLocalDate() }
                ?: throw Exception("Date cannot be null"),
            temperature = temp?.toTemperatureForecastModel(),
            perceivedTemperature = perceivedTemperature?.toPerceivedTemperatureModel(),
            weatherDescriptions = weatherDescription.map{ it?.toWeatherDescriptionModel()}.requireNoNulls()
        )
    }

    //TODO: Harmonize feelsLike vs Perceived temp names
    fun HourlyWeatherResponseDto.toHourlyWeatherModel(): HourlyWeather {
        return HourlyWeather(
            dateTime =  LocalDateTime.ofEpochSecond(dateTime.toLong(), 0, ZoneOffset.UTC)
                ?: throw Exception("Date cannot be null"),
            weatherDescriptions = weatherDescriptions?.map { it?.toWeatherDescriptionModel() }
                ?.requireNoNulls() ?: throw Exception("Weather descriptions cannot be null"),
            temperature = temperature ?: throw Exception("Temperature cannot be null"),
            feelsLike = feelsLike ?: throw Exception("Perceived temperature cannot be null")
        )
    }


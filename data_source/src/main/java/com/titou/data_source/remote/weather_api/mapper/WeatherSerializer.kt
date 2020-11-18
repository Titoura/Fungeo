package com.titou.data_source.remote.weather_api.mapper

import com.fungeo.data.entity.*
import com.titou.data_source.remote.weather_api.dtos.*
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


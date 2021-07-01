package com.barun.weather.data.response.weather

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherData(

    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_offset") val timezoneOffset: Int,
    @SerialName("current") val current: Current,
    @SerialName("minutely") val minutely: List<Minutely> =  mutableListOf(),
    @SerialName("hourly") val hourly: List<Hourly>,
    @SerialName("daily") val daily: List<Daily>
)
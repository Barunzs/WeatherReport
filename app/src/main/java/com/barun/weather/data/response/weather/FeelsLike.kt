package com.barun.weather.data.response.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Feels_like")
data class FeelsLike(

    @SerialName("day") val day : Double,
    @SerialName("night") val night : Double,
    @SerialName("eve") val eve : Double,
    @SerialName("morn") val morn : Double
)
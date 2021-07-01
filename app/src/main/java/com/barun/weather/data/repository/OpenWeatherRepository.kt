package com.barun.weather.data.repository

import android.util.Log
import com.barun.weather.data.persistance.WeatherDBRepo
import com.barun.weather.data.response.weather.WeatherData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class OpenWeatherRepository @Inject constructor(
    private var apiService: OpenWeatherDataApi,
    private var dbService: WeatherDBRepo
) : BaseRepository() {

    val TAG = OpenWeatherRepository::class.java.simpleName

    val coroutineErrorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, exception.printStackTrace().toString())
    }


    fun insertWeatherInfo(weatherInfo: WeatherData) {
        dbService.weatherInfoDao.insert(weatherInfo)
    }


    suspend fun getWeather(
        lat: String,
        lon: String,
        app_id: String
    ) = safeApiCall {
        apiService.getPost(lat, lon, app_id)
    }
}

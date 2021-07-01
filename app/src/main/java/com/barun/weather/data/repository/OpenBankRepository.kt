package com.barun.weather.data.repository

import android.util.Log
import com.barun.weather.data.persistance.WeatherDBRepo
import com.barun.weather.data.response.weather.Weather
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class OpenBankRepository @Inject constructor(
    private var apiService: OpenWeatherDataApi,
    private var dbService: WeatherDBRepo
) : BaseRepository() {

    val TAG = OpenBankRepository::class.java.simpleName

    val coroutineErrorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, exception.printStackTrace().toString())
    }


    fun insertWeatherInfo(weatherInfo: Weather) {
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

package com.barun.weather.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barun.weather.data.repository.OpenBankRepository
import com.barun.weather.data.response.weather.Resource
import com.barun.weather.data.response.weather.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@HiltViewModel
class DashBoardViewModel @Inject constructor(val repository: OpenBankRepository) : ViewModel() {

    private val TAG = DashBoardViewModel::class.java.simpleName
    private val _weatherResponse: MutableLiveData<Resource<WeatherData>> = MutableLiveData()
    val weatherResponse: LiveData<Resource<WeatherData>>
        get() = _weatherResponse


    fun getWeather(
        lat: String,
        lon: String,
        app_id: String
    ) = viewModelScope.launch {
        _weatherResponse.value = repository.getWeather(lat, lon, app_id)
    }
}


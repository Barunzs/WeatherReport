package com.barun.weather.data.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.barun.weather.data.response.weather.Weather
import com.barun.weather.data.response.weather.WeatherData

@Dao
interface WeatherInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg weatherInfo: WeatherData)
}
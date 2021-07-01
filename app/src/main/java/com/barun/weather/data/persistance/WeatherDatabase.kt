package com.barun.weather.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barun.weather.data.response.weather.Weather
import com.barun.weather.data.response.weather.WeatherData

@Database(entities = [WeatherData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao
}
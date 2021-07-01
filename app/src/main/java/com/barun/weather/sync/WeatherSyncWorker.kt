package com.barun.weather.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.barun.weather.data.repository.OpenBankRepository
import com.barun.weather.data.response.Resource
import com.barun.weather.data.response.weather.WeatherData
import com.google.android.gms.location.LocationServices
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@HiltWorker
class WeatherSyncWorker @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val openBankRepository: OpenBankRepository
) : CoroutineWorker(appContext, workerParams) {

    private val TAG = WeatherSyncWorker::class.java.canonicalName

    override suspend fun doWork(): Result {
        Log.d(TAG, "1 Thread${Thread.currentThread().name}")
        try {
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(appContext)
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    val lastKnownLocation = task.result
                    CoroutineScope(Dispatchers.IO).launch {
                        val result: Resource<WeatherData> =
                            openBankRepository.getWeather(
                                lastKnownLocation.latitude.toString(),
                                lastKnownLocation.longitude.toString(),
                                "6d518c5daeccfffbf4a8128394d4b4c7"
                            )
                        if(result is Resource.Success){
                           
                        }
                        Log.d(TAG, "result got::${result}")
                    }
                }
            }

        } catch (securityException: SecurityException) {
            Log.d(TAG, "securityException")
        }
        Log.d(TAG, "Result.success()")
        return Result.success()
    }
}

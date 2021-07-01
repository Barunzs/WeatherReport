package com.barun.weather.sync

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.barun.weather.data.repository.OpenBankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ExperimentalSerializationApi
@HiltViewModel
class SyncViewModel @Inject constructor(
    val repository: OpenBankRepository,
    application: Application
) : ViewModel() {

    private val workManager = WorkManager.getInstance(application)
    private val TAG_OUTPUT = SyncViewModel::class.java.canonicalName

    fun startWork() {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<WeatherSyncWorker>(
            30, TimeUnit.MINUTES,
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()

        workManager.cancelAllWork()
        workManager.enqueue(work)


    }
}
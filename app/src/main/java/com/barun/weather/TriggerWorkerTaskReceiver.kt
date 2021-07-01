package com.barun.weather

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.*
import com.barun.weather.sync.WeatherSyncWorker
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class TriggerWorkerTaskReceiver : BroadcastReceiver() {

    private val TAG_OUTPUT = TriggerWorkerTaskReceiver::class.java.canonicalName
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d(TAG_OUTPUT,"Alarm Triggered to Initiate WorkManager")
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = OneTimeWorkRequestBuilder<WeatherSyncWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()
        val workManager = WorkManager.getInstance(context)
        workManager.cancelAllWork()
        workManager.enqueue(work)
    }
}
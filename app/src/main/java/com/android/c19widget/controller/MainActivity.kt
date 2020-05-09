package com.android.c19widget.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.android.c19widget.R
import com.android.c19widget.model.AppRepository
import com.android.c19widget.utils.AppLogger
import com.android.c19widget.utils.Worker
import com.android.c19widget.view.WidgetView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = PeriodicWorkRequestBuilder<Worker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    override fun onResume() {
        super.onResume()
        doUpdate()
    }

    private fun doUpdate() {
        AppLogger.log("MainActivity trying to get data")
        WidgetProvider().doUpdate(this, AppRepository(), WidgetView())
    }
}

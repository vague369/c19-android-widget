package com.android.c19widget.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.c19widget.controller.WidgetProvider
import com.android.c19widget.model.AppRepository
import com.android.c19widget.view.WidgetView

class Worker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        AppLogger.log("Worker working")
        return WidgetProvider().doUpdate(context, AppRepository(), WidgetView())
    }
}
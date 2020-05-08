package com.android.c19widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.IBinder

class C19WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Intent(context, UpdateService::class.java).also { context?.startService(it) }
    }

    class UpdateService : Service() {
        override fun onBind(intent: Intent?): IBinder? {
            return null
        }

    }
}
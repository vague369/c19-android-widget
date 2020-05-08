package com.android.c19widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews


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

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            val updateViews = buildUpdate(this)
            val thisWidget = ComponentName(this, C19WidgetProvider::class.java)
            AppWidgetManager.getInstance(this)
                .apply { updateAppWidget(thisWidget, updateViews) }

            return Service.START_STICKY
        }

        private fun buildUpdate(context: Context?): RemoteViews? {
            context ?: return null

            val views = RemoteViews(context.packageName, R.layout.layout_appwidget)
            // Update View state

            return views
        }
    }
}
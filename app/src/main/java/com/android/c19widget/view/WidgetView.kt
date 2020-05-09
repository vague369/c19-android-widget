package com.android.c19widget.view

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.android.c19widget.R
import com.android.c19widget.controller.MainActivity
import com.android.c19widget.controller.WidgetProvider
import com.android.c19widget.utils.AppLogger

class WidgetView : View {

    override fun update(context: Context, noc: String, nod: String) {
        AppLogger.log("Updating Widget")
        val updatedViews = RemoteViews(context.packageName, R.layout.layout_appwidget).apply {
            setTextViewText(R.id.WidgetNoc, noc)
            setTextViewText(R.id.WidgetNod, "$nod deaths")

            val intent = Intent(context, MainActivity::class.java)
            setOnClickPendingIntent(
                R.id.Widget,
                PendingIntent.getActivity(context, 0, intent, 0)
            )
        }

        val thisWidget = ComponentName(context, WidgetProvider::class.java)
        AppWidgetManager.getInstance(context).apply {
            updateAppWidget(thisWidget, updatedViews)
        }
    }
}
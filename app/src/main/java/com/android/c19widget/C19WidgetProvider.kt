package com.android.c19widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import com.android.c19widget.data.GetDataService
import com.android.c19widget.data.RetrofitClientInstance


class C19WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        show("Updating")
        Intent(context, UpdateService::class.java).also { context?.startService(it) }
    }

    class UpdateService : Service() {
        override fun onBind(intent: Intent?): IBinder? {
            show("In onBind")
            return null
        }

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            show("In onStartCommand")
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

            // Poll data
            val service =
                RetrofitClientInstance.getRetrofitInstance().create(GetDataService::class.java)
            val call = service.getDailyData()
            show("Preparing to get data")
            val response = call.execute()
            val data = response.body()

            data?.let {
                val confcases = data[1]
                val deaths = data[3]

                views.setTextViewText(R.id.WidgetNoc, confcases.values)
                views.setTextViewText(R.id.WidgetNod, "${deaths.values} deaths")
            }

            return views
        }
    }

    companion object {
        fun show(s: String) {
            Log.e("C19", s)
        }
    }
}
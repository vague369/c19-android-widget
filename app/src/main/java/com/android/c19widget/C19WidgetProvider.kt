package com.android.c19widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import com.android.c19widget.utils.Constants


class C19WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        show("Updating")
    }

    companion object {
        fun show(s: String) {
            Log.d(Constants.LOG_TAG, s)
        }
    }
}
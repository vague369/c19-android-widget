package com.android.c19widget.controller

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.android.c19widget.model.AppRepository
import com.android.c19widget.model.Repository
import com.android.c19widget.view.View
import com.android.c19widget.view.WidgetView


class WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        doUpdate(context, AppRepository(), WidgetView())
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun doUpdate(context: Context?, repository: Repository, view: View) {
    }
}
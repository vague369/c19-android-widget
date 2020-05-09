package com.android.c19widget.controller

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.android.c19widget.model.AppRepository
import com.android.c19widget.model.Repository


class WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        doUpdate(AppRepository())
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun doUpdate(repository: Repository) {

    }
}
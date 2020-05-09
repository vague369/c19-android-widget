package com.android.c19widget.controller

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.android.c19widget.model.AppRepository
import com.android.c19widget.model.Failure
import com.android.c19widget.model.Repository
import com.android.c19widget.model.Success
import com.android.c19widget.utils.AppLogger
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
        context ?: return
        repository.getData {
            when (it) {
                is Success -> {
                    val noc = it.data.first
                    val nod = it.data.second
                    if (noc.isNotEmpty() && nod.isNotEmpty()) view.update(context, noc, nod)
                }
                is Failure -> {
                    AppLogger.cry("Failure to get data from repository")
                }
            }
        }
    }
}
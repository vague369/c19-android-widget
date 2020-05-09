package com.android.c19widget.controller

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import androidx.work.ListenableWorker
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
    fun doUpdate(context: Context?, repository: Repository, view: View): ListenableWorker.Result {
        context ?: return ListenableWorker.Result.failure()
        var hasFailed = true
        AppLogger.log("WidgetProvider trying to do update")
        repository.getData {
            hasFailed = when (it) {
                is Success -> {
                    AppLogger.log("Data came in alright")
                    val noc = it.data.first
                    val nod = it.data.second
                    if (noc.isNotEmpty() && nod.isNotEmpty()) view.update(context, noc, nod)
                    false
                }
                is Failure -> {
                    AppLogger.cry(it.err)
                    true
                }
            }
        }

        return if (hasFailed) ListenableWorker.Result.failure() else ListenableWorker.Result.success()
    }
}
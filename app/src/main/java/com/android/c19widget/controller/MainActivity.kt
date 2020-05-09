package com.android.c19widget.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.c19widget.R
import com.android.c19widget.model.AppRepository
import com.android.c19widget.utils.AppLogger
import com.android.c19widget.view.WidgetView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doUpdate()
    }

    private fun doUpdate() {
        AppLogger.log("MainActivity trying to get data")
        WidgetProvider().doUpdate(this, AppRepository(), WidgetView())
    }
}

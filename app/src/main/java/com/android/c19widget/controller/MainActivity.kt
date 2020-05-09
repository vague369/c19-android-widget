package com.android.c19widget.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.c19widget.R
import com.android.c19widget.model.AppRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doUpdate()
    }

    private fun doUpdate() {
        WidgetProvider().doUpdate(AppRepository())
    }
}

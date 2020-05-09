package com.android.c19widget.utils

import android.util.Log

object AppLogger {
    fun log(s: String) {
        Log.d(Constants.LOG_TAG, s)
    }
}
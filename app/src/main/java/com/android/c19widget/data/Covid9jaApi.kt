package com.android.c19widget.data

import retrofit2.Call
import retrofit2.http.GET

interface Covid9jaApi {

    @GET("/api/confirmed")
    fun getDailyData(): Call<List<Covid9jaApiResponse>>
}
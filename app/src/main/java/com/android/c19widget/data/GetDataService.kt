package com.android.c19widget.data

import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {

    @GET("/api/confirmed")
    fun getDailyData(): Call<List<Response>>
}
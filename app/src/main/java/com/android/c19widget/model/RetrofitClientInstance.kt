package com.android.c19widget.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {

    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://covid9ja.herokuapp.com"

        fun getRetrofitInstance(): Retrofit {
            retrofit ?: {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }()

            return retrofit!!
        }
    }
}
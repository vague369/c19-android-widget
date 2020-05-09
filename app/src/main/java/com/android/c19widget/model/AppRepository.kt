package com.android.c19widget.model

import android.os.Handler
import android.os.Looper
import com.android.c19widget.utils.AppLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository : Repository {

    override fun getData(callback: (RepoResult) -> Unit) {
        AppLogger.log("Repository trying to get data")
        val uiHandler = Handler(Looper.getMainLooper())

        // Get data from api on a background thread

        val service =
            RetrofitClientInstance.getRetrofitInstance().create(Covid9jaApi::class.java)
        val call = service.getDailyData()

        call.enqueue(object : Callback<List<Covid9jaApiResponse>> {
            override fun onFailure(call: Call<List<Covid9jaApiResponse>>, t: Throwable) {
                uiHandler.post {
                    callback(Failure("Could not get data, probably network"))
                }
            }

            override fun onResponse(
                call: Call<List<Covid9jaApiResponse>>,
                response: Response<List<Covid9jaApiResponse>>
            ) {
                val data = response.body()
                data ?: uiHandler.post {
                    callback(Failure("Api returned no data"))
                }
                data?.let {
                    AppLogger.log("Data received from api")
                    val noc = data[1].values
                    val nod = data[3].values

                    // Return retrieved data
                    uiHandler.post {
                        callback(Success(Pair(noc, nod)))
                    }
                }
            }
        })
    }
}
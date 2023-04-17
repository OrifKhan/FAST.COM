package com.example.dbfordodo.dodoViewMadel.retrafitRepository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrafit   {
    private val interceptor = HttpLoggingInterceptor()
    init {

        interceptor.level= HttpLoggingInterceptor.Level.BODY
    }

    private val client= OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api-life3.megafon.tj").client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
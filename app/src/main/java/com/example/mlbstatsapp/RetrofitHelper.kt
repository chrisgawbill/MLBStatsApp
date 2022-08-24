package com.example.mlbstatsapp

import com.example.mlbstatsapp.RetrofitHelper.gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Creates the RetrofitHelper object
 */
object RetrofitHelper {
    val baseUrl = "http://lookup-service-prod.mlb.com/json/"
    var gson = GsonBuilder()
        .setLenient()
        .create()
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
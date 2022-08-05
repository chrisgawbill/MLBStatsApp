package com.example.mlbstatsapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MlbApi {

    @GET("named.search_player_all.bam")
    fun getPlayerSearchResults(@QueryMap query:Map<String,String>): Call<PlayerSearchApiModel>


}
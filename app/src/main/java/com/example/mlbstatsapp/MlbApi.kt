package com.example.mlbstatsapp

import com.example.mlbstatsapp.ApiModels.PlayerHittingApiModel
import com.example.mlbstatsapp.ApiModels.PlayerPitchingApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MlbApi {

    @GET("named.search_player_all.bam")
    fun getPlayerSearchResults(@QueryMap query:Map<String,String>): Call<PlayerSearchApiModel>
    @GET("named.sport_hitting_tm.bam")
    fun getPlayerHittingStats(@QueryMap query:Map<String,String>):Call<PlayerHittingApiModel>
    @GET("named.sport_pitching_tm.bam")
    fun getPlayerPitchingStats(@QueryMap query:Map<String,String>): Call<PlayerPitchingApiModel>

}
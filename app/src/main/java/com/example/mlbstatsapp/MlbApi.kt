package com.example.mlbstatsapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MlbApi {
    @GET("/json/named.search_player_all.bam?sport_code=%27mlb%27&active_sw=%27Y%27&name_part=%27harper%25%27")
    fun getPlayerSearchResults(): Call<MlbApi>
}
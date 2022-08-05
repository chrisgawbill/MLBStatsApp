package com.example.mlbstatsapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MlbApi {

    @GET("/json/named.search_player_all.bam")
    fun getPlayerSearchResults(@Query("sport_code")sport_code:String,
                               @Query("active_sw")active_sw:String,
                               @Query("name_part")name_part:String): Call<ApiModel>

    /*
    @GET("/json/named.search_player_all.bam?sport_code=%27mlb%27&active_sw=%27Y%27&name_part=%27Harper%25%27")
    fun getPlayerSearchResults(): Call<ApiModel>
*/

}
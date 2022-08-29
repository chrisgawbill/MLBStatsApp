package com.example.mlbstatsapp.ApiModels

data class PlayerHittingApiModel(
    val sport_hitting_tm:HittingStatsQuery
)
data class HittingStatsQuery(
    val copyRight:String,
    val queryResults: HittingStatResults
)
data class HittingStatResults(
    val created:String,
    val totalSize:String,
    val row:PlayerHittingStats
)
data class PlayerHittingStats(
    val gidp:String,
    val sac:String,
    val np:String,
    val sport_code:String,
    val hgnd:String,
    val tb:String,
    val gdip_opp:String,
    val sport_id:String,
    val bb:String,
    val avg:String,
    val slg:String,
    val team_full:String,
    val ops:String,
    val hbp:String,
    val league_full:String,
    val team_abbrev:String,
    val so:String,
    val hfly:String,
    val wo:String,
    val league_id:String,
    val sf:String,
    val team_seq:String,
    val league:String,
    val hpop:String,
    val cs:String,
    val season:String,
    val sb:String,
    val go_ao:String,
    val ppa:String,
    val player_id:String,
    val ibb:String,
    val team_id:String,
    val roe:String,
    val go:String,
    val hr:String,
    val rbi:String,
    val babip:String,
    val lob:String,
    val end_date:String,
    val xbh:String,
    val league_short:String,
    val g:String,
    val d:String,
    val sport:String,
    val team_short:String,
    val tpa:String,
    val h:String,
    val obp:String,
    val hldr:String,
    val t:String,
    val ao:String,
    val r:String,
    val ab:String
)
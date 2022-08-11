package com.example.mlbstatsapp.ApiModels

data class PlayerPitchingApiModel(
    val sport_pitching_tm:PitchingStatsQuery
)
data class PitchingStatsQuery(
    val copyright:String,
    val queryResults:PitchingStatResult
)
data class PitchingStatResult(
    val created:String,
    val totalSize:String,
    val row:PlayerPitchingStat
)
data class PlayerPitchingStat(
    val gdip:String,
    val h9:String,
    val np:String,
    val tr:String,
    val gf:String,
    val sport_code:String,
    val bqs:String,
    val hgnd:String,
    val sho:String,
    val bq:String,
    val gidp_opp:String,
    val bk:String,
    val kbb:String,
    val sport_id:String,
    val hr9:String,
    val sv:String,
    val slg:String,
    val bb:String,
    val whip:String,
    val avg:String,
    val ops:String,
    val team_full:String,
    val db:String,
    val league_full:String,
    val team_abbrev:String,
    val hfly:String,
    val so:String,
    val tbf:String,
    val bb9:String,
    val league_id:String,
    val wp:String,
    val team_seq:String,
    val hpop:String,
    val league:String,
    val hb:String,
    val cs:String,
    val pgs:String,
    val season:String,
    val sb:String,
    val go_ao:String,
    val ppa:String,
    val cg:String,
    val player_id:String,
    val gs:String,
    val ibb:String,
    val team_id:String,
    val pk:String,
    val go:String,
    val hr:String,
    val irs:String,
    val wpct:String,
    val era:String,
    val babip:String,
    val end_date:String,
    val rs9:String,
    val qs:String,
    val league_short:String,
    val g:String,
    val ir:String,
    val hld:String,
    val k9:String,
    val sport:String,
    val team_short:String,
    val l:String,
    val svo:String,
    val h:String,
    val ip:String,
    val obp:String,
    val w:String,
    val hldr:String,
    val ao:String,
    val s:String,
    val r:String,
    val spct:String,
    val pip:String,
    val ab:String,
    val er:String
)

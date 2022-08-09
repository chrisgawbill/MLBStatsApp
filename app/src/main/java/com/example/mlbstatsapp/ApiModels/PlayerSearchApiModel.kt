package com.example.mlbstatsapp

import com.google.gson.internal.LinkedTreeMap
import java.lang.reflect.Type

data class PlayerSearchApiModel(
    val search_player_all:SearchPlayerAll
)
data class SearchPlayerAll(
    val copyRight:String,
    val queryResults:PlayerList
)
data class PlayerList(
    val created:String,
    val totalSize:String,
    val row: Any
)
data class PlayerApiModel(
    val position:String,
    val birth_country:String,
    val weight:String,
    val birth_state:String,
    val name_display_first_last:String,
    val college:String,
    val height_inches:Float,
    val name_display_roster:String,
    val sport_code:String,
    val bats:String,
    val name_first:String,
    val team_code:String,
    val birth_city:String,
    val height_feet:String,
    val pro_debut_date:String,
    val team_full:String,
    val team_abbrev:String,
    val birth_date:String,
    val throws:String,
    val league:String,
    val name_display_last_first:String,
    val position_id:String,
    val high_school:String,
    val name_use:String,
    val player_id:String,
    val name_last:String,
    val team_id:String,
    val service_years:String,
    val active_sw:String
)


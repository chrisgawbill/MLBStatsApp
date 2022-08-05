package com.example.mlbstatsapp

data class PlayerList(
    val created:String,
    val totalSize:String,
    val row: List<PlayerApiModel>
)

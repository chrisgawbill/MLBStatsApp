package com.example.mlbstatsapp

import androidx.lifecycle.MutableLiveData

class PlayerSearchIndividualPlayerSharedViewModel {
    val playerId = MutableLiveData<Int>()

    fun playerId(playerIdParam:Int){
        this.playerId.value = playerIdParam
    }
}
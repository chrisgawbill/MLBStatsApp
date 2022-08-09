package com.example.mlbstatsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerSearchIndividualPlayerSharedViewModel:ViewModel() {
    val playerId = MutableLiveData<Int>()

    fun playerId(playerIdParam:Int){
        this.playerId.value = playerIdParam
    }
}
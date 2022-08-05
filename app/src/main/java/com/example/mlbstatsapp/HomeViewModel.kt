package com.example.mlbstatsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    fun HomeViewModel(){

    }
    fun getFavorites(){

    }
    fun getPlayerSearchList(playerSearch:String){
            val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
            GlobalScope.launch(coroutineExceptionHandler) {
                val result = mlbApi.getPlayerSearchResults()
                if (result != null) {
                    Log.d(HomeViewModel::class.java.simpleName, result.body().toString())
                    var apiModel:ApiModel = result.body() as ApiModel
                    var searchPlayerAll:SearchPlayerAll = apiModel.search_player_all as SearchPlayerAll
                    var playerList:PlayerList = searchPlayerAll.queryResults
                    var listOfPlayers:ArrayList<Player> = playerList.row as ArrayList<Player>

                    Log.d(HomeViewModel::class.java.simpleName, listOfPlayers.toString())
                }
            }
    }
}
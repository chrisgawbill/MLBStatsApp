package com.example.mlbstatsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var playerSearchResultsArray:ArrayList<PlayerApiModel> = ArrayList()
    lateinit var playerSearch:String
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    fun HomeViewModel(){

    }
    fun getFavorites(){

    }
    fun setPlayerSearchTerm(playerSearchParam:String){
        playerSearch = playerSearchParam
    }
    fun getPlayerSearchList(){
            val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
            GlobalScope.launch(coroutineExceptionHandler) {
                val result = mlbApi.getPlayerSearchResults()
                if (result != null) {
                    Log.d(HomeViewModel::class.java.simpleName, result.body().toString())
                    var apiModel:ApiModel = result.body() as ApiModel
                    var searchPlayerAll:SearchPlayerAll = apiModel.search_player_all as SearchPlayerAll
                    var playerList:PlayerList = searchPlayerAll.queryResults
                    var listOfPlayers:ArrayList<PlayerApiModel> = playerList.row as ArrayList<PlayerApiModel>

                    Log.d(HomeViewModel::class.java.simpleName, listOfPlayers.toString())

                    playerSearchResultsArray = listOfPlayers
                }
            }
    }
}
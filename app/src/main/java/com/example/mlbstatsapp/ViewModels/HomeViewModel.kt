package com.example.mlbstatsapp.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mlbstatsapp.*
import com.example.mlbstatsapp.RecycleAdapters.PlayerSearchResultsAdapter
import retrofit2.Call
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    var playerSearchResults:MutableLiveData<PlayerList> = MutableLiveData()
    var playerSearchResultsRecyclerViewAdapter = PlayerSearchResultsAdapter()

    fun getAdapter(): PlayerSearchResultsAdapter {
        return playerSearchResultsRecyclerViewAdapter
    }
    fun setAdapterData(data:ArrayList<PlayerApiModel>){
        playerSearchResultsRecyclerViewAdapter.setDataList(data)
        playerSearchResultsRecyclerViewAdapter.notifyDataSetChanged()
    }
    fun getPlayerSearchResultsDataObserver():MutableLiveData<PlayerList>{
        return playerSearchResults
    }
    fun getPlayerSearchList(playerSearch:String){
        val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
        Log.d(HomeViewModel::class.java.simpleName, playerSearch)
        var queryMap:HashMap<String,String> = HashMap()
        queryMap.put("sport_code","'mlb'")
        queryMap.put("active_sw", "'Y'")
        queryMap.put("name_part", "'" + playerSearch + "%'")
        val call = mlbApi.getPlayerSearchResults(queryMap)
        Log.d(HomeViewModel::class.java.simpleName, call.request().url().toString())

        call.enqueue(object:retrofit2.Callback<PlayerSearchApiModel>{
            override fun onFailure(call: Call<PlayerSearchApiModel>, t:Throwable){
                Log.d(HomeViewModel::class.java.simpleName, "API CALLED FAILED")
                Log.d(HomeViewModel::class.java.simpleName, t.message.toString())
            }
            override fun onResponse(call:Call<PlayerSearchApiModel>, response: Response<PlayerSearchApiModel>){
                if(response.isSuccessful){
                    var apiModel: PlayerSearchApiModel = response.body() as PlayerSearchApiModel
                    var searchPlayerAll: SearchPlayerAll = apiModel.search_player_all as SearchPlayerAll
                    var playerList: PlayerList = searchPlayerAll.queryResults
                    Log.d(HomeViewModel::class.java.simpleName, response.body().toString())
                    Log.d(HomeViewModel::class.java.simpleName, playerList.toString())
                    playerSearchResults.postValue(playerList)
                }else{
                    Log.d(HomeViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                }

            }
        })

    }
}
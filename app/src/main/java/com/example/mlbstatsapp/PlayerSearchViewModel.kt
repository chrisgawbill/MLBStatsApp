package com.example.mlbstatsapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mlbstatsapp.RecycleAdapters.PlayerSearchResultsAdapter
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class PlayerSearchViewModel(): ViewModel() {
    var playerSearchResults:MutableLiveData<PlayerList> = MutableLiveData()
    var playerSearchResultsRecyclerViewAdapter = PlayerSearchResultsAdapter()

    /**
     * Returns the playerSearchResultsRecyclerViewAdapter set in setAdapterData()
     */
    fun getAdapter(): PlayerSearchResultsAdapter {
        return playerSearchResultsRecyclerViewAdapter
    }

    /**
     * Sets the value for playerSearchResultsRecyclerViewAdapter
     */
    fun setAdapterData(data:ArrayList<PlayerApiModel>){
        playerSearchResultsRecyclerViewAdapter.setDataList(data)
        playerSearchResultsRecyclerViewAdapter.notifyDataSetChanged()
    }

    /**
     * Returns playerSearchResutls Mutable Live Data
     */
    fun getPlayerSearchResultsDataObserver():MutableLiveData<PlayerList>{
        return playerSearchResults
    }

    /**
     * Calls the retrofit helper class to get the returned response to the playerSearch string query
     */
    fun getPlayerSearchList(playerSearch:String){
        val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
        Log.d(PlayerSearchViewModel::class.java.simpleName, playerSearch)
        var queryMap:HashMap<String,String> = HashMap()
        queryMap.put("sport_code","'mlb'")
        queryMap.put("active_sw", "'Y'")
        queryMap.put("name_part", "'" + playerSearch + "%'")
        val call = mlbApi.getPlayerSearchResults(queryMap)
        Log.d(PlayerSearchViewModel::class.java.simpleName, call.request().url().toString())

        call.enqueue(object:retrofit2.Callback<PlayerSearchApiModel>{
            override fun onFailure(call: Call<PlayerSearchApiModel>, t:Throwable){
                Log.d(PlayerSearchViewModel::class.java.simpleName, "API CALLED FAILED")
                Log.d(PlayerSearchViewModel::class.java.simpleName, t.message.toString())
            }
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(call:Call<PlayerSearchApiModel>, response: Response<PlayerSearchApiModel>){
                if(response.isSuccessful){
                    try {
                        var apiModel: PlayerSearchApiModel = response.body() as PlayerSearchApiModel
                        var searchPlayerAll: SearchPlayerAll = apiModel.search_player_all
                        var playerList: PlayerList = searchPlayerAll.queryResults
                        Log.d(
                            PlayerSearchViewModel::class.java.simpleName,
                            response.body().toString()
                        )
                        Log.d(PlayerSearchViewModel::class.java.simpleName, playerList.toString())
                        playerSearchResults.postValue(playerList)
                    }catch (e:Exception){
                        Log.d(PlayerSearchViewModel::class.java.simpleName, "Something went wrong during processing")
                    }
                }else{
                    Log.d(PlayerSearchViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                }

            }
        })

    }
}
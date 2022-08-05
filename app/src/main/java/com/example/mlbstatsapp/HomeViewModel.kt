package com.example.mlbstatsapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeViewModel: ViewModel() {
    var playerSearchResults:MutableLiveData<PlayerList> = MutableLiveData()
    var playerSearchResultsRecyclerViewAdapter = PlayerSearchResultsAdapter()

    var playerSearchResultsArray:ArrayList<PlayerApiModel> = ArrayList()
    lateinit var playerSearch:String
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    fun HomeViewModel(){

    }
    fun getFavorites(){

    }
    fun getAdapter(): PlayerSearchResultsAdapter{
        return playerSearchResultsRecyclerViewAdapter
    }
    fun setAdapterData(data:ArrayList<PlayerApiModel>){
        playerSearchResultsRecyclerViewAdapter.setDataList(data)
        playerSearchResultsRecyclerViewAdapter.notifyDataSetChanged()
    }
    fun setPlayerSearchTerm(playerSearchParam:String){
        playerSearch = playerSearchParam
    }
    fun getPlayerSearchResultsDataObserver():MutableLiveData<PlayerList>{
        return playerSearchResults
    }
    fun getPlayerSearchList(){
        val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
        val call = mlbApi.getPlayerSearchResults()
        call.enqueue(object:retrofit2.Callback<MlbApi>{
            override fun onFailure(call: Call<MlbApi>, t:Throwable){
                Log.d(HomeViewModel::class.java.simpleName, "API CALLED FAILED")
                Log.d(HomeViewModel::class.java.simpleName, t.message.toString())
            }
            override fun onResponse(call:Call<MlbApi>, response: Response<MlbApi>){
                if(response.isSuccessful){
                    var apiModel:ApiModel = response.body() as ApiModel
                    var searchPlayerAll:SearchPlayerAll = apiModel.search_player_all as SearchPlayerAll
                    var playerList:PlayerList = searchPlayerAll.queryResults
                    Log.d(HomeViewModel::class.java.simpleName, playerList.toString())
                    playerSearchResults.postValue(playerList)
                }else{
                    Log.d(HomeViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                    playerSearchResults.postValue(null)
                }

            }
        })

    }
}
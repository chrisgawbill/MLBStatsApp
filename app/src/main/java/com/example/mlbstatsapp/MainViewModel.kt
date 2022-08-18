package com.example.mlbstatsapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mlbstatsapp.ApiModels.*
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher
import retrofit2.Call
import retrofit2.Response

class MainViewModel(context: Context):ViewModel() {
    var playerHittingStatsLiveData: MutableLiveData<PlayerHittingStats> = MutableLiveData()
    var playerPitchingStatsLiveData: MutableLiveData<PlayerPitchingStat> = MutableLiveData()

    val db = LoadData.getInstance(context)

    fun getAllBatters():List<Batter>{
        return db.getAllBatters()
    }
    fun getAllPitchers():List<Pitcher>{
        return db.getAllPitchers()
    }
    fun getPlayerHittingStatsData():MutableLiveData<PlayerHittingStats>{
        return playerHittingStatsLiveData
    }
    fun getPlayerPitchingStatsData():MutableLiveData<PlayerPitchingStat>{
        return playerPitchingStatsLiveData
    }
    fun getPlayerHittingStats(playerID:Int){
        val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
        Log.d(HomeViewModel::class.java.simpleName, playerID.toString())
        var queryMap:HashMap<String,String> = HashMap()
        queryMap.put("league_list_id","'mlb'")
        queryMap.put("game_type", "'R'")
        queryMap.put("season", "'2022'")
        queryMap.put("player_id", "'" + playerID.toString() + "'")
        val call = mlbApi.getPlayerHittingStats(queryMap)
        Log.d(HomeViewModel::class.java.simpleName, call.request().url().toString())

        call.enqueue(object:retrofit2.Callback<PlayerHittingApiModel>{
            override fun onFailure(call: Call<PlayerHittingApiModel>, t:Throwable){
                Log.d(HomeViewModel::class.java.simpleName, "API CALLED FAILED")
                Log.d(HomeViewModel::class.java.simpleName, t.message.toString())
            }
            override fun onResponse(call: Call<PlayerHittingApiModel>, response: Response<PlayerHittingApiModel>){
                if(response.isSuccessful){
                    var apiModel: PlayerHittingApiModel = response.body() as PlayerHittingApiModel
                    var playerHittingModel: HittingStatsQuery = apiModel.sport_hitting_tm
                    var hittingStatResult: HittingStatResults = playerHittingModel.queryResults
                    var playerHittingStats:PlayerHittingStats = hittingStatResult.row
                    Log.d(HomeViewModel::class.java.simpleName, response.body().toString())
                    Log.d(HomeViewModel::class.java.simpleName, playerHittingStats.toString())
                    playerHittingStatsLiveData.postValue(playerHittingStats)
                }else{
                    Log.d(HomeViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                }

            }
        })
    }
    fun getPlayerPitchingStats(playerID:Int) {
        val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
        Log.d(HomeViewModel::class.java.simpleName, playerID.toString())
        var queryMap: HashMap<String, String> = HashMap()
        queryMap.put("league_list_id", "'mlb'")
        queryMap.put("game_type", "'R'")
        queryMap.put("season", "'2022'")
        queryMap.put("player_id", "'" + playerID.toString() + "'")
        val call = mlbApi.getPlayerPitchingStats(queryMap)
        Log.d(HomeViewModel::class.java.simpleName, call.request().url().toString())

        call.enqueue(object : retrofit2.Callback<PlayerPitchingApiModel> {
            override fun onFailure(call: Call<PlayerPitchingApiModel>, t: Throwable) {
                Log.d(HomeViewModel::class.java.simpleName, "API CALLED FAILED")
                Log.d(HomeViewModel::class.java.simpleName, t.message.toString())
            }

            override fun onResponse(
                call: Call<PlayerPitchingApiModel>,
                response: Response<PlayerPitchingApiModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        var apiModel: PlayerPitchingApiModel =
                            response.body() as PlayerPitchingApiModel
                        var playerPitchingModel: PitchingStatsQuery = apiModel.sport_pitching_tm
                        var pitchingStatResult: PitchingStatResult =
                            playerPitchingModel.queryResults
                        var playerPitchingStats: PlayerPitchingStat = pitchingStatResult.row
                        Log.d(HomeViewModel::class.java.simpleName, response.body().toString())
                        //Log.d(HomeViewModel::class.java.simpleName, playerPitchingStats.toString())
                        playerPitchingStatsLiveData.postValue(playerPitchingStats)
                    } catch (e: Exception) {
                        Log.d(
                            IndividualPlayerViewModel::class.java.simpleName,
                            "Error while processing"
                        )
                    }
                } else {
                    Log.d(HomeViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                }

            }
        })
    }
}
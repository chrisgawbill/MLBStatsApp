package com.example.mlbstatsapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mlbstatsapp.ApiModels.*
import retrofit2.Call
import retrofit2.Response
import java.text.FieldPosition

class IndividualPlayerViewModel{
    var playerHittingStatsLiveData:MutableLiveData<PlayerHittingStats> = MutableLiveData()
    var playerPitchingStatsLiveData:MutableLiveData<PlayerPitchingStat> = MutableLiveData()

    var playerPosition:MutableLiveData<String> = MutableLiveData()
    var playerFirstName:MutableLiveData<String> = MutableLiveData()
    var playerLastName:MutableLiveData<String> = MutableLiveData()
    var playerWeight:MutableLiveData<String> = MutableLiveData()
    var playerHeight:MutableLiveData<String> = MutableLiveData()
    var playerCurrentTeam:MutableLiveData<String> = MutableLiveData()
    var playerHighSchool:MutableLiveData<String> = MutableLiveData()
    var playerCollege:MutableLiveData<String> = MutableLiveData()

    fun setGeneralPlayerInfo(playerPositionParam:String, playerFirstNameParam:String, playerLastNameParam:String,
    playerHeightParam:String, playerWeightParam:String, playerCurrentTeamParam:String, playerHighSchoolParam:String,
    playerCollegeParam:String){
        playerPosition.value = playerPositionParam
        playerFirstName.value = playerFirstNameParam
        playerLastName.value = playerLastNameParam
        playerWeight.value = playerWeightParam
        playerHeight.value = playerHeightParam
        playerCurrentTeam.value = playerCurrentTeamParam
        playerHighSchool.value = playerHighSchoolParam
        playerCollege.value = playerCollegeParam
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
    fun getPlayerPitchingStats(playerID:Int){
        val mlbApi = RetrofitHelper.getInstance().create(MlbApi::class.java)
        Log.d(HomeViewModel::class.java.simpleName, playerID.toString())
        var queryMap:HashMap<String,String> = HashMap()
        queryMap.put("league_list_id","'mlb'")
        queryMap.put("game_type", "'R'")
        queryMap.put("season", "'2022'")
        queryMap.put("player_id", "'" + playerID.toString() + "'")
        val call = mlbApi.getPlayerPitchingStats(queryMap)
        Log.d(HomeViewModel::class.java.simpleName, call.request().url().toString())

        call.enqueue(object:retrofit2.Callback<PlayerPitchingApiModel>{
            override fun onFailure(call: Call<PlayerPitchingApiModel>, t:Throwable){
                Log.d(HomeViewModel::class.java.simpleName, "API CALLED FAILED")
                Log.d(HomeViewModel::class.java.simpleName, t.message.toString())
            }
            override fun onResponse(call: Call<PlayerPitchingApiModel>, response: Response<PlayerPitchingApiModel>){
                if(response.isSuccessful){
                    var apiModel: PlayerPitchingApiModel = response.body() as PlayerPitchingApiModel
                    var playerPitchingModel: PitchingStatsQuery = apiModel.sport_pitching_tm
                    var pitchingStatResult: PitchingStatResult = playerPitchingModel.queryResults
                    var playerPitchingStats:PlayerPitchingStat = pitchingStatResult.row
                    Log.d(HomeViewModel::class.java.simpleName, response.body().toString())
                    Log.d(HomeViewModel::class.java.simpleName, playerPitchingStats.toString())
                    playerPitchingStatsLiveData.postValue(playerPitchingStats)
                }else{
                    Log.d(HomeViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                }

            }
        })

    }

}
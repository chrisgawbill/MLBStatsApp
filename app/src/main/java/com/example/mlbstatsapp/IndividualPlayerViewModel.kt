package com.example.mlbstatsapp

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.example.mlbstatsapp.ApiModels.*
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
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
    var playerThrows:MutableLiveData<String> = MutableLiveData()
    var playerBats:MutableLiveData<String> = MutableLiveData()
    var playerHometown:MutableLiveData<String> = MutableLiveData()

    /**
     * Sets the respective mutable live data in this class to values
     * Used for other methods in this class
     */
    fun setGeneralPlayerInfo(playerPositionParam:String, playerFirstNameParam:String, playerLastNameParam:String,
    playerHeightParam:String, playerWeightParam:String, playerCurrentTeamParam:String, playerThrowsParam:String,
    playerBatsParam:String, playerHometownParam:String){
        playerPosition.value = playerPositionParam
        playerFirstName.value = playerFirstNameParam
        playerLastName.value = playerLastNameParam
        playerWeight.value = playerWeightParam
        playerHeight.value = playerHeightParam
        playerCurrentTeam.value = playerCurrentTeamParam
        playerThrows.value = playerThrowsParam
        playerBats.value = playerBatsParam
        playerHometown.value = playerHometownParam
    }

    /**
     * Returns the playerHittingStatsLiveData Mutable Live Data
     */
    fun getPlayerHittingStatsData():MutableLiveData<PlayerHittingStats>{
        return playerHittingStatsLiveData
    }

    /**
     * Returns the playerPitchingStatsLiveData Mutable Live Data
     */
    fun getPlayerPitchingStatsData():MutableLiveData<PlayerPitchingStat>{
        return playerPitchingStatsLiveData
    }

    /**
     * Calls retrofit helper class to get the plauer hitting stats for the respective plauerID put in
     */
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

    /**
     * Calls the retrofit helper class to get the pitching stats for the respective playerID
     */
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
                    }catch(e:Exception){
                        Log.d(IndividualPlayerViewModel::class.java.simpleName, "Error while processing")
                    }
                }else{
                    Log.d(HomeViewModel::class.java.simpleName, "NOTHING WAS RETURNED")
                }

            }
        })

    }

    /**
     * It checks the set playerPosition and uses the data set in setGeneralInfo to input the player into the db
     */
    fun insertFavoriteIntoDb(activity:FragmentActivity?, playerId:String){
        val db = LoadData.getInstance(activity?.applicationContext ?: activity?.applicationContext)
        if(playerPosition.value == "P"){
            val era = playerPitchingStatsLiveData.value!!.era.toFloat()
            val wins = playerPitchingStatsLiveData.value!!.w.toInt()
            val loses = playerPitchingStatsLiveData.value!!.l.toInt()

            val pitcher:Pitcher = Pitcher(playerId, playerFirstName.value.toString(), playerLastName.value.toString(),playerHometown.value.toString(),
            playerHeight.value.toString(), playerWeight.value.toString(), playerCurrentTeam.value.toString(), playerBats.value.toString(), playerThrows.value.toString(),era, wins, loses)
            db.insertPitcher(pitcher)
        }else{
            val hr = playerHittingStatsLiveData.value!!.hr.toInt()
            val rbi = playerHittingStatsLiveData.value!!.rbi.toInt()
            val ba = playerHittingStatsLiveData.value!!.babip.toFloat()

            val batter:Batter = Batter(playerId, playerFirstName.value.toString(), playerLastName.value.toString(),playerHometown.value.toString(),
               playerHeight.value.toString(), playerWeight.value.toString(), playerCurrentTeam.value.toString(), playerBats.value.toString(), playerThrows.value.toString(), playerPosition.value.toString(), hr, rbi, ba)
            db.insertBatter(batter)
        }
    }

    /**
     * Deletes the player from the db
     */
    fun deleteFavoritePlayerFromDb(activity: FragmentActivity?, playerId:String){
        val db = LoadData.getInstance(activity?.applicationContext ?: activity?.applicationContext)
        if(playerPosition.value == "P"){
            db.deletePitcher(playerId)
        }else{
            db.deleteBatter(playerId)
        }
    }

}
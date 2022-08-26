package com.example.mlbstatsapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import com.example.mlbstatsapp.Activities.MainActivity
import com.example.mlbstatsapp.ApiModels.PlayerHittingStats
import com.example.mlbstatsapp.ApiModels.PlayerPitchingStat
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher

/**
 * This is the broadcastReciever class for the alarmMangager set in SplashScreenActiity
 * It gets the list of favorite batter/pitchers
 * It calls mainViewModel stats to get the stats for each player
 * It then compares the returned stats and the ones in db, if they differ a notification is
 * created and sent to the user
 */
class AlarmReciever: BroadcastReceiver() {
    lateinit var notificationManager:NotificationManager
    lateinit var mainViewModel:MainViewModel
    lateinit var context:Context
    lateinit var batterList:List<Batter>
    lateinit var pitcherList:List<Pitcher>

    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private val ACTION_UPDATE_NOTIFICATION = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION"
    private val NOTIFICATION_ID = 0

    override fun onReceive(
        contextParam: Context,
        intentParam: Intent
    ) {
        context = contextParam
        mainViewModel = MainViewModel(context)
        Log.d(AlarmReciever::class.java.simpleName, "Alarm just fired")

        createNotificationChannel()

        batterList = mainViewModel.getAllBatters()
        pitcherList = mainViewModel.getAllPitchers()

        Log.d(AlarmReciever::class.java.simpleName, batterList.size.toString())
        Log.d(AlarmReciever::class.java.simpleName, pitcherList.size.toString())

        compareBatterList()
        comparePitcherList()

    }
    fun compareBatterList(){
        if(batterList.isNotEmpty()){
            for(batter in batterList){
                var batterId:Int = batter.playerId.toInt()
                var batterRbi = batter.rbi
                var batterHr = batter.hr
                var batterBa = batter.ba
                var batterName = batter.firstName + " " + batter.lastName
                mainViewModel.getPlayerHittingStatsData().observeForever(Observer<PlayerHittingStats>{
                    if(it != null){
                        Log.d(AlarmReciever::class.java.simpleName, (it as PlayerHittingStats).toString())
                        var recievedRbi = it.rbi.toInt()
                        var recievedHr = it.hr.toInt()
                        var recievedBa = it.babip.toFloat()

                        if(recievedRbi > batterRbi || recievedHr > batterHr || recievedBa > batterBa){
                            Log.d(AlarmReciever::class.java.simpleName, "${batterName} stat has changed")
                            createPlayerNotification(batterName)
                            mainViewModel.updateBatterStats(batterId.toString(), recievedHr.toString(), recievedRbi.toString(), recievedBa.toString())
                        }else{
                            Log.d(AlarmReciever::class.java.simpleName, "${batterName} stat has not changed")
                        }
                    }else{
                        Log.d(AlarmReciever::class.java.simpleName, "SOMETHING WENT WRONG")
                    }
                })
                mainViewModel.getPlayerHittingStats(batterId)
            }
        }
    }
    fun comparePitcherList(){
        if(pitcherList.isNotEmpty()){
            for(pitcher in pitcherList){
                var pitcherId:Int = pitcher.playerId.toInt()
                var pitcherEra:Float = pitcher.era.toFloat()
                var pitcherWins:Int = pitcher.wins.toInt()
                var pitcherLosses:Int = pitcher.losses
                var pitcherName = pitcher.firstName + " " + pitcher.lastName

                mainViewModel.getPlayerPitchingStatsData().observeForever(Observer<PlayerPitchingStat>{
                    if(it != null){
                        Log.d(AlarmReciever::class.java.simpleName, (it as PlayerPitchingStat).toString())
                        var recievedEra:Float = it.era.toFloat()
                        var recievedWins:Int = it.w.toInt()
                        var recievedLosses = it.l.toInt()
                        if(recievedEra > pitcherEra || recievedWins > pitcherWins || recievedLosses > pitcherLosses){
                            Log.d(AlarmReciever::class.java.simpleName, "${pitcherName} stat has changed")
                            createPlayerNotification(pitcherName)
                            mainViewModel.updatePitchingStats(pitcherId.toString(),recievedEra.toString(),recievedWins.toString(),recievedLosses.toString())
                        }else{
                            Log.d(AlarmReciever::class.java.simpleName, "${pitcherName} stat has not changed")
                        }
                    }else{
                        Log.d(AlarmReciever::class.java.simpleName, "SOMETHING WENT WRONG")
                    }
                })
                mainViewModel.getPlayerPitchingStats(pitcherId)
            }
        }
    }
    fun createPlayerNotification(playerName:String){
        var notifyBuilder:NotificationCompat.Builder = getNotificationBuilder(playerName)
        notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build())
    }
    fun getNotificationBuilder(playerName: String):NotificationCompat.Builder{
        //Intent to launch the main activity
        val notificationIntent = Intent(context, MainActivity::class.java)
        //Creates a pending intent (an intent that allows it be fired on behalf of you)
        val notificationPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        //Creating Notification builder
        val notifyBuilder = NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
            .setContentTitle("Favorite Player Stat Changed")
            .setContentText("${playerName} stat has changed")
            .setSmallIcon(R.drawable.ic_small_app_logo)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        return notifyBuilder

    }
    fun createNotificationChannel(){
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            var notificationChannel:NotificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID, "Favorite Player Stats", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableVibration(true)
            notificationChannel.description = "A Favorite Player Stats Have Changed"
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
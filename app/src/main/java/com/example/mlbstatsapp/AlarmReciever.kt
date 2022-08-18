package com.example.mlbstatsapp

import android.app.NotificationManager
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

class AlarmReciever: BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        var mainViewModel:MainViewModel = MainViewModel(context)
        Log.d(AlarmReciever::class.java.simpleName, "Alarm just fired")

        var main:MainActivity = MainActivity()
        val NOTIFICATION_ID = 0

        val batterList:List<Batter> = mainViewModel.getAllBatters()
        val pitcherList:List<Pitcher> = mainViewModel.getAllPitchers()
        Log.d(AlarmReciever::class.java.simpleName, batterList.size.toString())
        Log.d(AlarmReciever::class.java.simpleName, pitcherList.size.toString())

        if(batterList.isNotEmpty()){
            for(batter in batterList){
                var batterId:Int = batter.playerId.toInt()
                var batterRbi = batter.rbi
                var batterHr = batter.hr
                var batterBa = batter.ba
                var batterName = batter.firstName + " " + batter.lastName
                mainViewModel.getPlayerHittingStatsData().observe(main, Observer<PlayerHittingStats>{
                    if(it != null){
                        Log.d(AlarmReciever::class.java.simpleName, (it as PlayerHittingStats).toString())
                        var recievedRbi = it.rbi.toInt()
                        var recievedHr = it.hr.toInt()
                        var recievedBa = it.babip.toFloat()

                        if(recievedRbi > batterRbi || recievedHr > batterHr || recievedBa > batterBa){
                            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            var builder: NotificationCompat.Builder  =  NotificationCompat.Builder(context)
                                .setContentTitle("A Favorited Player Stat Has Changed")
                                .setContentText(batterName + " stat has changed")
                                //.setContentIntent(contentPendingIntent)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setAutoCancel(true)
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                            nManager.notify(NOTIFICATION_ID, builder.build())
                        }
                    }else{
                        Log.d(AlarmReciever::class.java.simpleName, "SOMETHING WENT WRONG")
                    }
                })
                mainViewModel.getPlayerHittingStats(batterId)
            }
        }
        if(pitcherList.isNotEmpty()){
            for(pitcher in pitcherList){
                var pitcherId:Int = pitcher.playerId.toInt()
                var pitcherEra:Float = pitcher.era.toFloat()
                var pitcherWins:Int = pitcher.wins.toInt()
                var pitcherLosses:Int = pitcher.losses
                var pitcherName = pitcher.firstName + " " + pitcher.lastName

                mainViewModel.getPlayerPitchingStatsData().observe(main, Observer<PlayerPitchingStat>{
                    if(it != null){
                        Log.d(AlarmReciever::class.java.simpleName, (it as PlayerPitchingStat).toString())
                        var recievedEra:Float = it.era.toFloat()
                        var recievedWins:Int = it.w.toInt()
                        var recievedLosses = it.l.toInt()
                        if(recievedEra > pitcherEra || recievedWins > pitcherWins || recievedLosses > pitcherLosses){
                            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            var builder: NotificationCompat.Builder  =  NotificationCompat.Builder(context)
                                .setContentTitle("A Favorited Player Stat Has Changed")
                                .setContentText(pitcherName + " stat has changed")
                                //.setContentIntent(contentPendingIntent)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setAutoCancel(true)
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                            nManager.notify(NOTIFICATION_ID, builder.build())
                        }
                    }else{
                        Log.d(AlarmReciever::class.java.simpleName, "SOMETHING WENT WRONG")
                    }
                })
                mainViewModel.getPlayerPitchingStats(pitcherId)
            }
        }
    }
}
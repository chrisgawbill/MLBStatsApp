package com.example.mlbstatsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Team::class, Batter::class, Pitcher::class, User::class, UserWithTeams::class], version =1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTeamDao(): TeamDao
    abstract fun getBatterDao():BatterDao
    abstract fun getPitcherDao():PitcherDao
    abstract fun getUserWithTeamsDao(): UserWithTeamsDao
    abstract fun getUserDao(): UserDao



    companion object {
        private var db_instance: AppDatabase? =null

        fun getAppDatabaseInstance(context: Context): AppDatabase{

                if(db_instance == null){
                    db_instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, "mlb_stats"
                    )

                        .allowMainThreadQueries()
                        .build()
                }
                    return db_instance!!
            }


   }

}

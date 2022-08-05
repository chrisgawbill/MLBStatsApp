package com.example.mlbstatsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Team::class, Batter::class, Pitcher::class], version =1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTeamDao(): TeamDao

    companion object{
        private var db_instance: AppDatabase? =null

        fun getAppDatabaseInstance(context: Context): AppDatabase{

            if(db_instance == null){
                db_instance= Room.databaseBuilder<AppDatabase>(
                    context.applicationContext, AppDatabase::class.java, "app_db"
                )

                    .allowMainThreadQueries()
                    .build()
            }
                return db_instance!!
        }

    }
}
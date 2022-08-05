package com.example.mlbstatsapp.di

import android.app.Application
import android.content.Context
import com.example.mlbstatsapp.database.AppDatabase
import com.example.mlbstatsapp.database.TeamDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application : Application) {

    @Singleton
    @Provides
    fun getTeamDao(appDatabase: AppDatabase): TeamDao{
        return appDatabase.getTeamDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): AppDatabase{
        return AppDatabase.getAppDatabaseInstance(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }

}
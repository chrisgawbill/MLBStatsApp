package com.example.mlbstatsapp.di

import com.example.mlbstatsapp.LoadData
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {

    fun inject(loadData: LoadData)
}
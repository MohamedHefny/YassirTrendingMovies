package com.example.yassirtrendingmovies

import android.app.Application
import com.example.trendingmovies.di.ServiceLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.appContext = this
    }

}
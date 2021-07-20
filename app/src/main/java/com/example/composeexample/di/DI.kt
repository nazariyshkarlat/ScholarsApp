package com.example.composeexample.di

import android.app.Application
import com.example.composeexample.core.putApplication
import com.example.composeexample.di.feature.articlesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object DI{

    fun init(application: Application){
        putApplication(application)
        startKoin{
            androidLogger()
            androidContext(application)
            modules(
                networkModule,
                articlesModule
            )
        }
    }

}
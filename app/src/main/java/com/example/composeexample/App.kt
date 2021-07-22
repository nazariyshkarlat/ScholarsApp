package com.example.composeexample

import android.app.Application
import com.example.composeexample.di.DI
import org.koin.core.context.GlobalContext

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        DI.init(this)
    }

}
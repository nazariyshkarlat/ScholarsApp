package com.example.composeexample.core

import android.app.Application

val application: Application
get() = _application!!

private var _application: Application? = null

val isInitialized
get() = _application != null

fun putApplication(application: Application){
    if(!isInitialized){
        _application = application
    }
}

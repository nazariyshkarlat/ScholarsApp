package com.example.composeexample.data.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.composeexample.App

val connectivityManager by lazy{
    App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

val isNetworkAbsent: Boolean
    get() {
        val n = connectivityManager.activeNetwork
        if (n != null) {
            val nc = connectivityManager.getNetworkCapabilities(n)
            return !((nc?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true) ||
                    (nc?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true))
        }
        return true
    }

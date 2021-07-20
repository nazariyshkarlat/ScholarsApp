package com.example.composeexample.di

import android.util.Log
import com.example.composeexample.data.ktor.xml.XmlFeature
import com.example.composeexample.data.ktor.xml.XmlSerializer
import com.example.composeexample.extensions.unescape
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.dsl.module

val networkModule = module {
    single{
        HttpClient {
            install(HttpTimeout) {
                requestTimeoutMillis = 25 * 1000
            }
            install(XmlFeature) {
                serializer = XmlSerializer.Default()
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HttpClient", message.unescape().toString())
                    }
                }
                level = LogLevel.BODY
            }
            install(DefaultRequest) {
                accept(ContentType.Application.Xml)
            }
        }
    }
}
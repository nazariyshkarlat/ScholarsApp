package com.example.composeexample

import com.example.composeexample.di.feature.articleDetailsModule
import com.example.composeexample.di.feature.articlesListModule
import com.example.composeexample.di.feature.articlesModule
import com.example.composeexample.di.networkModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinTest : KoinTest {
    @Test
    fun checkAllModules() = checkModules {
        modules(networkModule, articlesModule, articleDetailsModule, articlesListModule)
        // other KoinApplication attributes if needed
    }
}
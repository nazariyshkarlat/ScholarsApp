package com.example.composeexample.di.feature

import com.example.composeexample.BuildConfig
import com.example.composeexample.data.use_case.get_articles.data_source.api.ArticlesApiImpl
import org.koin.dsl.module

val articlesModule = module {
    single {
        ArticlesApiImpl(BuildConfig.BASE_URL, get())
    }
}
package com.example.composeexample.di.feature

import com.example.composeexample.data.use_case.articles.ArticlesRepositoryImpl
import com.example.composeexample.domain.feature.article.repository.ArticlesRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val articlesModule = module {
    single {
        ArticlesRepositoryImpl(
            get(articlesListDataSourceQualifier),
            get(articleDetailsDataSourceQualifier),
            get(articleDetailsDataStoreQualifier),
            get(),
            get(),
            get())
    } bind ArticlesRepository::class
}
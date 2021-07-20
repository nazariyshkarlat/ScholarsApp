package com.example.composeexample.di.feature

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeexample.BuildConfig
import com.example.composeexample.data.data_source.DataSource
import com.example.composeexample.data.data_source.DefDataSourceProvider
import com.example.composeexample.data.data_source.api.BaseNetworkRequest
import com.example.composeexample.data.data_source.cache.BaseCacheRequest
import com.example.composeexample.data.use_case.get_articles.data_source.GetArticlesDataSource
import com.example.composeexample.data.use_case.get_articles.data_source.api.GetArticlesNetworkRequest
import com.example.composeexample.data.use_case.get_articles.data_source.cache.GetArticlesCacheRequest
import com.example.composeexample.data.use_case.get_articles.entity.ArticlesResponse
import com.example.composeexample.data.use_case.get_articles.repository.ArticlesRepositoryImpl
import com.example.composeexample.domain.feature.articles.repository.ArticlesRepository
import com.example.composeexample.domain.feature.articles.use_case.GetArticles
import com.example.composeexample.domain.feature.articles.use_case.GetArticlesImpl
import com.example.composeexample.presentation.feature.articles_list.ArticlesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.math.sin

val articlesModule = module {

    single {
        GetArticlesNetworkRequest(BuildConfig.BASE_URL, get())
    } bind BaseNetworkRequest::class

    single {
        GetArticlesCacheRequest()
    } bind BaseCacheRequest::class

    single {
        GetArticlesDataSource(get(), get())
    } bind DataSource::class

    single {
        ArticlesRepositoryImpl(DefDataSourceProvider, get())
    } bind ArticlesRepository::class

    single {
        GetArticlesImpl(get())
    } bind GetArticles::class

    viewModel{
        ArticlesListViewModel(get())
    }

}
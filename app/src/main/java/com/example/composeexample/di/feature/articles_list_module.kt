package com.example.composeexample.di.feature

import com.example.composeexample.data.data_source.DataSource
import com.example.composeexample.data.data_source.DataSourceProvider
import com.example.composeexample.data.use_case.articles.articles_list.data_source.ArticlesDataSource
import com.example.composeexample.data.use_case.articles.articles_list.data_source.ArticlesDataSourceProvider
import com.example.composeexample.data.use_case.articles.ArticlesRepositoryImpl
import com.example.composeexample.domain.feature.article.repository.ArticlesRepository
import com.example.composeexample.domain.feature.article.articles_list.use_case.get_articles.GetArticles
import com.example.composeexample.domain.feature.article.articles_list.use_case.get_articles.GetArticlesImpl
import com.example.composeexample.presentation.feature.articles_list.view_model.ArticlesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val articlesListDataSourceQualifier = named("articles_list_data_source")

val articlesListModule = module {

    single {
        ArticlesDataSource()
    } bind DataSource::class

    single(articlesListDataSourceQualifier) {
        ArticlesDataSourceProvider(get())
    } bind DataSourceProvider::class

    single {
        GetArticlesImpl(get())
    } bind GetArticles::class

    viewModel{
        ArticlesListViewModel(get(), get())
    }

}
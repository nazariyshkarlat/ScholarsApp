package com.example.composeexample.di.feature

import com.example.composeexample.data.data_source.DataSource
import com.example.composeexample.data.data_source.DataSourceProvider
import com.example.composeexample.data.data_source.DataStore
import com.example.composeexample.data.data_source.memory.MemoryStorage
import com.example.composeexample.data.use_case.articles.article_details.data_source.ArticleDetailsDataSource
import com.example.composeexample.data.use_case.articles.article_details.data_source.ArticleDetailsDataSourceProvider
import com.example.composeexample.data.use_case.articles.article_details.data_source.ArticleDetailsDataStore
import com.example.composeexample.data.use_case.articles.article_details.data_source.ArticleDetailsDataStoreProvider
import com.example.composeexample.data.use_case.articles.article_details.data_source.memory.ArticleDetailsMemoryStorage
import com.example.composeexample.data.use_case.articles.articles_list.data_source.ArticlesDataSource
import com.example.composeexample.data.use_case.articles.articles_list.data_source.ArticlesDataSourceProvider
import com.example.composeexample.domain.feature.article.article_details.use_case.get_article.GetArticle
import com.example.composeexample.domain.feature.article.article_details.use_case.get_article.GetArticleImpl
import com.example.composeexample.domain.feature.article.article_details.use_case.save_article.SaveArticle
import com.example.composeexample.domain.feature.article.article_details.use_case.save_article.SaveArticleImpl
import com.example.composeexample.domain.feature.article.articles_list.use_case.get_articles.GetArticles
import com.example.composeexample.domain.feature.article.articles_list.use_case.get_articles.GetArticlesImpl
import com.example.composeexample.presentation.feature.article_details.view_model.ArticleDetailsViewModel
import com.example.composeexample.presentation.feature.articles_list.view_model.ArticlesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module


val articleDetailsDataStoreQualifier = named("article_details_data_store")
val articleDetailsDataSourceQualifier = named("article_details_data_source")

val articleDetailsModule = module {

    single {
        ArticleDetailsDataSource()
    } bind DataSource::class

    single {
        ArticleDetailsDataStore()
    } bind DataStore::class

    single(articleDetailsDataSourceQualifier) {
        ArticleDetailsDataSourceProvider(get())
    } bind DataSourceProvider::class

    single(articleDetailsDataStoreQualifier) {
        ArticleDetailsDataStoreProvider(get())
    } bind DataSourceProvider::class

    single {
        GetArticleImpl(get())
    } bind GetArticle::class

    single {
        SaveArticleImpl(get())
    } bind SaveArticle::class

    single {
        ArticleDetailsMemoryStorage()
    } bind MemoryStorage::class

    viewModel { (articleId: String) ->
        ArticleDetailsViewModel(articleId, get())
    }
}
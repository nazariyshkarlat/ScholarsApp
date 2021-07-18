package com.example.composeexample.domain.feature.articles.use_case

import com.example.composeexample.domain.feature.articles.repository.ArticlesRepository
import com.example.composeexample.domain.response.event.GetArticlesEvent
import com.example.composeexample.domain.response.event.ResponseEvent
import com.example.composeexample.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetArticlesImpl(private val repository: ArticlesRepository) : GetArticles {
    override fun getArticles(): Flow<GetArticlesEvent> = flow{
        emit(ResponseEvent.Loading)
        when(val result = repository.getArticles()){
            is Result.LocalException -> emit(ResponseEvent.Exception)
            is Result.NetworkError -> emit(ResponseEvent.NetworkUnavailable)
            is Result.CacheIsEmpty -> emit(GetArticlesEvent.NoArticlesFound)
            is Result.ServerError -> emit(GetArticlesEvent.ServerError)
            is Result.Success -> emit(GetArticlesEvent.ArticlesFound(articles = result.data))
        }
    }.flowOn(Dispatchers.IO)
}
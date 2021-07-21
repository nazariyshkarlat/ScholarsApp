package com.example.composeexample.domain.feature.article.articles_list.use_case.get_articles

import com.example.composeexample.domain.feature.article.repository.ArticlesRepository
import com.example.composeexample.domain.response.event.GetArticlesEvent
import com.example.composeexample.domain.response.event.ResponseEvent
import com.example.composeexample.domain.response.event.toGetArticlesEvent
import com.example.composeexample.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetArticlesImpl(private val repository: ArticlesRepository) : GetArticles {
    override fun getArticles(): Flow<GetArticlesEvent> = flow{
        emit(ResponseEvent.Loading)
        emit(repository.getArticles().toGetArticlesEvent())
    }.flowOn(Dispatchers.IO)
}

interface GetArticles {
    fun getArticles(): Flow<GetArticlesEvent>
}
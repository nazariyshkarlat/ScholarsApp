package com.example.composeexample.domain.feature.articles.use_case

import com.example.composeexample.domain.response.event.GetArticlesEvent
import kotlinx.coroutines.flow.Flow

interface GetArticles {
    fun getArticles(): Flow<GetArticlesEvent>
}
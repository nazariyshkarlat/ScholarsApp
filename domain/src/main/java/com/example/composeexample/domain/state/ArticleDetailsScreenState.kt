package com.example.composeexample.domain.state

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.intent.ArticleDetailsScreenIntent
import com.example.composeexample.domain.intent.Intent
import com.example.composeexample.domain.mvi.reducer

sealed class ArticleDetailsScreenState {

    companion object{
        val initialValue = Loading
    }

    object Loading : ArticleDetailsScreenState()
    data class ArticleDetails(val article: Article): ArticleDetailsScreenState()
}


fun articleDetailsScreenReducer() = reducer<ArticleDetailsScreenState, ArticleDetailsScreenIntent>{ currentState, intent->
    when(intent){
        is ArticleDetailsScreenIntent.ShowArticleDetails -> ArticleDetailsScreenState.ArticleDetails(article = intent.articleDetails)
        Intent.DoNothing -> currentState
    }
}


package com.example.composeexample.presentation.feature.article_details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.feature.article.article_details.use_case.get_article.GetArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    private val articleId: String,
    private val getArticle: GetArticle
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getArticle.getArticle(articleId).collect {
                println(it)
            }
        }
    }
}
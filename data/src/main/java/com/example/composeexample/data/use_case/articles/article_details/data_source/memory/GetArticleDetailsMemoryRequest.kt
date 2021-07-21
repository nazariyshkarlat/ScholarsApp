package com.example.composeexample.data.use_case.articles.article_details.data_source.memory

import com.example.composeexample.data.data_source.memory.BaseMemoryRequest
import com.example.composeexample.data.data_source.memory.MemoryResult
import com.example.composeexample.data.data_source.memory.MemoryStorage
import com.example.composeexample.domain.feature.article.entity.Article
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetArticleDetailsMemoryRequest(private val articleId: String) : BaseMemoryRequest<Article>, KoinComponent {

    override val memoryStorage: MemoryStorage<Article> by inject()

    override fun makeRequest(): MemoryResult<Article> {
        return memoryStorage.getElement(articleId)
    }
}
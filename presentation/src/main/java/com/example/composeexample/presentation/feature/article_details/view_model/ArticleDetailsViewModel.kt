package com.example.composeexample.presentation.feature.article_details.view_model

import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.action.ArticleDetailsScreenClientAction
import com.example.composeexample.domain.event.*
import com.example.composeexample.domain.feature.article.article_details.use_case.get_article.GetArticle
import com.example.composeexample.domain.intent.ArticleDetailsScreenIntent
import com.example.composeexample.domain.response.event.toArticleDetailsScreenEvent
import com.example.composeexample.domain.state.ArticleDetailsScreenState
import com.example.composeexample.domain.state.articleDetailsScreenReducer
import com.example.composeexample.presentation.feature.article_details.state.ArticleDetailsScreenUiState
import com.example.composeexample.presentation.feature.article_details.state.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    private val articleId: String,
    private val getArticle: GetArticle
) : BaseViewModel() {
    override val state: MutableStateFlow<ArticleDetailsScreenState> = MutableStateFlow(
        ArticleDetailsScreenState.initialValue
    )
    override val uiState: StateFlow<ArticleDetailsScreenUiState> by lazy {
        state.map {
            it.toUi()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = state.value.toUi()
        )
    }

    override val _events: Channel<ArticleDetailsScreenClientEvent> = Channel(Channel.BUFFERED)

    init {
        loadArticle(articleId = articleId)
    }

    override fun consumeClientAction(action: ArticleDetailsScreenClientAction) {
        TODO("Not yet implemented")
    }

    override fun consumeInternalEvent(internalEvent: ArticleDetailsScreenEvent) {
        consumeIntent(internalEvent.toIntent())
    }

    override fun consumeIntent(intent: ArticleDetailsScreenIntent) {
        _events.trySend(articleDetailsScreenEventHandler().invoke(state.value, intent))
        state.value = articleDetailsScreenReducer().invoke(state.value, intent)
    }

    private fun loadArticle(articleId: String){
        viewModelScope.launch(Dispatchers.IO) {
            getArticle.getArticle(articleId = articleId).collect {
                consumeInternalEvent(it.toArticleDetailsScreenEvent())
            }
        }
    }

}

private typealias BaseViewModel =
        com.example.composeexample.presentation.base.BaseViewModel<
                ArticleDetailsScreenState,
                ArticleDetailsScreenUiState,
                ArticleDetailsScreenIntent,
                ArticleDetailsScreenClientEvent,
                ArticleDetailsScreenClientAction,
                ArticleDetailsScreenEvent>


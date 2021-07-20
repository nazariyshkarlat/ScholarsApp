package com.example.composeexample.presentation.feature.articles_list

import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.action.ArticlesScreenClientAction
import com.example.composeexample.domain.action.toArticlesScreenIntent
import com.example.composeexample.domain.event.ArticlesScreenEvent
import com.example.composeexample.domain.event.toIntent
import com.example.composeexample.domain.feature.articles.use_case.GetArticles
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.response.event.toArticlesScreenEvent
import com.example.composeexample.domain.state.ArticlesScreenState
import com.example.composeexample.domain.state.articlesScreenReducer
import com.example.composeexample.domain.event.ArticlesScreenClientEvent
import com.example.composeexample.domain.event.articlesScreenEventHandler
import com.example.composeexample.presentation.feature.articles_list.state.ArticlesScreenUiState
import com.example.composeexample.presentation.feature.articles_list.state.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArticlesListViewModel(
    private val getArticles: GetArticles
) : BaseViewModel() {

    override val state: MutableStateFlow<ArticlesScreenState> = MutableStateFlow(
        value = ArticlesScreenState.Loading
    )

    override val uiState: StateFlow<ArticlesScreenUiState> = state.map {
        it.toUi()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = state.value.toUi()
    )

    override val _events: Channel<ArticlesScreenClientEvent> = Channel(Channel.BUFFERED)

    init {
        requestArticles()
    }

    override fun consumeClientAction(action: ArticlesScreenClientAction) {
        consumeIntent(action.toArticlesScreenIntent(state.value))
    }

    override fun consumeInternalEvent(internalEvent: ArticlesScreenEvent) {
        consumeIntent(internalEvent.toIntent())
    }

    override fun consumeIntent(intent: ArticlesScreenIntent) {
        _events.trySend(articlesScreenEventHandler().invoke(state.value, intent))
        state.value = articlesScreenReducer().invoke(state.value, intent)
    }

    private fun requestArticles(){
        viewModelScope.launch(Dispatchers.IO){
            getArticles.getArticles().collect {
                consumeInternalEvent(it.toArticlesScreenEvent())
            }
        }
    }
}

private typealias BaseViewModel = com.example.composeexample.presentation.base.BaseViewModel<ArticlesScreenState, ArticlesScreenUiState, ArticlesScreenIntent, ArticlesScreenClientEvent, ArticlesScreenClientAction, ArticlesScreenEvent>
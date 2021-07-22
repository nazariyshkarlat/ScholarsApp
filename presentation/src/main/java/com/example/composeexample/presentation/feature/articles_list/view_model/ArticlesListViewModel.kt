package com.example.composeexample.presentation.feature.articles_list.view_model

import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.action.ArticlesScreenClientAction
import com.example.composeexample.domain.action.toArticlesScreenIntent
import com.example.composeexample.domain.event.ArticlesScreenEvent
import com.example.composeexample.domain.event.toIntent
import com.example.composeexample.domain.feature.article.articles_list.use_case.get_articles.GetArticles
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.response.event.toArticlesScreenEvent
import com.example.composeexample.domain.state.ArticlesScreenState
import com.example.composeexample.domain.state.articlesScreenReducer
import com.example.composeexample.domain.event.ArticlesScreenClientEvent
import com.example.composeexample.domain.event.articlesScreenEventHandler
import com.example.composeexample.domain.feature.article.article_details.use_case.save_article.SaveArticle
import com.example.composeexample.domain.feature.article.entity.Category
import com.example.composeexample.presentation.feature.articles_list.state.ArticlesScreenUiState
import com.example.composeexample.presentation.feature.articles_list.state.toUi
import com.example.composeexample.domain.navigation.NavigationDirections
import com.example.composeexample.domain.navigation.NavigationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArticlesListViewModel(
    private val getArticles: GetArticles,
    private val saveArticle: SaveArticle
) : BaseViewModel() {

    override val state: MutableStateFlow<ArticlesScreenState> = MutableStateFlow(
        value = ArticlesScreenState.Loading(
            category = Category(term = com.example.composeexample.domain.entity.Category.Astrophysics.category, scheme = "")
        )
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
        when(intent){
            is ArticlesScreenIntent.ShowArticleScreen -> {
                viewModelScope.launch(Dispatchers.IO) {
                    saveArticle.saveArticle(intent.article)
                    NavigationManager.navigate(
                        NavigationDirections.ArticleDetails.create(
                            arguments = mapOf(
                                NavigationDirections.ArticleDetails.Arguments.ARTICLE_ID to intent.article.id
                            )
                        )
                    )
                }
            }
            else -> Unit
        }
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
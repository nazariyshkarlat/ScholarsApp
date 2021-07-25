package com.example.composeexample.presentation.feature.articles_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeexample.domain.action.ArticlesScreenClientAction
import com.example.composeexample.presentation.feature.articles_list.entity.ArticleListItemUi
import com.example.composeexample.presentation.feature.articles_list.state.ArticlesScreenUiState
import com.example.composeexample.presentation.feature.articles_list.view_model.ArticlesListViewModel
import com.example.composeexample.presentation.theme.*

@Composable
fun ArticlesListScreen(viewModel: ArticlesListViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val onItemClick: (id: String) -> Unit = {
        viewModel.consumeClientAction(ArticlesScreenClientAction.ArticleClick(it))
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(category = uiState.category)
        when (val state = uiState) {
            is ArticlesScreenUiState.ArticlesListScreen -> ArticlesListScreen(
                articles = state.articles,
                onItemClick
            )
            is ArticlesScreenUiState.EmptyArticlesScreen -> EmptyScreen()
            is ArticlesScreenUiState.Error -> ErrorScreen(errorText = state.errorType.name)
            is ArticlesScreenUiState.Loading -> LoadingScreen()
        }
    }
}

@Composable
private fun ArticlesListScreen(
    articles: List<ArticleListItemUi>,
    onItemClick: (id: String) -> Unit
){
    LazyColumn {
        items(articles){
            ArticleItem(article = it, onClick = onItemClick)
        }
    }
}

@Composable
private fun Toolbar(category: String){
    Surface(
        color = MaterialTheme.appColors.background,
        elevation = SmallDimension
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.appColors.surface)
                .padding(
                    start = MediumDimension,
                    end = MediumDimension,
                    top = MediumDimension,
                    bottom = MediumDimension
                )
                .wrapContentHeight()
        ) {
            Text(
                text = category,
                color = MaterialTheme.appColors.onBackground,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
private fun ArticleItem(article: ArticleListItemUi, onClick: (id: String) -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true),
            onClick = {
                onClick.invoke(article.id)
            }
        )
        .padding(
            top = SmallDimension,
            bottom = SmallDimension,
            start = MediumDimension,
            end = MediumDimension
        )
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Start,
            color = MaterialTheme.appColors.onBackground
        )
        Text(
            modifier = Modifier.padding(top = SmallDimension),
            text = article.authors,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Start,
            color = MaterialTheme.appColors.onBackground
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = SmallDimension)
        ) {
            Text(
                text = article.category,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.appColors.primary
            )
            Text(
                modifier = Modifier.padding(start = SmallDimension),
                text = article.date,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.appColors.onBackgroundSecondary
            )
            article.doi?.let {
                Text(
                    modifier = Modifier.padding(start = SmallDimension),
                    text = it,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.appColors.onBackgroundSecondary
                )
            }
        }
    }
}

@Composable
private fun EmptyScreen(){

}

@Composable
private fun LoadingScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator(
            modifier = Modifier
                .size(ProgressBarSize)
                .align(Alignment.Center),
            strokeWidth = ProgressBarStrokeWidth
        )
    }
}

@Composable
private fun ErrorScreen(errorText: String){
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.appColors.error,
            text = errorText
        )
    }
}
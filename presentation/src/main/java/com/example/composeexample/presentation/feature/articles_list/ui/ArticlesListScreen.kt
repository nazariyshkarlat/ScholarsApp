package com.example.composeexample.presentation.feature.articles_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composeexample.presentation.feature.articles_list.ArticlesListViewModel
import com.example.composeexample.presentation.feature.articles_list.entity.ArticleListItemUi
import com.example.composeexample.presentation.feature.articles_list.state.ArticlesScreenUiState
import com.example.composeexample.presentation.theme.MediumDimension
import com.example.composeexample.presentation.theme.SmallDimension

@Composable
fun ArticlesListScreen(viewModel: ArticlesListViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val onItemClick: (id: String) -> Unit = {

    }

    when(val state = uiState){
        is ArticlesScreenUiState.ArticlesListScreen -> ArticlesListScreen(articles = state.articles, onItemClick)
        ArticlesScreenUiState.EmptyArticlesScreen -> EmptyScreen()
        is ArticlesScreenUiState.Error -> ErrorScreen(errorText = state.errorType.name)
        ArticlesScreenUiState.Loading -> LoadingScreen()
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
        ).padding(
            top = SmallDimension,
            bottom = SmallDimension,
            start = MediumDimension,
            end = MediumDimension
        )
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.padding(top = SmallDimension),
            text = article.authors,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Start
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = SmallDimension)
        ) {
            Text(
                text = article.category,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primary
            )
            Text(
                modifier = Modifier.padding(start = SmallDimension),
                text = article.date,
                style = MaterialTheme.typography.subtitle2
            )
            article.doi?.let {
                Text(
                    modifier = Modifier.padding(start = SmallDimension),
                    text = it,
                    style = MaterialTheme.typography.subtitle2
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
                .size(24F.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun ErrorScreen(errorText: String){
    Box{
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.error,
            text = errorText
        )
    }
}
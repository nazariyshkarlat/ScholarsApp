package com.example.composeexample.presentation.feature.article_details.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeexample.domain.navigation.NavigationManager
import com.example.composeexample.presentation.feature.article_details.entity.*
import com.example.composeexample.presentation.feature.article_details.state.ArticleDetailsScreenUiState
import com.example.composeexample.presentation.feature.article_details.view_model.ArticleDetailsViewModel
import com.example.composeexample.presentation.theme.*

@Composable
fun ArticleDetailsScreen(viewModel: ArticleDetailsViewModel){

    val backButtonClick = {
        NavigationManager.navigateBack()
    }

    val uiState by viewModel.uiState.collectAsState()

    when(val state = uiState){
       is ArticleDetailsScreenUiState.ArticleDetails -> {
           ArticleDetailsScreen(article = state.article, backButtonClick = backButtonClick)
       }
    }
}

@Composable
private fun ArticleDetailsScreen(article: ArticleDetailsUi, backButtonClick: () -> Unit){

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .wrapContentHeight()
        .verticalScroll(
            state = scrollState
        )
    ) {
        Toolbar(backButtonClick = backButtonClick)
        Box(
            modifier = Modifier
                .padding(
                    start = MediumDimension,
                    end = MediumDimension,
                    top = MediumDimension,
                    bottom = MediumDimension
                )
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Title(title = article.title)
                Authors(authors = article.authors)
                Summary(summary = article.summary)
                Comments(comments = article.comments)
                SubmissionHistory(submissionHistory = article.submissionHistory)
                Links(links = article.links)
                article.doi?.let { DOI(doi = it) }
                Subjects(subjects = article.subjects)
            }
        }
    }
}

@Composable
private fun Toolbar(backButtonClick: () -> Unit){
    Surface(
        color = MaterialTheme.appColors.background,
        elevation = SmallDimension
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.appColors.surface)
                .height(ToolbarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(IconsSize+IconsClickLargePadding*2),
                onClick = backButtonClick,
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.appColors.onBackground
                )
            }
        }
    }
}

@Composable
private fun Title(title: String){
    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.appColors.onBackground
    )
}

@Composable
private fun Summary(summary: String){
    Text(
        modifier = Modifier.padding(top = MediumDimension),
        text = summary,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.appColors.onBackground
    )
}

@Composable
private fun Authors(authors: String){
    Text(
        modifier = Modifier.padding(top = MediumDimension),
        text = authors,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.appColors.onBackground
    )
}

@Composable
private fun Comments(comments: Comments){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MediumDimension
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = comments.title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.appColors.onBackground
        )
        Text(
            modifier = Modifier.padding(start = MediumDimension),
            text = comments.text,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.appColors.onBackgroundSecondary
        )
    }
}

@Composable
private fun SubmissionHistory(submissionHistory: SubmissionHistory){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = MediumDimension
            )
    ) {
        Text(
            text = submissionHistory.title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.appColors.onBackground
        )
        Text(
            modifier = Modifier.padding(top = SmallDimension),
            text = submissionHistory.published,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.appColors.onBackgroundSecondary
        )
        submissionHistory.updated?.let {
            Text(
                modifier = Modifier.padding(top = SmallDimension),
                text = it,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.appColors.onBackgroundSecondary
            )
        }
    }
}

@Composable
private fun Links(links: Links){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = MediumDimension
            )
    ) {
        Text(
            text = links.title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.appColors.onBackground
        )
        links.links.forEach {
            Row(
                modifier = Modifier
                    .padding(
                        top = SmallDimension/2,
                        bottom = SmallDimension/2
                    ).fillMaxWidth()
            ) {
                val hasTitle = it.title != null

                it.title?.let { title->
                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.appColors.onBackgroundSecondary
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(
                            start = if(hasTitle) MediumDimension else 0F.dp,
                        ),
                    text = it.link,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.appColors.onBackgroundSecondary
                )
            }
        }
    }
}


@Composable
private fun DOI(doi: DOI){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MediumDimension
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = doi.title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.appColors.onBackground
        )
        Text(
            modifier = Modifier.padding(start = BigDimension),
            text = doi.text,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.appColors.onBackgroundSecondary
        )
    }
}

@Composable
private fun Subjects(subjects: Subjects){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MediumDimension
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = subjects.title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.appColors.onBackground
        )
        Text(
            modifier = Modifier.padding(start = BigDimension),
            text = subjects.text,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.appColors.onBackgroundSecondary
        )
    }
}
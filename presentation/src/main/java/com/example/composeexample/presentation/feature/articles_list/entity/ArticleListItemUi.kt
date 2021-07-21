package com.example.composeexample.presentation.feature.articles_list.entity

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.presentation.extensions.formattedDate
import com.example.composeexample.presentation.extensions.str
import com.example.presentation.R
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ArticleListItemUi(
    val id: String,
    val date: String,
    val title: String,
    val summary: String,
    val authors: String,
    val doi: String?,
    val category: String
)

fun Article.toListItemUi() = ArticleListItemUi(
    id = id,
    date = published.toPublishedDate(),
    title = title,
    summary = summary,
    doi = doi?.toDoi(),
    authors = authors.toUi(),
    category = category.toUi()
)

private fun String.toDoi() = R.string.case_template_doi.str(this)

private fun Date.toPublishedDate() = R.string.case_template_date.str(this.formattedDate)

fun com.example.composeexample.domain.feature.article.entity.Category.toUi() =
    when(com.example.composeexample.domain.entity.Category.byName(term)){
        com.example.composeexample.domain.entity.Category.ASTROPHYSICS -> R.string.case_category_astrophysics.str
    }

private fun List<com.example.composeexample.domain.feature.article.entity.Author>.toUi() =
    when(this.size){
        1 -> R.string.case_template_authors_one.str(first().name)
        2 -> R.string.case_template_authors_many.str(*this.map { it.name }.toTypedArray())
        else -> R.string.case_template_authors_many.str(*this.map { it.name }.toTypedArray())
    }
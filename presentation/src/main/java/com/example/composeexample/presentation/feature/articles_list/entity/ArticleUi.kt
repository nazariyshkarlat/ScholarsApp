package com.example.composeexample.presentation.feature.articles_list.entity

import com.example.composeexample.domain.feature.articles.entity.Article
import kotlinx.serialization.Serializable

@Serializable
data class ArticleUi(
    val id: String,
    val updated: String,
    val published: String,
    val title: String,
    val summary: String,
    val authors: List<Author>,
    val doi: String? = null,
    val comment: String,
    val journalRef: String? = null,
    val primaryCategory: Category,
    val links: List<ArticleLink>,
    val category: Category
)

@Serializable
data class ArticleLink(
    val title: String? = null,
    val href: String,
    val rel: String,
    val type: String? = null
)

@Serializable
data class Category(
    val term: String,
    val scheme: String
)

@Serializable
data class Author(
    val name: String
)

fun Article.toUi() = ArticleUi(
    id = id,
    updated = updated,
    published = published,
    title = title,
    summary = summary,
    doi = doi,
    comment = comment,
    journalRef = journalRef,
    authors = authors.map { it.toUi() },
    primaryCategory = primaryCategory.toUi(),
    links = links.map { it.toUi() },
    category = category.toUi()
)

fun com.example.composeexample.domain.feature.articles.entity.ArticleLink.toUi() = ArticleLink(
    title = title,
    href = href,
    rel = rel,
    type = type
)

fun com.example.composeexample.domain.feature.articles.entity.Category.toUi() = Category(
    term = term,
    scheme = scheme
)

fun com.example.composeexample.domain.feature.articles.entity.Author.toUi() = Author(
    name = name
)
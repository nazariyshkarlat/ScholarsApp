package com.example.composeexample.domain.feature.articles.entity

import kotlinx.serialization.Serializable

@Serializable
data class Article(
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
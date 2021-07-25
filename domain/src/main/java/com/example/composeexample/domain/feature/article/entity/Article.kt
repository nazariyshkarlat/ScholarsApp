package com.example.composeexample.domain.feature.article.entity

import com.example.composeexample.domain.extensions.date.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Article(
    val id: String,
    @Serializable(with = DateSerializer::class)
    val updated: Date,
    @Serializable(with = DateSerializer::class)
    val published: Date,
    val title: String,
    val summary: String,
    val authors: List<Author>,
    val doi: String?,
    val comment: String,
    val journalRef: String?,
    val primaryCategory: Category,
    val links: List<ArticleLink>,
    val category: Category
)

@Serializable
data class ArticleLink(
    val title: String?,
    val href: String,
    val rel: String,
    val type: String?
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
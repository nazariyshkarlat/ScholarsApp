package com.example.composeexample.data.use_case.articles.articles_list.entity

import com.example.composeexample.domain.extensions.date.date
import com.example.composeexample.domain.feature.article.entity.Article
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName(value = "feed", namespace = "http://www.w3.org/2005/Atom", prefix = "")
data class ArticlesResponse(
    @XmlElement(true)
    val link: Link,
    @XmlElement(true)
    val title: Title,
    @XmlElement(true)
    val id: String,
    @XmlElement(true)
    val updated: String,
    @XmlSerialName(value = "totalResults", namespace = "http://a9.com/-/spec/opensearch/1.1/", prefix = "opensearch")
    @XmlElement(true)
    val totalResults: Int,
    @XmlSerialName(value = "startIndex", namespace = "http://a9.com/-/spec/opensearch/1.1/", prefix = "opensearch")
    @XmlElement(true)
    val startIndex: Int,
    @XmlSerialName(value = "itemsPerPage", namespace = "http://a9.com/-/spec/opensearch/1.1/", prefix = "opensearch")
    @XmlElement(true)
    val itemsPerPage: Int,
    @XmlElement(true)
    @XmlSerialName(value = "entry", namespace = "http://www.w3.org/2005/Atom", prefix = "")
    val articles: List<ArticleEntity>
)

@Serializable
data class ArticleEntity(
    @XmlElement(true)
    val id: String,
    @XmlElement(true)
    val updated: String,
    @XmlElement(true)
    val published: String,
    @XmlElement(true)
    val title: String,
    @XmlElement(true)
    val summary: String,
    @XmlElement(true)
    val authors: List<Author>,
    @XmlSerialName(value = "doi", namespace = "http://arxiv.org/schemas/atom", prefix = "arxiv")
    @XmlElement(true)
    val doi: String? = null,
    @XmlSerialName(value = "comment", namespace = "http://arxiv.org/schemas/atom", prefix = "arxiv")
    @XmlElement(true)
    val comment: String,
    @XmlSerialName(value = "journal_ref", namespace = "http://arxiv.org/schemas/atom", prefix = "arxiv")
    @XmlElement(true)
    val journalRef: String? = null,
    @XmlElement(true)
    val primaryCategory: PrimaryCategory,
    @XmlSerialName(value = "link", namespace = "http://www.w3.org/2005/Atom", prefix = "")
    val links: List<ArticleLink>,
    @XmlElement(true)
    val category: Category
)

@Serializable
@XmlSerialName(value = "link", namespace = "http://www.w3.org/2005/Atom", prefix = "")
data class ArticleLink(
    @XmlElement(false)
    val title: String? = null,
    @XmlElement(false)
    val href: String,
    @XmlElement(false)
    val rel: String,
    @XmlElement(false)
    val type: String? = null
)

@Serializable
@XmlSerialName(value = "category", namespace = "http://www.w3.org/2005/Atom", prefix = "")
data class Category(
    @XmlElement(false)
    val term: String,
    @XmlElement(false)
    val scheme: String
)

@Serializable
@XmlSerialName(value = "primary_category", namespace = "http://arxiv.org/schemas/atom", prefix = "arxiv")
data class PrimaryCategory(
    @XmlElement(false)
    val term: String,
    @XmlElement(false)
    val scheme: String
)

@Serializable
@XmlSerialName(value = "author", namespace = "http://www.w3.org/2005/Atom", prefix = "")
data class Author(
    @XmlElement(true)
    val name: String
)

@Serializable
@XmlSerialName(value = "link", namespace = "http://www.w3.org/2005/Atom", prefix = "")
data class Link(
    @XmlElement(false)
    val href: String,
    @XmlElement(false)
    val rel: String,
    @XmlElement(false)
    val type: String
)

@Serializable
@XmlSerialName(value = "title", namespace = "http://www.w3.org/2005/Atom", prefix = "")
data class Title(
    @XmlValue(true)
    val title: String,
    @XmlElement(false)
    val type: String
)

internal fun ArticlesResponse.toArticles() = this.articles.map { it.toArticle() }

internal fun List<ArticleEntity>.toArticles() = this.map { it.toArticle() }

internal fun ArticleEntity.toArticle() = Article(
    id = id.toDomainId(),
    updated = updated.date,
    published = published.date,
    title = title,
    summary = summary.toSummaryDomain(),
    doi = doi,
    comment = comment,
    journalRef = journalRef,
    authors = authors.map { it.toDomain() },
    primaryCategory = primaryCategory.toDomain(),
    links = links.map { it.toDomain() },
    category = category.toDomain()
)

internal fun String.toSummaryDomain() = this.replace("\n", "")

internal fun String.toDomainId() = this.split("/").last()

internal fun ArticleLink.toDomain() = com.example.composeexample.domain.feature.article.entity.ArticleLink(
    title = title,
    href = href,
    rel = rel,
    type = type
)

internal fun Category.toDomain() = com.example.composeexample.domain.feature.article.entity.Category(
    term = term,
    scheme = scheme
)

internal fun PrimaryCategory.toDomain() = com.example.composeexample.domain.feature.article.entity.Category(
    term = term,
    scheme = scheme
)

internal fun Author.toDomain() = com.example.composeexample.domain.feature.article.entity.Author(
    name = name
)
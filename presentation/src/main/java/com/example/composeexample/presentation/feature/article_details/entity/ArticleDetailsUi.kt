package com.example.composeexample.presentation.feature.article_details.entity

import com.example.composeexample.domain.extensions.date.DateSerializer
import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.feature.article.entity.ArticleLink
import com.example.composeexample.domain.feature.article.entity.Author
import com.example.composeexample.domain.feature.article.entity.Category
import com.example.composeexample.presentation.extensions.formattedDate
import com.example.composeexample.presentation.extensions.str
import com.example.presentation.R
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ArticleDetailsUi(
    val id: String,
    val title: String,
    val authors: String,
    val summary: String,
    val comments: Comments,
    val submissionHistory: SubmissionHistory,
    val links: Links,
    val doi: DOI?,
    val subjects: Subjects
)

@Serializable
data class DOI(val title: String, val text: String)

@Serializable
data class Comments(val title: String, val text: String)

@Serializable
data class Subjects(val title: String, val text: String)

@Serializable
data class Links(val title: String, val links: List<com.example.composeexample.presentation.feature.article_details.entity.ArticleLink>)

@Serializable
data class SubmissionHistory(val title: String, val updated: String?, val published: String)

@Serializable
data class ArticleLink(val title: String?, val link: String)

fun Article.toDetailsUi() = ArticleDetailsUi(
    id = id,
    title = title,
    authors = authors.toUi(),
    summary = summary,
    comments = comment.toComments(),
    doi = doi?.toDOI(),
    submissionHistory = listOf(published, updated).toSubmissionHistory(),
    links = links.toLinks(),
    subjects = category.toSubjects()
)

private fun String.toComments() = Comments(title = R.string.case_label_comments.str, text = this)

private fun Category.toSubjects() = Subjects(title = R.string.case_label_subjects.str, text = this.toUi())

private fun String.toDOI() = DOI(title = R.string.case_label_doi.str, text = this)

private fun List<Date>.toSubmissionHistory() =
    SubmissionHistory(
        title = R.string.case_label_submission_history.str,
        published = this[0].toPublishedDate(),
        updated = this[1].takeIf { it != this[0] }
            ?.toUpdatedDate()
    )

private fun List<ArticleLink>.toLinks() =
    Links(
        title = R.string.case_label_links.str,
        links = this.map { it.toUi() }
    )

private fun ArticleLink.toUi() = ArticleLink(
    title = title,
    link = href
)

private fun Date.toPublishedDate() = R.string.screen_article_details_template_published_date.str(this.formattedDate)

private fun Date.toUpdatedDate() = R.string.screen_article_details_template_updated_date.str(this.formattedDate)

fun Category.toUi() =
    when(com.example.composeexample.domain.entity.Category.byName(term)){
        com.example.composeexample.domain.entity.Category.Astrophysics -> R.string.case_category_astrophysics.str
    }

private fun List<Author>.toUi() =
    when(this.size){
        1 -> R.string.case_template_authors_one.str(first().name)
        2 -> R.string.case_template_authors_many.str(*this.map { it.name }.toTypedArray())
        else -> R.string.case_template_authors_many.str(*this.map { it.name }.toTypedArray())
    }
package com.example.composeexample.domain.navigation

import com.example.composeexample.domain.navigation.NavigationDirections.ArticleDetails.Arguments.ARTICLE_ID

object NavigationDirections {

    object ArticlesList : NavigationCommand() {
        override val arguments = emptyList<Argument>()

        override val destination: Destination =
            destinationOf("articles_list")
    }

    object ArticleDetails : NavigationCommand() {

        object Arguments{
            const val ARTICLE_ID = "article_id"
        }

        override val arguments = listOf(
            argumentOf(ARTICLE_ID, isOptional = false, navType = NavType.String)
        )

        override val destination: Destination =
            destinationOf("article_details", arguments)
    }
}

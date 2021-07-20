/*
 * Copyright 2021 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.composeexample.presentation.navigation

import androidx.navigation.NavType
import com.example.composeexample.presentation.navigation.NavigationDirections.ArticleDetails.Arguments.ARTICLE_ID

object NavigationDirections {

    object ArticlesList : NavigationCommand() {
        override val arguments = emptyList<NavigationCommand.Argument>()

        override val destination: NavigationCommand.Destination =
            destinationOf("articles_list")
    }

    object ArticleDetails : NavigationCommand() {

        object Arguments{
            const val ARTICLE_ID = "article_id"
        }

        override val arguments = listOf(
            argumentOf(ARTICLE_ID, isOptional = false, navType = NavType.StringType)
        )

        override val destination: NavigationCommand.Destination =
            destinationOf("article_details", arguments)
    }
}

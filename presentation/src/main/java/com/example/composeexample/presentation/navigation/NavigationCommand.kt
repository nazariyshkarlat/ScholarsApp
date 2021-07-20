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
import androidx.navigation.compose.navArgument

abstract class NavigationCommand {

    abstract val arguments: List<Argument>
    abstract val destination: Destination

    fun create(arguments: Map<String, Any> = emptyMap()) = CommandResult(route, arguments)

    val route
    get() = destination.toString()

    val navArguments
    get() = arguments.map {
        navArgument(name = it.name){
            type = it.type
        }
    }

    companion object {
        val Default = object : NavigationCommand() {
            override val arguments = emptyList<Argument>()
            override val destination = Destination(name = "")
        }

        fun argumentOf(name: String, isOptional: Boolean, navType: NavType<*>) = Argument(name, isOptional, navType)

        fun destinationOf(name: String, arguments: List<Argument> = emptyList()) = Destination(name, arguments)
    }


    data class Argument(val name: String, val isOptional: Boolean, val type: NavType<*>)

    class Destination(private val name: String, private val arguments: List<Argument> = emptyList()){
        override fun toString() = buildString {
            append(name)
            arguments
                .filter { !it.isOptional }
                .takeIf { it.isNotEmpty() }
                ?.joinToString(prefix = "", postfix = "") {
                    formRequiredArgument(it.name)
                }?.let{
                    append(it)
                }
            arguments
                .filter { it.isOptional }
                .takeIf { it.isNotEmpty() }
                ?.joinToString(prefix = "", postfix = "") {
                    formOptionalArgument(it.name)
                }?.let {
                    append(it)
                }
        }

        private fun formRequiredArgument(name: String) = "/{${name}}"
        private fun formOptionalArgument(name: String) = "?${name}={${name}}"
    }

    class CommandResult(
        val pathTemplate: String,
        val arguments: Map<String, Any> = emptyMap()
    ){
        override fun toString() : String {
            var pathTemplate = pathTemplate

            arguments.forEach {
                pathTemplate = pathTemplate.replace("{${it.key}}", it.value.toString())
            }
            return pathTemplate
        }
    }
}

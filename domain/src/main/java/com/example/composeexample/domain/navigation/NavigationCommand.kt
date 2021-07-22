package com.example.composeexample.domain.navigation

abstract class NavigationCommand {

    abstract val arguments: List<Argument>
    abstract val destination: Destination

    fun create(arguments: Map<String, Any> = emptyMap()) = CommandResult(route, arguments)

    val route
    get() = destination.toString()

    companion object {
        val Default = object : NavigationCommand() {
            override val arguments = emptyList<Argument>()
            override val destination = Destination(name = "")
        }

        fun argumentOf(name: String, isOptional: Boolean, navType: NavType) = Argument(name, isOptional, navType)

        fun destinationOf(name: String, arguments: List<Argument> = emptyList()) = Destination(name, arguments)
    }


    data class Argument(val name: String, val isOptional: Boolean, val type: NavType)

    class Destination(private val name: String, private val arguments: List<Argument> = emptyList()){
        override fun toString() = buildString {
            append(name)
            append(formRequiredRoutePart())
            append(formOptionalRoutePart())
        }

        private fun formRequiredArgument(name: String) = "/{${name}}"
        private fun formOptionalArgument(name: String) = "?${name}={${name}}"

        private fun formOptionalRoutePart() =
            arguments
                .filter { it.isOptional }
                .takeIf { it.isNotEmpty() }
                ?.joinToString(prefix = "", postfix = "") {
                    formOptionalArgument(it.name)
                } ?: ""

        private fun formRequiredRoutePart() =
            arguments
                .filter { !it.isOptional }
                .takeIf { it.isNotEmpty() }
                ?.joinToString(prefix = "", postfix = "") {
                    formRequiredArgument(it.name)
                } ?: ""
    }

    class CommandResult(
        val pathTemplate: String,
        val arguments: Map<String, Any> = emptyMap()
    ){
        override fun toString() =
            arguments.toList().fold(initial = pathTemplate) { acc, pair->
                acc.replace("{${pair.first}}", pair.second.toString())
            }
    }

    enum class NavType{
        Int,
        IntArray,
        Long,
        LongArray,
        Float,
        FloatArray,
        Bool,
        BoolArray,
        String,
        StringArray
    }
}

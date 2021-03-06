package com.example.composeexample.domain.entity

enum class Category(val category: String) {
    Astrophysics("astro-ph");


    companion object {
        fun byName(name: String) = Category.values().first { it.category == name }
    }
}
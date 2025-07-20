package com.example.vocabularyapp.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddEdit : Screen("add_edit")
    object Favorites : Screen("favorites")
    object Detail : Screen("detail/{cardId}") {
        fun createRoute(cardId: Int) = "detail/$cardId"
    }
} 
package com.example.myqurancompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{number}") {
        fun createRoute(number: String) = "home/$number"
    }
}
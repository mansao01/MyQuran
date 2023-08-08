package com.example.myqurancompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{number}/{surah}/{asma}") {
        fun createRoute(number: String, surah: String, asma: String) = "home/$number/$surah/$asma"
    }
}
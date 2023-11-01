package com.example.myqurancompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Setting:Screen("setting")
    object Detail : Screen("detail")
}
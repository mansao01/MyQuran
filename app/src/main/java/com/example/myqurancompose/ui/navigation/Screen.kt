package com.example.myqurancompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Setting:Screen("setting")
    object Detail : Screen("home/{number}/{surah}/{asma}/{arti}") {
        fun createRoute(
            number: String,
            surah: String,
            asma: String,
            arti: String,
//            keterangan: String
        ) = "home/$number/$surah/$asma/$arti"
    }
}
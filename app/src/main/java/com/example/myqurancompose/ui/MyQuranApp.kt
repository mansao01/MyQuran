package com.example.myqurancompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myqurancompose.ui.navigation.Screen
import com.example.myqurancompose.ui.screen.detail.DetailScreen
import com.example.myqurancompose.ui.screen.detail.DetailViewModel
import com.example.myqurancompose.ui.screen.home.HomeScreen
import com.example.myqurancompose.ui.screen.home.HomeViewModel

@Composable
fun MyQuranApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            HomeScreen(
                uiState = homeViewModel.uiState,
                navigateToDetail = { number, surah, asma ->
                    navController.navigate(Screen.Detail.createRoute(number, surah, asma))
                })
        }

        composable(
            Screen.Detail.route, arguments =
            listOf(navArgument("number") {
                type = NavType.StringType
            }, navArgument("surah") {
                type = NavType.StringType
            }, navArgument("asma") {
                type = NavType.StringType
            })
        ) { data ->
            val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
            val number = data.arguments?.getString("number") ?: ""
            val surah = data.arguments?.getString("surah") ?: ""
            val asma = data.arguments?.getString("asma") ?: ""

            DetailScreen(
                number = number,
                surah = surah,
                asma = asma,
                uiState = detailViewModel.uiState
            )
        }
    }
}

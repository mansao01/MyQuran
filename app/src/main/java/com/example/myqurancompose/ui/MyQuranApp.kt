package com.example.myqurancompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.myqurancompose.ui.screen.setting.SettingScreen
import com.example.myqurancompose.ui.screen.setting.SettingViewModel

@ExperimentalMaterial3Api
@Composable
fun MyQuranApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onDarkModeChange: (Boolean) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier.fillMaxSize()
    ) {

        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            HomeScreen(
                uiState = homeViewModel.uiState,
                navigateToSetting = {
                    navController.navigate(Screen.Setting.route)
                },
                scrollBehavior = scrollBehavior,
                navigateToDetail = { number, surah, asma, arti ->
                    navController.navigate(
                        Screen.Detail.createRoute(
                            number,
                            surah,
                            asma,
                            arti,
                        )
                    )
                }
            )
        }

        composable(Screen.Setting.route) {
            val settingViewModel: SettingViewModel = viewModel(factory = SettingViewModel.Factory)
            SettingScreen(
                settingViewModel = settingViewModel,
                onDarkModeChange = onDarkModeChange,
                scrollBehavior = scrollBehavior,
                navigateToHome = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }

        composable(
            Screen.Detail.route, arguments =
            listOf(
                navArgument("number") {
                    type = NavType.StringType
                },
                navArgument("surah") {
                    type = NavType.StringType
                },
                navArgument("asma") {
                    type = NavType.StringType
                },
                navArgument("arti") {
                    type = NavType.StringType
                },

            )
        ) { data ->
            val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
            val number = data.arguments?.getString("number") ?: ""
            val surah = data.arguments?.getString("surah") ?: ""
            val asma = data.arguments?.getString("asma") ?: ""
            val arti = data.arguments?.getString("arti") ?: ""

            DetailScreen(
                number = number,
                surah = surah,
                asma = asma,
                arti = arti,
                scrollBehavior = scrollBehavior,
                uiState = detailViewModel.uiState,
                navigateToHome = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
    }
}

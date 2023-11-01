package com.example.myqurancompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val sharedViewModel:SharedViewModel = viewModel()
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
                navigateToDetail = {
                    navController.navigate(Screen.Detail.route)
                },
                sharedViewModel = sharedViewModel
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
            Screen.Detail.route
        ) {
            val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)


            DetailScreen(
                scrollBehavior = scrollBehavior,
                uiState = detailViewModel.uiState,
                navigateToHome = {
                    navController.navigate(Screen.Home.route)
                },
                sharedViewModel = sharedViewModel
            )
        }
    }
}

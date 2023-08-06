package com.example.myqurancompose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myqurancompose.ui.screen.home.HomeScreen
import com.example.myqurancompose.ui.screen.home.HomeViewModel

@Composable
fun MyQuranApp() {

    val homeViewModel:HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    HomeScreen(uiState = homeViewModel.uiState)
}
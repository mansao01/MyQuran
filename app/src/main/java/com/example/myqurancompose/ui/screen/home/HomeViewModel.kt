package com.example.myqurancompose.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myqurancompose.MyQuranApplication
import com.example.myqurancompose.data.MyQuranRepository
import com.example.myqurancompose.ui.common.HomeUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(private val myQuranRepository: MyQuranRepository): ViewModel(){

    var uiState:HomeUiState by mutableStateOf(HomeUiState.Loading)

    init {
        getSurahList()
    }

     fun getSurahList(){
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            uiState = try {
                val result = myQuranRepository.getSurahList()
                HomeUiState.Success(result)
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyQuranApplication)
                val myQuranRepository = application.container.myQuranRepository
                HomeViewModel(myQuranRepository = myQuranRepository)
            }
        }
    }
}
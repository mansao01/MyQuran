package com.example.myqurancompose.ui.screen.detail

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
import com.example.myqurancompose.ui.common.DetailUiState
import com.example.myqurancompose.ui.common.HomeUiState
import com.example.myqurancompose.ui.screen.home.HomeViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailViewModel(private val myQuranRepository: MyQuranRepository):ViewModel() {

    var uiState:DetailUiState by mutableStateOf(DetailUiState.Loading)


    fun getSurahVerseList(number: String){
        viewModelScope.launch {
            uiState = DetailUiState.Loading
            uiState = try {
                val result = myQuranRepository.getSurahVerseList(number)
                DetailUiState.Success(result)
            }catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyQuranApplication)
                val myQuranRepository = application.container.myQuranRepository
                DetailViewModel(myQuranRepository = myQuranRepository)
            }
        }
    }
}

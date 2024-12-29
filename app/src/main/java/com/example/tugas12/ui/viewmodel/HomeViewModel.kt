package com.example.tugas12.ui.viewmodel

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugas12.model.Mahasiswa
import com.example.tugas12.repository.MahasiswaRepository
import java.io.IOException


sealed class HomeUiState{
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}
class HomeViewModel(private val mhs: MahasiswaRepository) : ViewModel(){
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set
    init {
        getMhs()
    }
}
fun getMhs(){
    viewModelScope.launch {
        mhsUiState = try {
            HomeUiState.Success(mhs.getMahasiswa())
        }catch (e: IOException){
            HomeUiState.Error
        }catch (e: HttpException){
            HomeUiState.Error
        }
    }
}
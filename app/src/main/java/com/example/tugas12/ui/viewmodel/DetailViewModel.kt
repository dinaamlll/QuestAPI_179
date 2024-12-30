package com.example.tugas12.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas12.model.Mahasiswa
import com.example.tugas12.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class DetailUiState {
    object Loading : DetailUiState()
    object Error : DetailUiState()
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
}

class DetailViewModel(private val mhs: MahasiswaRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    fun getMahasiswabyNim(nim: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            try {
                val mahasiswa = mhs.getMahasiswabyNim(nim)
                if (mahasiswa != null) {
                    _detailUiState.value = DetailUiState.Success(mahasiswa)
                } else {
                    _detailUiState.value = DetailUiState.Error
                }
            } catch (e: IOException) {
                e.printStackTrace()
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                e.printStackTrace()
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}

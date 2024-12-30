package com.example.tugas12.ui.viewmodel




import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tugas12.MahasiswaApplications
import com.example.tugas12.ui.viewmodel.DetailViewModel
import com.example.tugas12.ui.viewmodel.HomeViewModel
import com.example.tugas12.ui.viewmodel.InsertViewModel
import com.example.tugas12.ui.viewmodel.UpdateViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(AplikasiMahasiswa().container.mahasiswaRepository) }
        initializer { InsertViewModel(AplikasiMahasiswa().container.mahasiswaRepository) }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                AplikasiMahasiswa().container.mahasiswaRepository
            )
        }
        initializer {
            UpdateViewModel(
                createSavedStateHandle(),
                AplikasiMahasiswa().container.mahasiswaRepository
            )
        }
    }

    fun CreationExtras.AplikasiMahasiswa(): MahasiswaApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as MahasiswaApplications)
}
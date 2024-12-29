package com.example.tugas12.ui.viewmodel

import com.example.tugas12.model.Mahasiswa

fun Mahasiswa.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan =angkatan
)
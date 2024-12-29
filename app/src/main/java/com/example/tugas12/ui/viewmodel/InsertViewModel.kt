package com.example.tugas12.ui.viewmodel

import com.example.tugas12.model.Mahasiswa

data class InsertUiEvent(
    val nim:String = "",
    val nama:String = "",
    val alamat:String = "",
    val jenisKelamin:String = "",
    val kelas:String = "",
    val angkatan:String = "",
)
fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)
fun Mahasiswa.toInsertUiEvent():InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan =angkatan
)
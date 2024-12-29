package com.example.tugas12.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,

    @SerialName("Jenis_Kelamin")
    val jenisKelamin: String,

    val kelas: String,
    val angkatan: String
)

package com.example.tugas12.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas12.model.Mahasiswa
import com.example.tugas12.ui.customwidget.CostumeTopAppBar
import com.example.tugas12.ui.navigation.DestinasiNavigasi
import com.example.tugas12.ui.viewmodel.DetailUiState
import com.example.tugas12.ui.viewmodel.DetailViewModel
import com.example.tugas12.ui.viewmodel.PenyediaViewModel


object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mhs"
    const val NIM = "nim"
    val routeWithArg = "$route/{$NIM}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    nim: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit = { },
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
){
    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
},
floatingActionButton = {
    FloatingActionButton(
        onClick = { onEditClick(nim) },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Mahasiswa")
    }
},
    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetailMhs(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            retryAction = { viewModel.getDetailMahasiswa() }
        )
    }
}
@Composable
fun BodyDetailMhs(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    retryAction: () -> Unit = {}
) {
    when (detailUiState) {
        is DetailUiState.Loading -> {
            // Menampilkan gambar loading saat data sedang dimuat
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailUiState.Success -> {
            // Menampilkan detail mahasiswa jika berhasil
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMhs(mahasiswa = detailUiState.mahasiswa)
            }
        }
        is DetailUiState.Error -> {
            // Menampilkan error jika data gagal dimuat
            OnError(
                retryAction = retryAction,
                modifier = modifier.fillMaxSize()
            )
        }
        else -> {
            // Menangani kasus yang tidak terduga (optional, jika Anda ingin menangani hal ini)
            // Anda bisa menambahkan logika untuk menangani kesalahan yang tidak diketahui
            Text("Unexpected state encountered")
        }
    }

}
    @Composable
    fun ItemDetailMhs(
        modifier: Modifier = Modifier,
        mahasiswa: Mahasiswa
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
                Spacer(modifier = Modifier.padding(8.dp))

                ComponentDetailMhs(judul = "Nama", isinya = mahasiswa.nama)
                Spacer(modifier = Modifier.padding(4.dp))

                ComponentDetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
                Spacer(modifier = Modifier.padding(4.dp))

                ComponentDetailMhs(judul = "Jenis Kelamin", isinya = mahasiswa.jenisKelamin)
                Spacer(modifier = Modifier.padding(4.dp))

                ComponentDetailMhs(judul = "Kelas", isinya = mahasiswa.kelas)
                Spacer(modifier = Modifier.padding(4.dp))

                ComponentDetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
            }
        }
    }

    @Composable
    fun ComponentDetailMhs(
        modifier: Modifier = Modifier,
        judul: String,
        isinya: String,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$judul :",
                style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            )
            Text(
                text = isinya,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp
                ))
        }
    }


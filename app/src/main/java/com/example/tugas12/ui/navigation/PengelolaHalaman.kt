package com.example.tugas12.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugas12.ui.view.DestinasiDetail
import com.example.tugas12.ui.view.DestinasiEntry
import com.example.tugas12.ui.view.DestinasiHome
import com.example.tugas12.ui.view.DestinasiUpdate
import com.example.tugas12.ui.view.DetailView
import com.example.tugas12.ui.view.EntryMhsScreen
import com.example.tugas12.ui.view.HomeScreen
import com.example.tugas12.ui.view.UpdateView


@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = { nim ->
                    // Navigasi ke destinasi Detail dengan menyertakan nim
                    navController.navigate("${DestinasiDetail.route}/$nim") {
                        // Menggunakan popUpTo untuk memastikan navigasi ke Detail dan menghapus stack sebelumnya jika perlu
                        popUpTo(DestinasiHome.route) {
                            inclusive = true  // Termasuk juga destinasi yang akan dipopUp
                        }
                    }
                    println("PengelolaHalaman: nim = $nim")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetail.routesWithArg) { backStackEntry ->
            // Mendapatkan NIM dari argument route
            val nim = backStackEntry.arguments?.getString(DestinasiDetail.NIM)

            nim?.let {
                DetailView(
                    nim = it, // Mengirimkan NIM ke DetailMhsView
                    navigateBack = {
                        // Aksi ketika tombol "Kembali" ditekan
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true // Pop sampai ke DestinasiHome
                            }
                        }
                    },
                    onEditClick = {
                        // Navigasi ke halaman update dengan NIM sebagai argumen
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    }
                )
            }
        }


        composable(
            DestinasiUpdate.routesWithArg, // Correct route with argument
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            // Retrieve the 'nim' argument from the navBackStackEntry
            val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM)

            nim?.let {
                // Pass 'nim' to the UpdateView composable
                UpdateView(
                    navigateBack = {
                        navController.popBackStack()
                    },

                    modifier = modifier,

                    )
            }
        }


    }
}
package com.example.praktikum7

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pam_pertemuan5.FormulirView
import com.example.praktikum7.model.JenisKelamin.JenisKelamin
import com.example.praktikum7.ui.view.TampilData
import com.example.praktikum7.ui.view.TampilDataView
import com.example.praktikum7.viewmodel.SiswaViewModel

enum class Halaman {
    FORMULIR,
    TAMPILDATA
}

@Composable
fun Navigasi(
    viewModel: SiswaViewModel = viewModel(),
    navHost: NavHostController = rememberNavController()) {
    val uiState by viewModel.statusUI.collectAsState()
    NavHost(
        navController = navHost,
        startDestination = Halaman.FORMULIR.name) {
        composable(
            route = Halaman.FORMULIR.name) {
            val konteks = LocalContext.current
            FormulirView(
                ListJK = JenisKelamin.map { id ->
                    konteks.getString(id) },
                onSubmitClicked = {
                    viewModel.setDataSiswa(it)
                    navHost.navigate(Halaman.TAMPILDATA.name)
                }
            )
        }
        composable(route = Halaman.TAMPILDATA.name) {
            TampilDataView(
                uiState = uiState,
                onBackButton = {
                    navHost.popBackStack()
                }
            )
        }
    }
}
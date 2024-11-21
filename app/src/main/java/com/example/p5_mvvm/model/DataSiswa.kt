package com.example.praktikum7.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class DataSiswa(
    val nama: String="",
    val alamat: String="",
    val gender: String="",
    val nim: String="",
    val notelp: String="",
    val email: String="",
)
package frinsa.hpp.data.tahap

import android.content.Context

class colorSorter: Standard {

    constructor(
        tanggal: String,
        berat: Double,
        biaya: Int
    ) {
        this.tanggal = tanggal
        this.berat = berat
        this.biaya = biaya
    }

    constructor(){}
}
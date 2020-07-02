package frinsa.hpp.data.tahap

import android.content.Context

class colorSorter: Standard {

    constructor(
        id2: Int,
        tanggal : String,
        berat : Double,
        biaya : Int) {
        this.id2 = id2
        this.tanggal = tanggal
        this.berat = berat
        this.biaya = biaya
    }

    constructor(){}
}
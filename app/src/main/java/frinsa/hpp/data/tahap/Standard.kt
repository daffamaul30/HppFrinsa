package frinsa.hpp.data.tahap

import android.content.Context

open class Standard {
    var id : Int = 0
    var id2 : Int = 0
    var tanggal : String = ""
    var berat : Double = 0.0
    var biaya : Int = 0

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
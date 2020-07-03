package frinsa.hpp.data.tahap

import android.content.Context
import javax.xml.transform.dom.DOMLocator

open class jemurKadarAir {
    var id : Int = 0
    var id2 : Int = 0
    var tanggal : String = ""
    var berat : Double = 0.0
    var kadarAir: Double = 0.0
    var biaya : Int = 0

    constructor(
        id2: Int,
        tanggal : String,
        berat : Double,
        kadarAir : Double,
        biaya : Int) {
        this.id2 = id2
        this.tanggal = tanggal
        this.berat = berat
        this.kadarAir = kadarAir
        this.biaya = biaya
    }

    constructor(){}
}
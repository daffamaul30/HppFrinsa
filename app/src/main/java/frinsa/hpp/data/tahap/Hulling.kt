package frinsa.hpp.data.tahap

import android.content.Context
import javax.xml.transform.dom.DOMLocator

class Hulling: jemurKadarAir {

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
package frinsa.hpp.data.tahap

import android.content.Context
import javax.xml.transform.dom.DOMLocator

class Hulling: jemurKadarAir {

    constructor(
        tanggal : String,
        berat : Double,
        kadarAir : Double,
        biaya : Int) {
        this.tanggal = tanggal
        this.berat = berat
        this.kadarAir = kadarAir
        this.biaya = biaya
    }

    constructor(){}
}
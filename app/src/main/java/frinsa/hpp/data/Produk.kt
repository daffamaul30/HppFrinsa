package frinsa.hpp.data

import android.content.Context
import android.widget.Toast
import frinsa.hpp.data.tahap.Petik
import frinsa.hpp.data.tahap.Standard

class Produk {
    lateinit var petik : Petik
    lateinit var produksi : Produksi

    lateinit var context: Context
    private lateinit var db: DBPanen

    constructor(context: Context){
        this.context = context
        db = DBPanen(this.context)
    }

    constructor(produksi: Produksi, petik: Petik) {
        this.produksi = produksi
        this.petik = petik
    }

    constructor(){}
}
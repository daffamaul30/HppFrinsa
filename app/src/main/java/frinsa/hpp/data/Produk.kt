package frinsa.hpp.data

import android.content.Context
import android.widget.Toast

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

    fun insertPanen(produksi: Produksi, petik: Petik) {
        val (resultPR, resultPE) = db.insertPanen(produksi, petik)
        if ((resultPR == -1.toLong()) || (resultPE == -1.toLong())) {
            println(resultPR)
            println(resultPE)
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun readPanen(): MutableList<Produk> {
        val panen = db.readPanen()
        return panen
    }

    fun toastMessage(text: String) {
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
    }
}
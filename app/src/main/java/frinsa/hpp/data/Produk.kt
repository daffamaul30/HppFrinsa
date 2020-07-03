package frinsa.hpp.data

import android.content.Context
import android.widget.Toast

import frinsa.hpp.data.tahap.*
import frinsa.hpp.tahapan_proses.Pulping1_Fragment
import frinsa.hpp.tahapan_proses.Pulping2_Fragment

import frinsa.hpp.data.tahap.Petik
import frinsa.hpp.data.tahap.Standard


class Produk {
    lateinit var petik : Petik
    lateinit var produksi : Produksi
    lateinit var fermentasi : Fermentasi
    lateinit var transport: Transportasi
    lateinit var pulping1: pulpingSatu
    lateinit var pulping2 : pulpingDua

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


    constructor(fermentasi: Fermentasi){
        this.fermentasi = fermentasi
    }

    fun insertPanen(produksi: Produksi, petik: Petik) {
//        val (resultPR, resultPE) = db.insertPanen(produksi, petik)
//        if ((resultPR == -1.toLong()) || (resultPE == -1.toLong())) {
//            println(resultPR)
//            println(resultPE)
//            toastMessage("Gagal")
//        }else {
//            toastMessage("Berhasil")
//        }

        //TEST GET DATA
        val panen = readPanen()
        println(panen.size)
        if (panen.size > 0) {
            for (i in 0..(panen.size-1)) {
                print(panen.get(i).produksi.id_produksi)
                println(panen.get(i).petik.id_petik_produksi)
                if (panen.get(i).produksi.id_produksi == panen.get(i).petik.id_petik_produksi) {
                    kotlin.io.println("MASOK")
                    println("""
                                        DATA KE-${i+1}
                                        PRODUKSI
                                        id Produksi = ${panen.get(i).produksi.id_produksi}
                                        sumber = ${panen.get(i).produksi.sumber}
                                        beli dari = ${panen.get(i).produksi.beli_dari}
                                        bentuk = ${panen.get(i).produksi.bentuk}
                                        varietas = ${panen.get(i).produksi.varietas}
                                        blok = ${panen.get(i).produksi.blok}
                                        proses = ${panen.get(i).produksi.proses}
                                        status = ${panen.get(i).produksi.status}
                                        \n
                                        PETIK
                                        id Petik = ${panen.get(i).petik.id_petik}
                                        id Produksi = ${panen.get(i).petik.id_petik_produksi}
                                        tanggal Petik = ${panen.get(i).petik.tgl_petik}
                                        berat = ${panen.get(i).petik.berat}
                                        biaya petik = ${panen.get(i).petik.biaya_petik}
                                        biaya ojek = ${panen.get(i).petik.biaya_ojek}
                                        biaya cuci = ${panen.get(i).petik.biaya_cuci}
                                    """.trimIndent())
                }
            }
        }
    }

//    fun insertFermentasi(fer : Fermentasi){
//        val resultfer = db.insertFermentasi(fer)
//        if (resultfer == -1.toLong()) {
//            println(resultfer)
//            toastMessage("Gagal")
//        }else {
//            toastMessage("Berhasil")
//        }
//    }
//    fun insertTransportasi(transport : Transportasi){
//        val resulttrans = db.insertTransportasi(transport)
//        if (resulttrans == -1.toLong()) {
//            println(resulttrans)
//            toastMessage("Gagal")
//        }else {
//            toastMessage("Berhasil")
//        }
//    }
//    fun insertPulping1(pulping1 : pulpingSatu){
//        val resultPulping1 = db.insertPulping1(pulping1)
//        if (resultPulping1 == -1.toLong()) {
//            println(resultPulping1)
//            toastMessage("Gagal")
//        }else {
//            toastMessage("Berhasil")
//        }
//    }
//    fun <T: Standard> insertStandard(data: T, TABLE_NAME: String) {
//        val result = db.insertStandard<T>(data, TABLE_NAME)
//        if (result == -1.toLong()) {
//            println(result)
//            toastMessage("Gagal")
//        }else {
//            toastMessage("Berhasil")
//        }
//    }
    fun readPanen(): MutableList<Produk> {
        val panen = db.readPanen()
        return panen
    }

    fun toastMessage(text: String) {
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
    }

    constructor(){}

}
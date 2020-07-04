package frinsa.hpp.data

import android.content.Context
import android.widget.Toast

import frinsa.hpp.data.tahap.*
import frinsa.hpp.tahapan_proses.Pulping1_Fragment
import frinsa.hpp.tahapan_proses.Pulping2_Fragment

import frinsa.hpp.data.tahap.Petik
import frinsa.hpp.data.tahap.Standard


class Produk {
    var petik: Petik? = null
    var produksi: Produksi? = null
    var fermentasi: Fermentasi? = null
    var transport: Transportasi? = null
    var pulping1: pulpingSatu? = null
    var pulping2: pulpingDua? = null
    var jemurKadarAir: jemurKadarAir? = null
    var jemur1: jemurSatu? = null
    var jemur2: jemurDua? = null
    var hulling: Hulling? = null
    var sutonGrader: sutonGrader? = null
    var sizeGrading: sizeGrading? = null
    var colorSorter: colorSorter? = null
    var handPick: handPick? = null

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

    constructor(
        produksi: Produksi,
        petik: Petik,
        fermentasi: Fermentasi,
        transportasi: Transportasi,
        pulping1: pulpingSatu,
        pulping2: pulpingDua,
        jemurKadarAir: jemurKadarAir,
        jemur1: jemurSatu,
        jemur2: jemurDua,
        hulling: Hulling,
        sutonGrader: sutonGrader,
        sizeGrading: sizeGrading,
        colorSorter: colorSorter,
        handPick: handPick) {
        this.produksi = produksi
        this.petik = petik
        this.fermentasi = fermentasi
        this.transport = transportasi
        this.pulping1 = pulping1
        this.pulping2 = pulping2
        this.jemurKadarAir = jemurKadarAir
        this.jemur1 = jemur1
        this.jemur2 = jemur2
        this.hulling = hulling
        this.sutonGrader = sutonGrader
        this.sizeGrading = sizeGrading
        this.colorSorter = colorSorter
        this.handPick = handPick
    }

    constructor(fermentasi: Fermentasi){
        this.fermentasi = fermentasi
    }

    constructor(){}

    fun getTotalBiaya(data: Produk): Int {
        val totalPetik = intArrayOf(data.petik?.biaya_petik ?: 0,data.petik?.biaya_ojek ?: 0,data.petik?.biaya_cuci ?: 0).sum()
        val totalFermentasi = intArrayOf(data.fermentasi?.biaya_fermentasi ?: 0, data.fermentasi?.biaya_muat ?: 0).sum()
        val totalTransportasi = intArrayOf(data.transport?.biaya_transport ?: 0, data.transport?.biaya_kawal ?: 0, data.transport?.biaya_bongkar ?: 0).sum()
        val totalPulping1 = intArrayOf(data.pulping1?.biaya_pulping ?: 0, data.pulping1?.biaya_fermentasi ?: 0, data.pulping1?.biaya_cuci ?: 0, data.pulping1?.biaya_jemur ?: 0, data.pulping1?.biaya_muat ?: 0).sum()
        val totalPulping2 = data.pulping2?.biaya ?: 0
        val totaljemurKadarAir = data.jemurKadarAir?.biaya ?: 0
        val totaljemur1 = data.jemur1?.biaya ?: 0
        val totaljemur2 = data.jemur2?.biaya ?: 0
        val totalHulling = data.hulling?.biaya ?: 0
        val totalSutonGrader = data.sutonGrader?.biaya ?: 0
        val totalSizeGrading = data.sizeGrading?.biaya ?: 0
        val totalColorSorter = data.colorSorter?.biaya ?: 0
        val totalHandPick = data.handPick?.biaya ?: 0

        val totalBiaya = intArrayOf(
            totalPetik,
            totalFermentasi,
            totalTransportasi,
            totalPulping1,
            totalPulping2,
            totaljemurKadarAir,
            totaljemur1,
            totaljemur2,
            totalHulling,
            totalSutonGrader,
            totalSizeGrading,
            totalSizeGrading,
            totalColorSorter,
            totalHandPick).sum()

        return totalBiaya
    }
}
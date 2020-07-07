package frinsa.hpp.data

import android.content.Context
import frinsa.hpp.data.tahap.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*


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

    fun insertFirst(produksi: Produksi, petik: Petik) {
        db.insertPanen(produksi, petik)
        db.insertFermentasi(Fermentasi())
        db.insertTransportasi(Transportasi())
        db.insertPulping1(pulpingSatu())
        db.insertStandard<pulpingDua>(
            pulpingDua(),
            TABLE_PULPING2,
            COL_ID_PRODUKSI_PULPING2,
            COL_TGL_PULPING2,
            COL_BERAT_PULPING2,
            COL_BIAYA_PULPING2)
        db.insertKadarAir(
            jemurKadarAir(),
            TABLE_JEMUR_KADAR_AIR,
            COL_ID_PRODUKSI_KADAR_AIR,
            COL_TGL_KADAR_AIR,
            COL_BERAT_KADAR_AIR,
            COL_KDR_AIR_KADAR_AIR,
            COL_BIAYA_JEMUR_KADAR_AIR)
        db.insertStandard<jemurSatu>(
            jemurSatu(),
            TABLE_JEMUR1,
            COL_ID_PRODUKSI_JEMUR1,
            COL_TGL_JEMUR1,
            COL_BERAT_JEMUR1,
            COL_BIAYA_JEMUR1)
        db.insertStandard<jemurDua>(
            jemurDua(),
            TABLE_JEMUR2,
            COL_ID_PRODUKSI_JEMUR2,
            COL_TGL_JEMUR2,
            COL_BERAT_JEMUR2,
            COL_BIAYA_JEMUR2)
        db.insertKadarAir<Hulling>(
            Hulling(),
            TABLE_HULLING,
            COL_ID_PRODUKSI_HULLING,
            COL_TGL_HULLING,
            COL_BERAT_HULLING,
            COL_KDR_AIR_HULLING,
            COL_BIAYA_HULLING)
        db.insertStandard<sutonGrader>(
            sutonGrader(),
            TABLE_SUTON_GRADER,
            COL_ID_PRODUKSI_SUTON_GRADER,
            COL_TGL_SUTON_GRADER,
            COL_BERAT_SUTON_GRADER,
            COL_BIAYA_SUTON_GRADER)
        db.insertStandard<sizeGrading>(
            sizeGrading(),
            TABLE_SIZE_GRADING,
            COL_ID_PRODUKSI_SIZE_GRADING,
            COL_TGL_SIZE_GRADING,
            COL_BERAT_SIZE_GRADING,
            COL_BIAYA_SIZE_GRADING)
        db.insertStandard<colorSorter>(
            colorSorter(),
            TABLE_COLOR_SORTER,
            COL_ID_PRODUKSI_COLOR_SORTER,
            COL_TGL_COLOR_SORTER,
            COL_BERAT_COLOR_SORTER,
            COL_BIAYA_COLOR_SORTER)
        db.insertStandard<handPick>(
            handPick(),
            TABLE_HAND_PICK,
            COL_ID_PRODUKSI_HAND_PICK,
            COL_TGL_HAND_PICK,
            COL_BERAT_HAND_PICK,
            COL_BIAYA_HAND_PICK)
    }

    fun getLastWeight(data: Produk): Double {
        var berat: Double = 0.0
        when(data.produksi!!.status) {
            "petik" -> {
                berat = data.petik!!.berat
            }
            "fermentasi" -> {
                berat = data.fermentasi!!.berat
            }
            "transportasi" -> {
                berat = data.transport!!.berat
            }
            "pulping Ceri-Gabah Basah" -> {
                berat = data.pulping1!!.berat
            }
            "pulping" -> {
                berat = data.pulping2!!.berat
            }
            "jemur Kadar Air" -> {
                berat = data.jemurKadarAir!!.berat
            }
            "jemurI" -> {
                berat = data.jemur1!!.berat
            }
            "jemurII" -> {
                berat = data.jemur2!!.berat
            }
            "hulling" -> {
                berat = data.hulling!!.berat
            }
            "suton grader" -> {
                berat = data.sutonGrader!!.berat
            }
            "size grading" -> {
                berat = data.sizeGrading!!.berat
            }
            "color sorter" -> {
                berat = data.colorSorter!!.berat
            }
            "Selesai" -> {
                berat = data.handPick!!.berat
            }
        }
        return berat
    }

    fun formatRupiah(biaya: Double): String {
        val dec: DecimalFormat = DecimalFormat()
        DecimalFormat.getCurrencyInstance()
        val formatRP: DecimalFormatSymbols = DecimalFormatSymbols()
        formatRP.monetaryDecimalSeparator = ','
        formatRP.groupingSeparator = '.'
        dec.decimalFormatSymbols = formatRP

//        val localeID = Locale("in", "ID")
//        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
//        return formatRupiah.format(biaya)

//        Toast.makeText(context, dec.format(biaya), Toast.LENGTH_SHORT).show()
        return "Rp. " + dec.format(biaya)
    }

    fun deleteProduksiById(B: Int) {
        db.deleteConditional(TABLE_PRODUKSI, COL_ID_PRODUKSI, B)
        db.deleteConditional(TABLE_PETIK, COL_ID_PRODUKSI_PETIK, B)
        db.deleteConditional(TABLE_FERMEN, COL_ID_PRODUKSI_FERMENTASI, B)
        db.deleteConditional(TABLE_TRANSPORTASI, COL_ID_PRODUKSI_TRANSPORTASI, B)
        db.deleteConditional(TABLE_PULPING1, COL_ID_PRODUKSI_PULPING1, B)
        db.deleteConditional(TABLE_PULPING2, COL_ID_PRODUKSI_PULPING2, B)
        db.deleteConditional(TABLE_JEMUR_KADAR_AIR, COL_ID_PRODUKSI_KADAR_AIR, B)
        db.deleteConditional(TABLE_JEMUR1, COL_ID_PRODUKSI_JEMUR1, B)
        db.deleteConditional(TABLE_JEMUR2, COL_ID_PRODUKSI_JEMUR2, B)
        db.deleteConditional(TABLE_HULLING, COL_ID_PRODUKSI_HULLING, B)
        db.deleteConditional(TABLE_SUTON_GRADER, COL_ID_PRODUKSI_SUTON_GRADER, B)
        db.deleteConditional(TABLE_SIZE_GRADING, COL_ID_PRODUKSI_SIZE_GRADING, B)
        db.deleteConditional(TABLE_COLOR_SORTER, COL_ID_PRODUKSI_COLOR_SORTER, B)
        db.deleteConditional(TABLE_HAND_PICK, COL_ID_PRODUKSI_HAND_PICK, B)
    }
}
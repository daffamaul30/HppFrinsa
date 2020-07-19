package frinsa.hpp.data

import android.content.Context
import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.tahap.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.collections.ArrayList


class Produk {
    var petik: Petik = Petik()
    var produksi: Produksi = Produksi()
    var fermentasi: Fermentasi = Fermentasi()
    var transport: Transportasi = Transportasi()
    var pulping1: pulpingSatu = pulpingSatu()
    var pulping2: pulpingDua = pulpingDua()
    var jemurKadarAir: jemurKadarAir = jemurKadarAir()
    var jemur1: jemurSatu = jemurSatu()
    var jemur2: jemurDua = jemurDua()
    var hulling: Hulling = Hulling()
    var sutonGrader: sutonGrader = sutonGrader()
    var sizeGrading: sizeGrading = sizeGrading()
    var colorSorter: colorSorter = colorSorter()
    var handPick: handPick = handPick()

    lateinit var context: Context
    private lateinit var db: DBPanen

    constructor(context: Context) {
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
        handPick: handPick
    ) {
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

    constructor(fermentasi: Fermentasi) {
        this.fermentasi = fermentasi
    }

    constructor() {}

    fun getTotalBiaya(data: Produk): Int {
        val totalPetik = intArrayOf(
            data.petik.biaya_petik ?: 0,
            data.petik.biaya_ojek ?: 0,
            data.petik.biaya_cuci ?: 0
        ).sum()
        val totalFermentasi = intArrayOf(
            data.fermentasi.biaya_fermentasi ?: 0,
            data.fermentasi.biaya_muat ?: 0
        ).sum()
        val totalTransportasi = intArrayOf(
            data.transport.biaya_transport ?: 0,
            data.transport.biaya_kawal ?: 0,
            data.transport.biaya_bongkar ?: 0
        ).sum()
        val totalPulping1 = intArrayOf(
            data.pulping1.biaya_pulping ?: 0,
            data.pulping1.biaya_fermentasi ?: 0,
            data.pulping1.biaya_cuci ?: 0,
            data.pulping1.biaya_jemur ?: 0,
            data.pulping1.biaya_muat ?: 0
        ).sum()
        val totalPulping2 = data.pulping2.biaya ?: 0
        val totaljemurKadarAir = data.jemurKadarAir.biaya ?: 0
        val totaljemur1 = data.jemur1.biaya ?: 0
        val totaljemur2 = data.jemur2.biaya ?: 0
        val totalHulling = data.hulling.biaya ?: 0
        val totalSutonGrader = data.sutonGrader.biaya ?: 0
        val totalSizeGrading = data.sizeGrading.biaya ?: 0
        val totalColorSorter = data.colorSorter.biaya ?: 0
        val totalHandPick = data.handPick.biaya ?: 0

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
            //totalSizeGrading,
            totalColorSorter,
            totalHandPick
        ).sum()

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
            COL_BIAYA_PULPING2
        )
        db.insertKadarAir(
            jemurKadarAir(),
            TABLE_JEMUR_KADAR_AIR,
            COL_ID_PRODUKSI_KADAR_AIR,
            COL_TGL_KADAR_AIR,
            COL_BERAT_KADAR_AIR,
            COL_KDR_AIR_KADAR_AIR,
            COL_BIAYA_JEMUR_KADAR_AIR
        )
        db.insertStandard<jemurSatu>(
            jemurSatu(),
            TABLE_JEMUR1,
            COL_ID_PRODUKSI_JEMUR1,
            COL_TGL_JEMUR1,
            COL_BERAT_JEMUR1,
            COL_BIAYA_JEMUR1
        )
        db.insertStandard<jemurDua>(
            jemurDua(),
            TABLE_JEMUR2,
            COL_ID_PRODUKSI_JEMUR2,
            COL_TGL_JEMUR2,
            COL_BERAT_JEMUR2,
            COL_BIAYA_JEMUR2
        )
        db.insertKadarAir<Hulling>(
            Hulling(),
            TABLE_HULLING,
            COL_ID_PRODUKSI_HULLING,
            COL_TGL_HULLING,
            COL_BERAT_HULLING,
            COL_KDR_AIR_HULLING,
            COL_BIAYA_HULLING
        )
        db.insertStandard<sutonGrader>(
            sutonGrader(),
            TABLE_SUTON_GRADER,
            COL_ID_PRODUKSI_SUTON_GRADER,
            COL_TGL_SUTON_GRADER,
            COL_BERAT_SUTON_GRADER,
            COL_BIAYA_SUTON_GRADER
        )
        db.insertStandard<sizeGrading>(
            sizeGrading(),
            TABLE_SIZE_GRADING,
            COL_ID_PRODUKSI_SIZE_GRADING,
            COL_TGL_SIZE_GRADING,
            COL_BERAT_SIZE_GRADING,
            COL_BIAYA_SIZE_GRADING
        )
        db.insertStandard<colorSorter>(
            colorSorter(),
            TABLE_COLOR_SORTER,
            COL_ID_PRODUKSI_COLOR_SORTER,
            COL_TGL_COLOR_SORTER,
            COL_BERAT_COLOR_SORTER,
            COL_BIAYA_COLOR_SORTER
        )
        db.insertStandard<handPick>(
            handPick(),
            TABLE_HAND_PICK,
            COL_ID_PRODUKSI_HAND_PICK,
            COL_TGL_HAND_PICK,
            COL_BERAT_HAND_PICK,
            COL_BIAYA_HAND_PICK
        )
    }

    fun getLastWeight(data: Produk): Double {
        var berat: Double = 0.0
        when (data.produksi.status) {
            "petik" -> {
                berat = data.petik.berat
            }
            "fermentasi" -> {
                berat = data.fermentasi.berat
            }
            "transportasi" -> {
                berat = data.transport.berat
            }
            "pulping Ceri-Gabah Basah" -> {
                berat = data.pulping1.berat
            }
            "pulping" -> {
                berat = data.pulping2.berat
            }
            "jemur Kadar Air" -> {
                berat = data.jemurKadarAir.berat
            }
            "jemurI" -> {
                berat = data.jemur1.berat
            }
            "jemurII" -> {
                berat = data.jemur2.berat
            }
            "hulling" -> {
                berat = data.hulling.berat
            }
            "suton grader" -> {
                berat = data.sutonGrader.berat
            }
            "size grading" -> {
                berat = data.sizeGrading.berat
            }
            "color sorter" -> {
                berat = data.colorSorter.berat
            }
            "Selesai" -> {
                berat = data.handPick.berat
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

    fun insertMerge(produk: Produk) {
        db.insertPanen(produk.produksi, produk.petik)
        db.insertFermentasi(produk.fermentasi)
        db.insertTransportasi(produk.transport)
        db.insertPulping1(produk.pulping1)
        db.insertStandard<pulpingDua>(
            produk.pulping2,
            TABLE_PULPING2,
            COL_ID_PRODUKSI_PULPING2,
            COL_TGL_PULPING2,
            COL_BERAT_PULPING2,
            COL_BIAYA_PULPING2
        )
        db.insertKadarAir(
            produk.jemurKadarAir,
            TABLE_JEMUR_KADAR_AIR,
            COL_ID_PRODUKSI_KADAR_AIR,
            COL_TGL_KADAR_AIR,
            COL_BERAT_KADAR_AIR,
            COL_KDR_AIR_KADAR_AIR,
            COL_BIAYA_JEMUR_KADAR_AIR
        )
        db.insertStandard<jemurSatu>(
            produk.jemur1,
            TABLE_JEMUR1,
            COL_ID_PRODUKSI_JEMUR1,
            COL_TGL_JEMUR1,
            COL_BERAT_JEMUR1,
            COL_BIAYA_JEMUR1
        )
        db.insertStandard<jemurDua>(
            produk.jemur2,
            TABLE_JEMUR2,
            COL_ID_PRODUKSI_JEMUR2,
            COL_TGL_JEMUR2,
            COL_BERAT_JEMUR2,
            COL_BIAYA_JEMUR2
        )
        db.insertKadarAir<Hulling>(
            produk.hulling,
            TABLE_HULLING,
            COL_ID_PRODUKSI_HULLING,
            COL_TGL_HULLING,
            COL_BERAT_HULLING,
            COL_KDR_AIR_HULLING,
            COL_BIAYA_HULLING
        )
        db.insertStandard<sutonGrader>(
            produk.sutonGrader,
            TABLE_SUTON_GRADER,
            COL_ID_PRODUKSI_SUTON_GRADER,
            COL_TGL_SUTON_GRADER,
            COL_BERAT_SUTON_GRADER,
            COL_BIAYA_SUTON_GRADER
        )
        db.insertStandard<sizeGrading>(
            produk.sizeGrading,
            TABLE_SIZE_GRADING,
            COL_ID_PRODUKSI_SIZE_GRADING,
            COL_TGL_SIZE_GRADING,
            COL_BERAT_SIZE_GRADING,
            COL_BIAYA_SIZE_GRADING
        )
        db.insertStandard<colorSorter>(
            produk.colorSorter,
            TABLE_COLOR_SORTER,
            COL_ID_PRODUKSI_COLOR_SORTER,
            COL_TGL_COLOR_SORTER,
            COL_BERAT_COLOR_SORTER,
            COL_BIAYA_COLOR_SORTER
        )
        db.insertStandard<handPick>(
            produk.handPick,
            TABLE_HAND_PICK,
            COL_ID_PRODUKSI_HAND_PICK,
            COL_TGL_HAND_PICK,
            COL_BERAT_HAND_PICK,
            COL_BIAYA_HAND_PICK
        )
    }

    fun mergeData(posisi: MutableList<Int>, list: MutableList<ModelDaftarProduksi>): Produk {
        val daftar: MutableList<Produk> = ArrayList()
        val produk: Produk = Produk()

//        posisi?.forEach {
//            val idData: Int = list.get(it).id!!
//            val data = db.getAllDataConditional(posisi)
//            daftar.add(data)
//        }
        for (i in posisi){
            val data = db.getAllDataConditional(i)
            daftar.add(data)
        }

        produk.produksi = mergeProduksi(daftar)
        produk.petik = mergePetik(daftar)
        produk.fermentasi = mergeFermentasi(daftar)
        produk.transport = mergeTransportasi(daftar)
        produk.pulping1 = mergePulping1(daftar)
        produk.pulping2 = mergePulping2(daftar)
        produk.jemur1 = mergeJemur1(daftar)
        produk.jemur2 = mergeJemur2(daftar)
        produk.sutonGrader = mergeSutonGrader(daftar)
        produk.sizeGrading = mergeSizeGrading(daftar)
        produk.colorSorter = mergeColorSorter(daftar)
        produk.handPick = mergeHandPick(daftar)
        produk.jemurKadarAir = mergeKadarAir(daftar)
        produk.hulling = mergeHulling(daftar)

        insertMerge(produk)

        val merge = db.getAllDataConditional(db.getIdProduksi(TABLE_PRODUKSI))
        return merge
    }

    fun mergeProduksi(daftar: MutableList<Produk>): Produksi {
        var data: Produksi = Produksi()
        var sumber: MutableList<String> = ArrayList()
        var beli_dari: MutableList<String> = ArrayList()
        var bentuk: MutableList<String> = ArrayList()
        var varietas: MutableList<String> = ArrayList()
        var blok: MutableList<String> = ArrayList()
        var proses: String = ""
        var status: String = ""
        daftar.forEach {
            sumber.add(it.produksi.sumber)
            beli_dari.add(it.produksi.beli_dari)
            bentuk.add(it.produksi.bentuk)
            varietas.add(it.produksi.varietas)
            blok.add(it.produksi.blok)
            status = it.produksi.status
            if (it.produksi.proses != "-") {
                proses = it.produksi.proses
            }
//            deleteProduksiById(it.produksi.id_produksi)
        }
        data.sumber = sumber.distinct().joinToString(separator= ", ")
        data.beli_dari = beli_dari.distinct().joinToString(separator= ", ")
        data.bentuk = bentuk.distinct().joinToString(separator= ", ")
        data.varietas = varietas.distinct().joinToString(separator= ", ")
        data.blok = blok.distinct().joinToString(separator= ", ")
        data.proses = proses
        data.status = status

        return data
    }

    fun mergePetik(daftar: MutableList<Produk>): Petik {
        var data: Petik = Petik()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.petik.tgl_petik)
            data.berat = data.berat + it.petik.berat
            data.biaya_petik = data.biaya_petik + it.petik.biaya_petik
            data.biaya_ojek = data.biaya_ojek + it.petik.biaya_ojek
            data.biaya_cuci = data.biaya_cuci + it.petik.biaya_cuci
        }
        data.tgl_petik = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeFermentasi(daftar: MutableList<Produk>): Fermentasi {
        var data: Fermentasi = Fermentasi()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.fermentasi.tanggal)
            data.berat = data.berat + it.fermentasi.berat
            data.biaya_fermentasi = data.biaya_fermentasi + it.fermentasi.biaya_fermentasi
            data.biaya_muat = data.biaya_muat + it.fermentasi.biaya_muat
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeTransportasi(daftar: MutableList<Produk>): Transportasi {
        var data: Transportasi = Transportasi()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.transport.tanggal)
            data.berat = data.berat + it.transport.berat
            data.biaya_transport = data.biaya_transport + it.transport.biaya_transport
            data.biaya_kawal = data.biaya_kawal + it.transport.biaya_kawal
            data.biaya_bongkar = data.biaya_bongkar + it.transport.biaya_bongkar
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergePulping1(daftar: MutableList<Produk>): pulpingSatu {
        var data: pulpingSatu = pulpingSatu()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.pulping1.tanggal)
            data.berat = data.berat + it.pulping1.berat
            data.biaya_pulping = data.biaya_pulping + it.pulping1.biaya_pulping
            data.biaya_fermentasi = data.biaya_fermentasi + it.pulping1.biaya_fermentasi
            data.biaya_cuci = data.biaya_cuci + it.pulping1.biaya_cuci
            data.biaya_jemur = data.biaya_jemur + it.pulping1.biaya_jemur
            data.biaya_muat = data.biaya_muat + it.pulping1.biaya_muat
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergePulping2(daftar: MutableList<Produk>): pulpingDua {
        var data: pulpingDua = pulpingDua()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.pulping2.tanggal)
            data.berat = data.berat + it.pulping2.berat
            data.biaya = data.biaya + it.pulping2.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeJemur1(daftar: MutableList<Produk>): jemurSatu {
        var data: jemurSatu = jemurSatu()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.jemur1.tanggal)
            data.berat = data.berat + it.jemur1.berat
            data.biaya = data.biaya + it.jemur1.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeJemur2(daftar: MutableList<Produk>): jemurDua {
        var data: jemurDua = jemurDua()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.jemur2.tanggal)
            data.berat = data.berat + it.jemur2.berat
            data.biaya = data.biaya + it.jemur2.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeSutonGrader(daftar: MutableList<Produk>): sutonGrader {
        var data: sutonGrader = sutonGrader()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.sutonGrader.tanggal)
            data.berat = data.berat + it.sutonGrader.berat
            data.biaya = data.biaya + it.sutonGrader.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeSizeGrading(daftar: MutableList<Produk>): sizeGrading {
        var data: sizeGrading = sizeGrading()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.sizeGrading.tanggal)
            data.berat = data.berat + it.sizeGrading.berat
            data.biaya = data.biaya + it.sizeGrading.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeColorSorter(daftar: MutableList<Produk>): colorSorter {
        var data: colorSorter = colorSorter()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.colorSorter.tanggal)
            data.berat = data.berat + it.colorSorter.berat
            data.biaya = data.biaya + it.colorSorter.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeHandPick(daftar: MutableList<Produk>): handPick {
        var data: handPick = handPick()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.handPick.tanggal)
            data.berat = data.berat + it.handPick.berat
            data.biaya = data.biaya + it.handPick.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeKadarAir(daftar: MutableList<Produk>): jemurKadarAir {
        var data: jemurKadarAir = jemurKadarAir()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.jemurKadarAir.tanggal)
            data.berat = data.berat + it.jemurKadarAir.berat
            data.kadarAir = data.kadarAir + it.jemurKadarAir.kadarAir
            data.biaya = data.biaya + it.jemurKadarAir.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }

    fun mergeHulling(daftar: MutableList<Produk>): Hulling {
        var data: Hulling = Hulling()
        var tgl: MutableList<String> = ArrayList()
        daftar.forEach {
            tgl.add(it.hulling.tanggal)
            data.berat = data.berat + it.hulling.berat
            data.kadarAir = data.kadarAir + it.hulling.kadarAir
            data.biaya = data.biaya + it.hulling.biaya
        }
        data.tanggal = tgl.distinct().joinToString(separator= ", ")

        return data
    }
}
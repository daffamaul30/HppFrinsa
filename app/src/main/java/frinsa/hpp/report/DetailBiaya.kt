package frinsa.hpp.report

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.Dashboard
import frinsa.hpp.R
import frinsa.hpp.data.Produk
import frinsa.hpp.data.Produksi
import frinsa.hpp.data.Subproses
import frinsa.hpp.data.tahap.*
import kotlinx.android.synthetic.main.detail_biaya.*

class DetailBiaya : AppCompatActivity(), View.OnClickListener {

    private var recyclerView: RecyclerView? = null
    private var adapter: AdapterSubproses? = null
    private var subprosesArraylist: ArrayList<Subproses>? = null

    private var id_Produksi = ""
    private var tanggal_toExtra = ""
    private var varietas_toExtra  = ""
    private var blok_toExtra  = ""
    private var berat_toExtra  = ""
    private var proses_toExtra  = ""
    private var biaya_toExtra  = ""

    private var produk = Produk()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_biaya)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail Biaya"
        }

        tanggal_toExtra = intent.getStringExtra("tanggal")
        varietas_toExtra = intent.getStringExtra("varietas")
        blok_toExtra = intent.getStringExtra("blok")
        berat_toExtra = intent.getStringExtra("berat")
        proses_toExtra = intent.getStringExtra("proses")
        biaya_toExtra = intent.getStringExtra("biaya")
        id_Produksi = intent.getStringExtra("id")

        btn_selesai_detailbiaya.setOnClickListener(this)
        btn_dashboard_detailbiaya.setOnClickListener(this)

        var total_all : TextView = findViewById(R.id.total_allSub)
        total_all.text = produk.formatRupiah((intent.getStringExtra("total_biaya_petik").toInt()
                + intent.getStringExtra("total_biaya_ferm").toInt()
                + intent.getStringExtra("total_biaya_trans").toInt()
                + intent.getStringExtra("total_biaya_pu1").toInt()
                + intent.getStringExtra("biaya_pulping2").toInt()
                + intent.getStringExtra("biaya_jemurKadarAir").toInt()
                + intent.getStringExtra("biaya_jemur1").toInt()
                + intent.getStringExtra("biaya_jemur2").toInt()
                + intent.getStringExtra("biaya_hulling").toInt()
                + intent.getStringExtra("biaya_sutonGrader").toInt()
                + intent.getStringExtra("biaya_sizeGrading").toInt()
                + intent.getStringExtra("biaya_colorSorter").toInt()
                + intent.getStringExtra("biaya_handPick").toInt()).toDouble())

        //add subproses
        addDataSubProses(intent.getStringExtra("step"))
        addDataRincian()

        recyclerView = findViewById(R.id.rv_Subproses)
        adapter = AdapterSubproses(subprosesArraylist, produk)
        recyclerView?.layoutManager = LinearLayoutManager(this@DetailBiaya)
        recyclerView?.adapter = adapter
    }

    fun addDataSubProses(step: String?) { // ngambil data dari report view
        subprosesArraylist = ArrayList()
        var perStep = step?.split(",")
        for (i in 0..perStep!!.size-1) {
            when (perStep!!.get(i)) {
                "petik" -> {
                    if (intent.getStringExtra("tanggal_petik").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_petik"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("total_biaya_petik").toDouble())))}
                }
                "fermentasi" -> {
                    if (intent.getStringExtra("tanggal_fermentasi").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_fermentasi"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("total_biaya_ferm").toDouble())))}
                }
                "transportasi" -> {
                    if (intent.getStringExtra("tanggal_transportasi").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_transportasi"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("total_biaya_trans").toDouble())))}
                }
                "pulping Ceri-Gabah Basah" -> {
                    if (intent.getStringExtra("tanggal_pulp1").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_pulp1"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("total_biaya_pu1").toDouble())))}
                }
                "pulping" -> {
                    if (intent.getStringExtra("tanggal_pulp2").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_pulp2"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_pulping2").toDouble())))}
                }
                "jemur Kadar Air" -> {
                    if (intent.getStringExtra("tanggal_JKA").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_JKA"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_jemurKadarAir").toDouble())))}
                }
                "jemurI" -> {
                    if (intent.getStringExtra("tanggal_jemur1").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_jemur1"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_jemur1").toDouble())))}
                }
                "jemurII" -> {
                    if (intent.getStringExtra("tanggal_jemur2").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_jemur2"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_jemur2").toDouble())))}
                }
                "hulling" -> {
                    if (intent.getStringExtra("tanggal_hulling").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_hulling"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_hulling").toDouble())))}
                }
                "suton grader" -> {
                    if (intent.getStringExtra("tanggal_sutonGrader").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_sutonGrader"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_sutonGrader").toDouble())))}
                }
                "size grading" -> {
                    if (intent.getStringExtra("tanggal_sizeGrading").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_sizeGrading"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_sizeGrading").toDouble())))}
                }
                "color sorter" -> {
                    if (intent.getStringExtra("tanggal_colorSorter").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_colorSorter"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_colorSorter").toDouble())))}
                }
                "hand pick" -> {
                    if (intent.getStringExtra("tanggal_handPick").isNotEmpty()) {
                    subprosesArraylist!!.add(Subproses(intent.getStringExtra("tanggal_handPick"),
                        convertSubprosesnName(perStep!!.get(i)),
                        produk.formatRupiah(intent.getStringExtra("biaya_handPick").toDouble())))}
                }
                else -> {}
            }
        }
    }

    fun addDataRincian() {
        var produksi = Produksi()
        var petik = Petik()
        petik.biaya_petik = intent.getStringExtra("biaya_petik_pet").toInt()
        petik.biaya_ojek = intent.getStringExtra("biaya_ojek_pet").toInt()
        petik.biaya_cuci = intent.getStringExtra("biaya_cuci_pet").toInt()

        var fermentasi = Fermentasi()
        fermentasi.biaya_fermentasi = intent.getStringExtra("biaya_fermentasi_ferm").toInt()
        fermentasi.biaya_muat = intent.getStringExtra("biaya_muat_ferm").toInt()

        var transport = Transportasi()
        transport.biaya_transport = intent.getStringExtra("biaya_transportasi_trans").toInt()
        transport.biaya_kawal = intent.getStringExtra("biaya_kawal_trans").toInt()
        transport.biaya_bongkar = intent.getStringExtra("biaya_bongkar_trans").toInt()

        var pulping1 = pulpingSatu()
        pulping1.biaya_pulping = intent.getStringExtra("biaya_pulping_pu1").toInt()
        pulping1.biaya_fermentasi = intent.getStringExtra("biaya_fermentasi_pu1").toInt()
        pulping1.biaya_cuci = intent.getStringExtra("biaya_cuci_pu1").toInt()
        pulping1.biaya_jemur = intent.getStringExtra("biaya_jemur_pu1").toInt()
        pulping1.biaya_muat = intent.getStringExtra("biaya_muat_pu1").toInt()

        var pulping2 = pulpingDua()
        pulping2.biaya = intent.getStringExtra("biaya_pulping2").toInt()

        var jemurKA = jemurKadarAir()
        jemurKA.biaya = intent.getStringExtra("biaya_jemurKadarAir").toInt()

        var jemur1 = jemurSatu()
        jemur1.biaya = intent.getStringExtra("biaya_jemur1").toInt()

        var jemur2 = jemurDua()
        jemur2.biaya = intent.getStringExtra("biaya_jemur2").toInt()

        var hulling = Hulling()
        hulling.biaya = intent.getStringExtra("biaya_hulling").toInt()

        var sutongrader = sutonGrader()
        sutongrader.biaya = intent.getStringExtra("biaya_sutonGrader").toInt()

        var sizegrading = sizeGrading()
        sizegrading.biaya = intent.getStringExtra("biaya_sizeGrading").toInt()

        var colorsorter = colorSorter()
        colorsorter.biaya = intent.getStringExtra("biaya_colorSorter").toInt()

        var handpick = handPick()
        handpick.biaya = intent.getStringExtra("biaya_handPick").toInt()

        produk = Produk(
            produksi,
            petik,
            fermentasi,
            transport,
            pulping1,
            pulping2,
            jemurKA,
            jemur1,
            jemur2,
            hulling,
            sutongrader,
            sizegrading,
            colorsorter,
            handpick )
    }

    fun convertSubprosesnName(param : String): String {
        return when (param) {
            "petik" -> return "Petik"
            "fermentasi" -> return "Fermentasi"
            "transportasi" -> return "Transportasi"
            "pulping Ceri-Gabah Basah" -> return "Pulping Ceri-Gabah Basah"
            "pulping" -> return "Pulping"
            "jemur Kadar Air" -> return "Jemur Kadar Air"
            "jemurI" -> return "Jemur I"
            "jemurII" -> return "Jemur II"
            "hulling" -> return "Hulling"
            "suton grader" -> return "Suton Grader"
            "size grading" -> return "Size Grading"
            "color sorter" -> return "Color Sorter"
            "hand pick" -> return "Hand Pick"
            else -> return ""
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_selesai_detailbiaya -> {
                val intent = Intent(this@DetailBiaya, ReportView::class.java)
                intent.putExtra("last_id", id_Produksi)
                intent.putExtra("tanggal", tanggal_toExtra)
                intent.putExtra("varietas", varietas_toExtra)
                intent.putExtra("blok", blok_toExtra)
                intent.putExtra("berat", berat_toExtra)
                intent.putExtra("proses", proses_toExtra)
                intent.putExtra("biaya", biaya_toExtra)
                finish()
                startActivity(intent)
            }

            R.id.btn_dashboard_detailbiaya -> {
                val intent = Intent(this@DetailBiaya, Dashboard::class.java)
                finish()
                startActivity(intent)
            }
        }
    }

}
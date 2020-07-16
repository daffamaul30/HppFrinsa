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
import frinsa.hpp.data.Subproses
import kotlinx.android.synthetic.main.detail_biaya.*

class DetailBiaya : AppCompatActivity(), View.OnClickListener {

    private var recyclerView: RecyclerView? = null
    private var adapter: AdapterSubproses? = null
    private var subprosesArraylist: ArrayList<Subproses>? = null

    private var tanggal_toExtra = ""
    private var varietas_toExtra  = ""
    private var blok_toExtra  = ""
    private var berat_toExtra  = ""
//    private var kadar_air_toExtra  = ""
    private var proses_toExtra  = ""
    private var biaya_toExtra  = ""

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

        btn_selesai_detailbiaya.setOnClickListener(this)
        btn_dashboard_detailbiaya.setOnClickListener(this)

        var total_all : TextView = findViewById(R.id.total_allSub)
        total_all.text = "10000"

        //add subproses
        addData(intent.getStringExtra("step"))

        recyclerView = findViewById(R.id.rv_Subproses)
        adapter = AdapterSubproses(subprosesArraylist)
        recyclerView?.layoutManager = LinearLayoutManager(this@DetailBiaya)
        recyclerView?.adapter = adapter
    }

    fun addData(step: String?) { // ngambil data dari report view
        subprosesArraylist = ArrayList()
        var perStep = step?.split(",")
        for (i in 0..perStep!!.size-1) {
            subprosesArraylist!!.add(Subproses(convertSubprosesnName(perStep!!.get(i)),10000))
        }
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
                intent.putExtra("tanggal", tanggal_toExtra)
                intent.putExtra("varietas", varietas_toExtra)
                intent.putExtra("blok", blok_toExtra)
                intent.putExtra("berat", berat_toExtra)
//            intent.putExtra("kadar air",)
                intent.putExtra("proses", proses_toExtra)
                intent.putExtra("biaya", biaya_toExtra)
                startActivity(intent)
            }

            R.id.btn_dashboard_detailbiaya -> {
                val intent = Intent(this@DetailBiaya, Dashboard::class.java)
                startActivity(intent)
            }
        }
    }

}
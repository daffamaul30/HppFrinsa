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

        val tanggal_extra = intent.getStringExtra("tanggal")
        val varietas_extra = intent.getStringExtra("varietas")
        val blok_extra = intent.getStringExtra("blok")
        val berat_extra = intent.getStringExtra("berat")
//        val kadar_air_extra = ""
        val proses_extra = intent.getStringExtra("proses")
        val biaya_extra = intent.getStringExtra("biaya")

        tanggal_toExtra = tanggal_extra
        varietas_toExtra = varietas_extra
        blok_toExtra = blok_extra
        berat_toExtra = berat_extra
        proses_toExtra = proses_extra
        biaya_toExtra = biaya_extra

        btn_selesai_detailbiaya.setOnClickListener(this)
        btn_dashboard_detailbiaya.setOnClickListener(this)

        var total_all : TextView = findViewById(R.id.total_allSub)
        total_all.text = "10000"

        //add subproses
        addData()

        recyclerView = findViewById(R.id.rv_Subproses)
        adapter = AdapterSubproses(subprosesArraylist)
        recyclerView?.layoutManager = LinearLayoutManager(this@DetailBiaya)
        recyclerView?.adapter = adapter
    }

    fun addData() { // ngambil data dari report view
        subprosesArraylist = ArrayList()
        subprosesArraylist!!.add(Subproses("Petik",10000))
        subprosesArraylist!!.add(Subproses("Fermentasi",10000))
        subprosesArraylist!!.add(Subproses("Transportasi",10000))
        subprosesArraylist!!.add(Subproses("Pulping I",10000))
        subprosesArraylist!!.add(Subproses("Pulping II",10000))
        subprosesArraylist!!.add(Subproses("Jemur Kadar Air",10000))
        subprosesArraylist!!.add(Subproses("Jemur I",10000))
        subprosesArraylist!!.add(Subproses("Jemur II",10000))
        subprosesArraylist!!.add(Subproses("Hulling",10000))
        subprosesArraylist!!.add(Subproses("Suton Grader",10000))
        subprosesArraylist!!.add(Subproses("Size Grading",10000))
        subprosesArraylist!!.add(Subproses("Color Sorter",10000))
        subprosesArraylist!!.add(Subproses("Hand Pick",10000))
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
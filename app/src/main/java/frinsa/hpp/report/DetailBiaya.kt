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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_biaya)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail Biaya"
        }

        btn_selesai_detailbiaya.setOnClickListener(this)

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
                startActivity(intent)
            }
            R.id.btn_dashboard_detailbiaya -> {
                val intent = Intent(this@DetailBiaya, Dashboard::class.java)
                startActivity(intent)
            }
        }
    }

}
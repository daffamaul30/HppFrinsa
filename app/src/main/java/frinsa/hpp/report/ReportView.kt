package frinsa.hpp.report

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import frinsa.hpp.R
import frinsa.hpp.daftar_produksi.MainDaftarProduksi
import kotlinx.android.synthetic.main.report_view.*
import kotlin.random.Random


class ReportView : AppCompatActivity(), View.OnClickListener {
    private var series: LineGraphSeries<DataPoint>? = null

    private var subproses: ArrayList<String>? = null
    private var valueSub: ArrayList<Double>? = ArrayList()

    private var tanggal_toExtra = ""
    private var varietas_toExtra  = ""
    private var blok_toExtra  = ""
    private var berat_toExtra  = ""
//    private var kadar_air_toExtra  = ""
    private var proses_toExtra  = ""
    private var biaya_toExtra  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_view)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Report Hasil Panen"
        }

        val tanggal: TextView = findViewById(R.id.report_tanggal)
        val biaya: TextView = findViewById(R.id.report_biaya)
        val varietas: TextView = findViewById(R.id.report_varietas)
        val blok: TextView = findViewById(R.id.report_blok)
        val berat: TextView = findViewById(R.id.report_berat)
        val kadar: TextView = findViewById(R.id.report_kadarair)
        val proses: TextView = findViewById(R.id.report_proses)
        btn_detail_reportview.setOnClickListener(this)
        btn_kembali_reportview.setOnClickListener(this)


        // Grafik
        createGraph()


        //=====================================
        // ngambil value dari halaman sebelum
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


        tanggal.text = tanggal_extra
        varietas.text = varietas_extra
        blok.text = blok_extra
        berat.text = berat_extra
        kadar.text = "100"
        proses.text = proses_extra
        biaya.text = biaya_extra
        //=====================================
    }

    fun createGraph() {
        var y: Double
        var x: Double
        x = 0.0
        y = 0.0
        val graph: GraphView = findViewById(R.id.graph1)

        addData()

        // isi graph
        series = LineGraphSeries()
        series!!.appendData(DataPoint(0.0, 0.0), true, 100)
        series!!.isDrawDataPoints = true
        series!!.setOnDataPointTapListener { series, dataPointInterface ->
            val msg = "Berat : " + dataPointInterface.y +" Kg" +
                    "\nProses : " + getSubproses(dataPointInterface.x)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        // loopingnya berdasarkan panjang subproses yang diperluin?
        for (i in 0..addSubproses()!!.size-1) {

            if (i != 0) {
                // x buat X label
                x += 1.0

                // y buat  Value Y
                // insert data subproses di Y
                // y = value data
                y = valueSub!!.get(i-1)
            }

            series!!.appendData(DataPoint(x, y), true, 100)
        }
        graph.addSeries(series)
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL)

        //format label
        val staticLabelsFormatter = StaticLabelsFormatter(graph)

        // format Y Label
        // isi parameternya diganti bwt load nama subproses dari database
        staticLabelsFormatter.setHorizontalLabels(addSubproses()?.toTypedArray())

        graph.gridLabelRenderer.labelFormatter = staticLabelsFormatter
    }

    fun getSubproses(x : Double) : String {
        when (x) {
            1.0 -> { return "Petik" }
            2.0 -> { return "Fermentasi" }
            3.0 -> { return "Transportasi" }
            4.0 -> { return "Pulping I" }
            5.0 -> { return "Pulping II" }
            6.0 -> { return "Jemur Kadar Air" }
            7.0 -> { return "Jemur I" }
            8.0 -> { return "Jemur II" }
            9.0 -> { return "Hulling" }
            10.0 -> { return "Suton Grader" }
            11.0 -> { return "Size Grading" }
            12.0 -> { return "Color Sorter" }
            13.0 -> { return "Hand Pick" }
            else -> { return "Tidak ada proses" }
        }
    }

    fun addSubproses(): ArrayList<String>? {
        subproses = ArrayList()
        subproses!!.add("")
        subproses!!.add("Pet")
        subproses!!.add("Fer")
        subproses!!.add("Tran")
        subproses!!.add("Pu1")
        subproses!!.add("Pu2")
        subproses!!.add("JKA")
        subproses!!.add("Je1")
        subproses!!.add("Je2")
        subproses!!.add("Hul")
        subproses!!.add("SuG")
        subproses!!.add("SiG")
        subproses!!.add("CS")
        subproses!!.add("HP")

        return subproses
    }

    fun convertSubprosesnName(): String {
        return ""
    }

    fun addData(){ // load dari page sebelumnya
        valueSub = ArrayList()
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
        valueSub!!.add(Random.nextInt(0,400).toDouble())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_detail_reportview -> {
                val intent = Intent(this@ReportView, DetailBiaya::class.java)
                intent.putExtra("tanggal", tanggal_toExtra)
                intent.putExtra("varietas", varietas_toExtra)
                intent.putExtra("blok", blok_toExtra)
                intent.putExtra("berat", berat_toExtra)
//            intent.putExtra("kadar air",)
                intent.putExtra("proses", proses_toExtra)
                intent.putExtra("biaya", biaya_toExtra)
                startActivity(intent)
            }
            R.id.btn_kembali_reportview -> {
                val intent = Intent(this@ReportView, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
        }
    }
}
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
import frinsa.hpp.data.DBPanen
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
    private var step_toExtra  = ""

    private lateinit var db : DBPanen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_view)
        db = DBPanen(this)

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

        tanggal_toExtra = intent.getStringExtra("tanggal")
        varietas_toExtra = intent.getStringExtra("varietas")
        blok_toExtra = intent.getStringExtra("blok")
        berat_toExtra = intent.getStringExtra("berat")
        proses_toExtra = intent.getStringExtra("proses")
        biaya_toExtra = intent.getStringExtra("biaya")


        // Grafik
        var step = db.getStepProses(proses_toExtra)
        step_toExtra = step
        createGraph(step)


        //=====================================
        tanggal.text = tanggal_toExtra
        varietas.text = varietas_toExtra
        blok.text = blok_toExtra
        berat.text = berat_toExtra
        kadar.text = "100"
        proses.text = proses_toExtra
        biaya.text = biaya_toExtra
        //=====================================
    }

    fun createGraph(step: String) {
        var LabelGraph = addSubproses(step)
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
        for (i in 0..LabelGraph!!.size-1) {

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
        staticLabelsFormatter.setHorizontalLabels(LabelGraph?.toTypedArray())

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

    fun addSubproses(step: String): ArrayList<String>? {
        subproses = ArrayList()
        var perStep = step.split(",")
        subproses!!.add("")
        for (i in 0..perStep.size-1) {
            subproses!!.add(convertSubprosesnName(perStep.get(i) ))
        }

        return subproses
    }

    fun convertSubprosesnName(param : String): String {
        return when (param) {
            "petik" -> return "Pet"
            "fermentasi" -> return "Fer"
            "transportasi" -> return "Tran"
            "pulping Ceri-Gabah Basah" -> return "Pu1"
            "pulping" -> return "Pu2"
            "jemur Kadar Air" -> return "JKA"
            "jemurI" -> return "Je1"
            "jemurII" -> return "Je2"
            "hulling" -> return "Hul"
            "suton grader" -> return "SuG"
            "size grading" -> return "SiG"
            "color sorter" -> return "CS"
            "hand pick" -> return "HP"
            else -> return ""
        }
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
                intent.putExtra("step", step_toExtra)
                startActivity(intent)
            }
            R.id.btn_kembali_reportview -> {
                val intent = Intent(this@ReportView, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
        }
    }
}
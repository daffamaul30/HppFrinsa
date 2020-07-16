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
import frinsa.hpp.data.Produk
import kotlinx.android.synthetic.main.report_view.*


class ReportView : AppCompatActivity(), View.OnClickListener {
    private var series: LineGraphSeries<DataPoint>? = null

    private var subproses: ArrayList<String>? = null
    private var valueSub: ArrayList<Double>? = ArrayList()

    private var tanggal_toExtra = ""
    private var varietas_toExtra  = ""
    private var blok_toExtra  = ""
    private var berat_toExtra  = ""
    private var proses_toExtra  = ""
    private var biaya_toExtra  = ""
    private var step_toExtra  = ""
    private var id_Produksi = ""

    private lateinit var db : DBPanen
    private  lateinit var allData : Produk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_view)
        db = DBPanen(this)
        if (intent.getStringExtra("id") != null) {
            id_Produksi =  intent.getStringExtra("id")
        } else {
            id_Produksi =  intent.getStringExtra("last_id")
        }

        allData = db.getAllDataConditional(id_Produksi.toInt())

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
        createGraph(step,allData)


        //=====================================
        tanggal.text = tanggal_toExtra
        varietas.text = varietas_toExtra
        blok.text = blok_toExtra
        berat.text = berat_toExtra
        kadar.text = (allData.jemurKadarAir.kadarAir + allData.hulling.kadarAir).toString()
        proses.text = proses_toExtra
        biaya.text = biaya_toExtra
        //=====================================
    }

    fun createGraph(step: String, allData: Produk) {
        var labelGraph = addSubproses(step)
        var y: Double
        var x: Double
        x = 0.0
        y = 0.0
        val graph: GraphView = findViewById(R.id.graph1)

        addData(step,allData)

        // isi graph
        series = LineGraphSeries()
        series!!.appendData(DataPoint(0.0, 0.0), true, 100)
        series!!.isDrawDataPoints = true
        series!!.setOnDataPointTapListener { series, dataPointInterface ->
            val msg = "Berat : " + dataPointInterface.y +" Kg" +
                    "\nTahap : " + getSubprosesForDataPoint(dataPointInterface.x,labelGraph)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        for (i in 0..labelGraph!!.size-1) {

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
        staticLabelsFormatter.setHorizontalLabels(labelGraph?.toTypedArray())

        graph.gridLabelRenderer.labelFormatter = staticLabelsFormatter
    }

    fun getSubprosesForDataPoint(
        x: Double,
        labelGraph: ArrayList<String>?
    ) : String {
        return convertSubprosesnName2(labelGraph!![x.toInt()])
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

    fun convertSubprosesnName2(param : String): String {
        return when (param) {
            "Pet" -> return "Petik"
            "Fer" -> return "Fermentasi"
            "Tran" -> return "Transportasi"
            "Pu1" -> return "Pulping Ceri-Gabah Basah"
            "Pu2" -> return "Pulping II"
            "JKA" -> return "Jemur Kadar Air"
            "Je1" -> return "Jemue I"
            "Je2" -> return "Jemur II"
            "Hul" -> return "Hullling"
            "SuG" -> return "Suton Grader"
            "SiG" -> return "Size Grading"
            "CS" -> return "Color Sorter"
            "HP" -> return "Hand Pick"
            else -> return ""
        }
    }

    fun addData(step: String, allData: Produk){
        valueSub = ArrayList()
        var perStep = step.split(",")

        for (i in 0..perStep.size-1) {
            when(perStep.get(i)) {
                "petik" -> valueSub!!.add(allData.petik.berat)
                "fermentasi" -> valueSub!!.add(allData.fermentasi.berat)
                "transportasi" -> valueSub!!.add(allData.transport.berat)
                "pulping Ceri-Gabah Basah" -> valueSub!!.add(allData.pulping1.berat)
                "pulping" -> valueSub!!.add(allData.pulping2.berat)
                "jemur Kadar Air" -> valueSub!!.add((allData.jemurKadarAir.berat))
                "jemurI" -> valueSub!!.add(allData.jemur1.berat)
                "jemurII" -> valueSub!!.add(allData.jemur2.berat)
                "hulling" -> valueSub!!.add(allData.hulling.berat)
                "suton grader" -> valueSub!!.add(allData.sutonGrader.berat)
                "size grading" -> valueSub!!.add(allData.sizeGrading.berat)
                "color sorter" -> valueSub!!.add(allData.colorSorter.berat)
                "hand pick" -> valueSub!!.add(allData.handPick.berat)
                else -> {}
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_detail_reportview -> {
                val intent = Intent(this@ReportView, DetailBiaya::class.java)
                intent.putExtra("id", id_Produksi)

                intent.putExtra("tanggal", tanggal_toExtra)
                intent.putExtra("varietas", varietas_toExtra)
                intent.putExtra("blok", blok_toExtra)
                intent.putExtra("berat", berat_toExtra)
                intent.putExtra("proses", proses_toExtra)
                intent.putExtra("biaya", biaya_toExtra)
                intent.putExtra("step", step_toExtra)

                // Petik
                intent.putExtra("biaya_petik_pet", allData.petik.biaya_petik.toString())
                intent.putExtra("biaya_ojek_pet", allData.petik.biaya_ojek.toString())
                intent.putExtra("biaya_cuci_pet", allData.petik.biaya_cuci.toString())
                intent.putExtra("total_biaya_petik", (allData.petik.biaya_petik +
                        allData.petik.biaya_ojek +
                        allData.petik.biaya_cuci).toString())


                // Fermentasi
                intent.putExtra("biaya_fermentasi_ferm", allData.fermentasi.biaya_fermentasi.toString())
                intent.putExtra("biaya_muat_ferm", allData.fermentasi.biaya_muat.toString())
                intent.putExtra("total_biaya_ferm", (allData.fermentasi.biaya_fermentasi +
                        allData.fermentasi.biaya_muat).toString())

                // Transportasi
                intent.putExtra("biaya_transportasi_trans", allData.transport.biaya_transport.toString())
                intent.putExtra("biaya_kawal_trans", allData.transport.biaya_kawal.toString())
                intent.putExtra("biaya_bongkar_trans", allData.transport.biaya_bongkar.toString())
                intent.putExtra("total_biaya_trans", (allData.transport.biaya_transport +
                        allData.transport.biaya_kawal +
                        allData.transport.biaya_bongkar).toString())

                //Pulping 1
                intent.putExtra("biaya_pulping_pu1", allData.pulping1.biaya_pulping.toString())
                intent.putExtra("biaya_fermentasi_pu1", allData.pulping1.biaya_fermentasi.toString())
                intent.putExtra("biaya_cuci_pu1", allData.pulping1.biaya_cuci.toString())
                intent.putExtra("biaya_jemur_pu1", allData.pulping1.biaya_jemur.toString())
                intent.putExtra("biaya_muat_pu1", allData.pulping1.biaya_muat.toString())
                intent.putExtra("total_biaya_pu1", (allData.pulping1.biaya_pulping +
                        allData.pulping1.biaya_fermentasi +
                        allData.pulping1.biaya_cuci +
                        allData.pulping1.biaya_jemur +
                        allData.pulping1.biaya_muat).toString())

                //Sisanya
                intent.putExtra("biaya_pulping2", allData.pulping2.biaya.toString())
                intent.putExtra("biaya_jemurKadarAir", allData.jemurKadarAir.biaya.toString())
                intent.putExtra("biaya_jemur1", allData.jemur1.biaya.toString())
                intent.putExtra("biaya_jemur2", allData.jemur2.biaya.toString())
                intent.putExtra("biaya_hulling", allData.hulling.biaya.toString())
                intent.putExtra("biaya_sutonGrader", allData.sutonGrader.biaya.toString())
                intent.putExtra("biaya_sizeGrading", allData.sizeGrading.biaya.toString())
                intent.putExtra("biaya_colorSorter", allData.colorSorter.biaya.toString())
                intent.putExtra("biaya_handPick", allData.handPick.biaya.toString())

                startActivity(intent)
            }
            R.id.btn_kembali_reportview -> {
                val intent = Intent(this@ReportView, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
        }
    }
}
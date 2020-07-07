package frinsa.hpp.report

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import frinsa.hpp.R
import kotlinx.android.synthetic.main.report_view.*
import kotlin.random.Random


class ReportView : AppCompatActivity(), View.OnClickListener {
    private var series: LineGraphSeries<DataPoint>? = null

    private var subproses: ArrayList<String>? = null
    private var valueSub: ArrayList<Double>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_view)

        btn_detail.setOnClickListener(this)

        makeGraph()
        val tanggal: TextView = findViewById(R.id.report_tanggal)
        val biaya: TextView = findViewById(R.id.report_biaya)
        val varietas: TextView = findViewById(R.id.report_varietas)
        val blok: TextView = findViewById(R.id.report_blok)
        val berat: TextView = findViewById(R.id.report_berat)
        val kadar: TextView = findViewById(R.id.report_kadarair)
        val proses: TextView = findViewById(R.id.report_proses)


        var kg = " Kg"
        var massa = 100
        var rp = "Rp. "
        var harga = 100000

        tanggal.text = "14/06/2020"
        varietas.text = "Robus"
        blok.text = "A"
        berat.text = massa.toString()+kg
        kadar.text = "100"
        proses.text = "Jemur"
        biaya.text = rp+harga
    }

    fun makeGraph() {
        var y: Double
        var x: Double
        x = 0.0
        y = 0.0
        val graph: GraphView = findViewById(R.id.graph1)

        addData()

        // isi graph
        series = LineGraphSeries()
        series!!.appendData(DataPoint(0.0, 0.0), true, 100)

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

    fun addSubproses(): ArrayList<String>? {
        subproses = ArrayList()
        subproses!!.add("")
        subproses!!.add("Fer")
        subproses!!.add("Tran")
        subproses!!.add("Pu1")
        subproses!!.add("Pu2")
        subproses!!.add("Je1")
        subproses!!.add("Je2")
        subproses!!.add("Hul")
        subproses!!.add("SuG")
        subproses!!.add("SiG")
        subproses!!.add("CS")
        subproses!!.add("HP")

        return subproses
    }

    fun addData(){ // add param bwt load dari database
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
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_detail -> {
                val intent = Intent(this@ReportView, DetailBiaya::class.java)
                startActivity(intent)
            }
        }
    }
}
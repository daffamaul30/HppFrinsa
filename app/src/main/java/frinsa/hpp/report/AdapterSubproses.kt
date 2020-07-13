package frinsa.hpp.report

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.R
import frinsa.hpp.data.RincianSubproses
import frinsa.hpp.data.Subproses

class AdapterSubproses(dataList: ArrayList<Subproses>?) :
    RecyclerView.Adapter<AdapterSubproses.SubprosesViewHolder>() {

    private val dataList: ArrayList<Subproses>? = dataList
    private var rinciansubArraylist: ArrayList<RincianSubproses>? = null
    private var rinciansubArraylist2: ArrayList<RincianSubproses>? = null
    private var adapterRin: AdapterRincianSub? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubprosesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.detailbiaya_card_sub, parent, false)
        return SubprosesViewHolder(view)
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(
        holder: SubprosesViewHolder,
        position: Int
    ) {
        // ganti nama subproses
        holder.txtSubproses.setText(dataList!![position].nama_sub)
        holder.totalPerSub.setText(dataList!![position].total_biaya_sub.toString())

        //add rincian
        addData()

        adapterRin = AdapterRincianSub(rinciansubArraylist)
        holder.rvRincian.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayout.VERTICAL, false)
        holder.rvRincian.adapter = adapterRin
    }


    fun addData() {
        rinciansubArraylist = ArrayList()
        rinciansubArraylist2 = ArrayList()
        for (i in 0 .. 12) {
//            if (dataList!!.get(i).nama_sub == "Petik") {
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Petik", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Ojek", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Cuci Petik", 10000))
//            } else if (dataList!!.get(i).nama_sub == "Fermentasi") {
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Fermentasi", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Muat Fermentasi", 10000))
//            } else if (dataList!!.get(i).nama_sub == "Pulping I") {
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Pulping I", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Fermentasi Pulping I", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Cuci Pulping I", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Jemur Pulping I", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Muat Pulping I", 10000))
//            } else if (dataList!!.get(i).nama_sub == "Transportasi") {
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Transportasi", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Kawal Trasnportasi", 10000))
//                rinciansubArraylist!!.add(RincianSubproses("Biaya Bongkar Transportasi", 10000))
//            } else {
            rinciansubArraylist!!.add(RincianSubproses("Biaya " + dataList!!.get(i).nama_sub, 10000))
//            }
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    inner class SubprosesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtSubproses: TextView = itemView.findViewById(R.id.judul_card)
        val totalPerSub: TextView = itemView.findViewById(R.id.card_total_sub)
        val rvRincian: RecyclerView = itemView.findViewById(R.id.rv_rincian)
    }
}
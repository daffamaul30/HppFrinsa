package frinsa.hpp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.data.RincianSubproses
import frinsa.hpp.data.Subproses

class AdapterSubproses(dataList: ArrayList<Subproses>?) :
    RecyclerView.Adapter<AdapterSubproses.SubprosesViewHolder>() {

    private val dataList: ArrayList<Subproses>? = dataList
    private var rinciansubArraylist: ArrayList<RincianSubproses>? = null
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


    fun addData() { // load database
        rinciansubArraylist = ArrayList()
        rinciansubArraylist!!.add(RincianSubproses("Dimas Maulana",10000))
        rinciansubArraylist!!.add(RincianSubproses("Fadly Yonk",10000))
        rinciansubArraylist!!.add(RincianSubproses("Ariyandi Nugraha",10000))
        rinciansubArraylist!!.add(RincianSubproses("Aham Siswana",10000))

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
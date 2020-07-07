package frinsa.hpp.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.R
import frinsa.hpp.data.RincianSubproses

class AdapterRincianSub(dataList: ArrayList<RincianSubproses>?) :
    RecyclerView.Adapter<AdapterRincianSub.RincianViewHolder>() {

    private val dataList: ArrayList<RincianSubproses>? = dataList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RincianViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.detailbiaya_rincian_biaya_sub, parent, false)
        return RincianViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RincianViewHolder,
        position: Int
    ) {
        holder.namaRincian.setText(dataList!![position].nama_rincian)
        holder.biayaRincian.setText(dataList!![position].biaya_rincian.toString())
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    inner class RincianViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaRincian: TextView = itemView.findViewById(R.id.card_nama_rincian)
        val biayaRincian: TextView = itemView.findViewById(R.id.card_biaya_rincian)
    }

}
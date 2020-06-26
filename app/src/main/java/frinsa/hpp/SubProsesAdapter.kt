package frinsa.hpp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.Panen
import kotlinx.android.synthetic.main.cardviewproses.view.*

class SubProsesAdapter(val context: Context?, private val dpList: MutableList<ModelDaftarProduksi>): RecyclerView.Adapter<SubProsesAdapter.cardViewProses>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): cardViewProses {
        return cardViewProses(LayoutInflater.from(context).inflate(R.layout.cardviewproses,parent,false))
    }

    override fun getItemCount(): Int = dpList.size

    inner class cardViewProses(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val txtTanggal = itemView.sp_tgl
//        val txtVarietas = itemView.sp_varietas
//        val txtBlok = itemView.sp_blok
//        val intBerat = itemView.sp_berat
//        val txtproses = itemView.sp_proses
//        val intBiaya = itemView.sp_biaya
//        val txtTahap = itemView.sp_tahap
        fun bind(modelDaftarProses: ModelDaftarProduksi){
            itemView.sp_tgl.text = modelDaftarProses.tanggal
            itemView.sp_blok.text = modelDaftarProses.blok
            itemView.sp_varietas.text = modelDaftarProses.varietas
            itemView.sp_berat.text = modelDaftarProses.berat.toString()
            itemView.sp_proses.text = modelDaftarProses.proses
            itemView.sp_biaya.text = modelDaftarProses.biaya.toString()
            itemView.sp_tahap.text = modelDaftarProses.tahap
        }
    }
    override fun onBindViewHolder(holder: cardViewProses, position: Int) {
//        val spanen : ModelDaftarProduksi = dpList[position]
        holder.bind(dpList[position])
    }
}
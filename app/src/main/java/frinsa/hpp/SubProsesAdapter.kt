package frinsa.hpp

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.Produksi
import kotlinx.android.synthetic.main.cardviewproses.*
import kotlinx.android.synthetic.main.cardviewproses.view.*

class SubProsesAdapter(val context: Context?, private val dpList: MutableList<ModelDaftarProduksi>): RecyclerView.Adapter<SubProsesAdapter.cardViewProses>(){
    var checkboxarray = SparseBooleanArray()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): cardViewProses {
        return cardViewProses(LayoutInflater.from(context).inflate(R.layout.cardviewproses,parent,false))
    }

    override fun getItemCount(): Int = dpList.size

    inner class cardViewProses(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ceklist = itemView.ceklis
        init {
            ceklist.setOnClickListener{
                if (!checkboxarray.get(adapterPosition, false)){
                    ceklist.isChecked = true
                    checkboxarray.put(adapterPosition, true)
                }
                else{
                    ceklist.isChecked = false
                    checkboxarray.put(adapterPosition, false)
                }
            }
        }
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
        var posisi = holder.bind(dpList[position])
        holder.ceklist.isChecked = checkboxarray.get(position, false)
        holder.ceklist.text = "$posisi"
    }

}
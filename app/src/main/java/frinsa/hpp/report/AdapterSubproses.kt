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
import frinsa.hpp.data.Produk
import frinsa.hpp.data.RincianSubproses
import frinsa.hpp.data.Subproses

class AdapterSubproses(
    dataList: ArrayList<Subproses>?,
    dataRincian: Produk
) :
    RecyclerView.Adapter<AdapterSubproses.SubprosesViewHolder>() {

    private val dataList: ArrayList<Subproses>? = dataList
    private val dataRincian: Produk = dataRincian
    private var rinciansubArraylist: ArrayList<RincianSubproses>? = null
    private var adapterRin: AdapterRincianSub? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubprosesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.detailbiaya_card_sub,
            parent, false)
        return SubprosesViewHolder(view)
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(
        holder: SubprosesViewHolder,
        position: Int
    ) {
        // ganti nama subproses
        holder.txtSubproses.setText(dataList!![position].nama_sub)
        holder.totalPerSub.setText(dataList!![position].total_biaya_sub)

        //add rincian
        addData(dataList.get(position).nama_sub!!)

        adapterRin = AdapterRincianSub(rinciansubArraylist)
        holder.rvRincian.layoutManager = LinearLayoutManager(holder.itemView.context,
            LinearLayout.VERTICAL, false)
        holder.rvRincian.adapter = adapterRin
    }


    fun addData(param: String) {
        rinciansubArraylist = ArrayList()
        when (param) {
            "Petik" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya Petik",
                    dataRincian.formatRupiah(dataRincian.petik.biaya_petik.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Ojek",
                    dataRincian.formatRupiah(dataRincian.petik.biaya_ojek.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Cuci",
                    dataRincian.formatRupiah(dataRincian.petik.biaya_cuci.toDouble())))
            }
            "Fermentasi" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya Fermentasi",
                    dataRincian.formatRupiah(dataRincian.fermentasi.biaya_fermentasi.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Muat",
                    dataRincian.formatRupiah(dataRincian.fermentasi.biaya_muat.toDouble())))
            }
            "Pulping Ceri-Gabah Basah" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya Pulping",
                    dataRincian.formatRupiah(dataRincian.pulping1.biaya_pulping.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Fermentasi",
                    dataRincian.formatRupiah(dataRincian.pulping1.biaya_fermentasi.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Cuci",
                    dataRincian.formatRupiah(dataRincian.pulping1.biaya_cuci.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Jemur",
                    dataRincian.formatRupiah(dataRincian.pulping1.biaya_jemur.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Muat",
                    dataRincian.formatRupiah(dataRincian.pulping1.biaya_muat.toDouble())))
            }
            "Transportasi" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya Transportasi",
                    dataRincian.formatRupiah(dataRincian.transport.biaya_transport.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Pengawalan",
                    dataRincian.formatRupiah(dataRincian.transport.biaya_kawal.toDouble())))
                rinciansubArraylist!!.add(RincianSubproses("Biaya Bongkar",
                    dataRincian.formatRupiah(dataRincian.transport.biaya_bongkar.toDouble())))
            }
            "Pulping" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.pulping2.biaya.toDouble())))
            }
            "Jemur Kadar Air" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.jemurKadarAir.biaya.toDouble())))
            }
            "Jemur I" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.jemur1.biaya.toDouble())))
            }
            "Jemur II" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.jemur2.biaya.toDouble())))
            }
            "Hulling" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.hulling.biaya.toDouble())))
            }
            "Suton Grader" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.sutonGrader.biaya.toDouble())))
            }
            "Size Grading" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.sizeGrading.biaya.toDouble())))
            }
            "Color Sorter" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.colorSorter.biaya.toDouble())))
            }
            "Hand Pick" -> {
                rinciansubArraylist!!.add(RincianSubproses("Biaya " + param,
                    dataRincian.formatRupiah(dataRincian.handPick.biaya.toDouble())))
            }
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
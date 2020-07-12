package frinsa.hpp.daftar_produksi

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.R
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.Produk
import frinsa.hpp.data.TABLE_PRODUKSI
import frinsa.hpp.data.tahap.Petik
import kotlinx.android.synthetic.main.card_daftar_produksi.view.*
import kotlinx.android.synthetic.main.card_daftar_produksi.view.btn_dp_edit
import kotlinx.android.synthetic.main.cardviewproses.view.*
import kotlinx.android.synthetic.main.dialog_edit_dp.view.*
import kotlinx.android.synthetic.main.dialog_edit_dp.view.btn_dp_batal
import kotlinx.android.synthetic.main.dialog_edit_dp.view.btn_dp_submit
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_berat
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_blok
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_proses
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_tahap
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_tgl
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_varietas
import kotlinx.android.synthetic.main.dialog_edit_petik.view.*
import kotlinx.android.synthetic.main.dialog_submit.view.*

//2nd Adapter bagian Recycler View
class DaftarProduksiAdapter (val context: Context?, private val dpList: MutableList<ModelDaftarProduksi>): RecyclerView.Adapter<DaftarProduksiAdapter.cardViewHolder>(){
    val produk: Produk = Produk(context!!)
    private var db = DBPanen(context!!)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): cardViewHolder {
        return cardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_daftar_produksi,parent,false))
    }

    override fun getItemCount(): Int = dpList.size

    override fun onBindViewHolder(holder: cardViewHolder, position: Int) {
        holder.bind(dpList[position])
        val tahap = dpList[position].tahap

        holder.itemView.btn_dp_edit.setOnClickListener{
            when (tahap){
                "petik" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_petik,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biayaPetik = dialog.et_petik_biaya_beli.text.toString()
                        val biayaOjek = dialog.et_petik_biaya_ojek.text.toString()
                        val biayaCuci = dialog.et_petik_biaya_cuci.text.toString()
                        if (biayaPetik.isEmpty() or biayaOjek.isEmpty() or biayaCuci.isEmpty()){
                            //Pesan klo ada yg kosong blm kepikiran
                        }else{
                            //Auto Refresh Data belum bisa
                            val petik = Petik()
                            petik.biaya_petik = biayaPetik.toInt()
                            petik.biaya_ojek = biayaOjek.toInt()
                            petik.biaya_cuci = biayaCuci.toInt()
                            db.updateBiayaPetik(dpList.get(holder.position).id!!.toInt(),petik)
                            val biayaUpdate:Int = petik.biaya_petik + petik.biaya_ojek + petik.biaya_cuci
                            val biayaAsal:Int = db.getBiayaPetik(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            dpList[position].biaya = biayaDPList + biayaUpdate - biayaAsal
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
                    }
                }
                //proses lain ngikut petik klo dah jadi
                "fermentasi" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_fermentasi,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "transportasi" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_transportasi,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "pulping Ceri-Gabah Basah" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_pulping1,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "pulping" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_pulping2,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "jemur Kadar Air" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_jemur_kadar_air,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "jemurI" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_jemur2,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "jemurII" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_jemur3,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "hulling" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_hulling,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "suton grader" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_suton_grader,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "size grading" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_size_grading,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "color sorter" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_color_sorter,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                "Selesai" -> {
                    val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_edit_hand_pick,null)
                    val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
                    dialog.edt_dp_tgl.text = dpList[position].tanggal
                    dialog.edt_dp_varietas.text = dpList[position].varietas
                    dialog.edt_dp_blok.text = dpList[position].blok
                    dialog.edt_dp_berat.text = dpList[position].berat.toString() + " Kg"
                    dialog.edt_dp_proses.text = dpList[position].proses
                    //dialog.edt_dp_biaya.text = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                    dialog.edt_dp_tahap.text = dpList[position].tahap
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations =
                        R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
            }


        }

        holder.itemView.btn_dp_delete.setOnClickListener{
            val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_submit, null)
            val builder = AlertDialog.Builder(context).setView(dialog).setTitle("")
            dialog.tv_submit.text = "Apakah anda yakin ingin menghapus item?"
            val alertDialog =  builder.create()
            alertDialog.window?.attributes?.windowAnimations =
                R.style.DialogAnim_Fade
            alertDialog.show()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.submit_submit.setOnClickListener {
                produk.deleteProduksiById(dpList.get(holder.position).id!!.toInt())
                dpList.removeAt(position)
                notifyItemRemoved(position)
                alertDialog.dismiss()
            }
            dialog.batal_submit.setOnClickListener{
                alertDialog.dismiss()
            }
        }
    }

    inner class cardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(modelDaftarProduksi: ModelDaftarProduksi){
            itemView.dp_tv_tgl.text = modelDaftarProduksi.tanggal
            itemView.dp_tv_blok.text = modelDaftarProduksi.blok
            itemView.dp_tv_varietas.text = modelDaftarProduksi.varietas
            itemView.dp_tv_berat.text = modelDaftarProduksi.berat.toString() + " Kg"
            itemView.dp_tv_proses.text = modelDaftarProduksi.proses
            itemView.dp_tv_biaya.text = produk.formatRupiah(modelDaftarProduksi.biaya!!.toDouble())
            itemView.dp_tv_tahap.text = modelDaftarProduksi.tahap
            itemView.dp_id.text = modelDaftarProduksi.id.toString()
        }
    }
}
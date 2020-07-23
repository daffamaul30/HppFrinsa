package frinsa.hpp.daftar_produksi

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import frinsa.hpp.R
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.Produk
import frinsa.hpp.data.tahap.*
import frinsa.hpp.report.ReportView
import kotlinx.android.synthetic.main.card_daftar_produksi.view.*
import kotlinx.android.synthetic.main.dialog_edit_color_sorter.view.*
import kotlinx.android.synthetic.main.dialog_edit_dp.view.btn_dp_batal
import kotlinx.android.synthetic.main.dialog_edit_dp.view.btn_dp_submit
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_berat
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_blok
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_proses
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_tahap
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_tgl
import kotlinx.android.synthetic.main.dialog_edit_dp.view.edt_dp_varietas
import kotlinx.android.synthetic.main.dialog_edit_fermentasi.view.*
import kotlinx.android.synthetic.main.dialog_edit_hand_pick.view.*
import kotlinx.android.synthetic.main.dialog_edit_hulling.view.*
import kotlinx.android.synthetic.main.dialog_edit_jemur2.view.*
import kotlinx.android.synthetic.main.dialog_edit_jemur_kadar_air.view.*
import kotlinx.android.synthetic.main.dialog_edit_petik.view.*
import kotlinx.android.synthetic.main.dialog_edit_pulping1.view.*
import kotlinx.android.synthetic.main.dialog_edit_pulping2.view.*
import kotlinx.android.synthetic.main.dialog_edit_size_grading.view.*
import kotlinx.android.synthetic.main.dialog_edit_suton_grader.view.*
import kotlinx.android.synthetic.main.dialog_edit_transportasi.view.*
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
                    var sumber = "Beli"
                    sumber = db.getSumberPetik(dpList.get(holder.position).id!!.toInt())
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
                    if(sumber=="Beli"){

                        dialog.et_petik_biaya_ojek.isEnabled = false
                        dialog.tv_petik_biaya_ojek.setTextColor(Color.parseColor("#c2a7a9"))

                    }
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.btn_dp_batal.setOnClickListener{
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biayaPetik = dialog.et_petik_biaya_beli.text.toString()
                        val biayaOjek = dialog.et_petik_biaya_ojek.text.toString()
                        val biayaCuci = dialog.et_petik_biaya_cuci.text.toString()
                        if (biayaPetik.isEmpty() or biayaOjek.isEmpty() or biayaCuci.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()
                        }else if(sumber == "Beli"){

                            val petik = Petik()
                            petik.biaya_petik = biayaPetik.toInt()
                            petik.biaya_ojek = 0
                            petik.biaya_cuci = biayaCuci.toInt()

                            //Auto Refresh Data
                            val biayaUpdate:Int = petik.biaya_petik + petik.biaya_cuci
                            val biayaAsal:Int = db.getBiayaPetik(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)


                            db.updateBiayaPetik(dpList.get(holder.position).id!!.toInt(),petik)
                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }else{

                            val petik = Petik()
                            petik.biaya_petik = biayaPetik.toInt()
                            petik.biaya_ojek = biayaOjek.toInt()
                            petik.biaya_cuci = biayaCuci.toInt()

                            //Auto Refresh Data
                            val biayaUpdate:Int = petik.biaya_petik + petik.biaya_ojek + petik.biaya_cuci
                            val biayaAsal:Int = db.getBiayaPetik(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)


                            db.updateBiayaPetik(dpList.get(holder.position).id!!.toInt(),petik)
                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biayaFermentasi = dialog.et_fermentasi_biaya_fermentasi.text.toString()
                        val biayaMuat = dialog.et_fermentasi_biaya_muat.text.toString()
                        if (biayaFermentasi.isEmpty() or biayaMuat.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()
                        }else{
                            val fermentasi = Fermentasi()
                            fermentasi.biaya_fermentasi = biayaFermentasi.toInt()
                            fermentasi.biaya_muat = biayaMuat.toInt()

                            val biayaUpdate:Int = fermentasi.biaya_fermentasi + fermentasi.biaya_muat
                            val biayaAsal:Int = db.getBiayaFermentasi(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaFermentasi(dpList.get(holder.position).id!!.toInt(),fermentasi)
                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }

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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biayaTransport = dialog.et_transport_biaya_transport.text.toString()
                        val biayaKawal = dialog.et_transport_biaya_kawal.text.toString()
                        val biayaBongkar = dialog.et_transport_biaya_bongkar.text.toString()
                        if (biayaBongkar.isEmpty() or biayaKawal.isEmpty() or biayaBongkar.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val transport = Transportasi()
                            transport.biaya_bongkar = biayaBongkar.toInt()
                            transport.biaya_kawal = biayaKawal.toInt()
                            transport.biaya_transport = biayaTransport.toInt()

                            val biayaUpdate:Int = transport.biaya_bongkar + transport.biaya_kawal + transport.biaya_transport
                            val biayaAsal:Int = db.getBiayaTransport(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaTransport(dpList.get(holder.position).id!!.toInt(),transport)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biayaPulping = dialog.et_pulp1_biaya_pulping.text.toString()
                        val biayaFermentasi = dialog.et_pulp1_biaya_fermentasi.text.toString()
                        val biayaCuci = dialog.et_pulp1_biaya_cuci.text.toString()
                        val biayaJemur = dialog.et_pulp1_biaya_jemur.text.toString()
                        val biayaMuat = dialog.et_pulp1_biaya_muat.text.toString()
                        if (biayaPulping.isEmpty() or biayaFermentasi.isEmpty() or biayaCuci.isEmpty() or biayaJemur.isEmpty() or biayaMuat.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = pulpingSatu()
                            proses.biaya_pulping = biayaPulping.toInt()
                            proses.biaya_fermentasi = biayaFermentasi.toInt()
                            proses.biaya_cuci = biayaCuci.toInt()
                            proses.biaya_jemur = biayaJemur.toInt()
                            proses.biaya_muat = biayaMuat.toInt()

                            val biayaUpdate:Int = proses.biaya_pulping + proses.biaya_fermentasi + proses.biaya_cuci + proses.biaya_jemur + proses.biaya_muat
                            val biayaAsal:Int = db.getBiayaPulpSatu(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaPulpSatu(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_pulp2_biaya_pulping.text.toString()
                        if (biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = pulpingDua()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaPulpDua(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaPulpDua(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_jemur_kadar_air_biaya_jemur.text.toString()
                        val kadarAir = dialog.et_jemur_kadar_air.text.toString()
                        if (biaya.isEmpty() or kadarAir.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = jemurKadarAir()
                            proses.biaya = biaya.toInt()
                            proses.kadarAir = kadarAir.toDouble()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaJemurKadarAir(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaJemurKadarAir(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_jemur2_biaya_jemur.text.toString()
                        if (biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = jemurSatu()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaJemurSatu(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaJemurSatu(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_jemur2_biaya_jemur.text.toString()
                        if (biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = jemurDua()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaJemurDua(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaJemurDua(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_hulling_biaya.text.toString()
                        val kadarAir = dialog.et_hulling_kadar_air.text.toString()
                        if (biaya.isEmpty() or kadarAir.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = Hulling()
                            proses.biaya = biaya.toInt()
                            proses.kadarAir = kadarAir.toDouble()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaHulling(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaHulling(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_suton_grader_biaya.text.toString()
                        if (biaya.isEmpty() or biaya.isEmpty() or biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = sutonGrader()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaSutonGrader(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)


                            db.updateBiayaSutonGrader(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_size_grading_biaya.text.toString()
                        if (biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = sizeGrading()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaSizeGrading(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaSizeGrading(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_color_sorter_biaya.text.toString()
                        if (biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = colorSorter()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaColorSorter(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaColorSorter(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                        Toast.makeText(context, "Edit Biaya Dibatalkan", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                    dialog.btn_dp_submit.setOnClickListener {
                        val biaya = dialog.et_hand_pick_biaya.text.toString()
                        if (biaya.isEmpty()){
                            Toast.makeText(context, "Nominal Biaya Harus Di Isi", Toast.LENGTH_SHORT).show()

                        }else{
                            val proses = handPick()
                            proses.biaya = biaya.toInt()

                            val biayaUpdate:Int = proses.biaya
                            val biayaAsal:Int = db.getBiayaHandPick(dpList.get(holder.position).id!!.toInt())
                            val biayaDPList:Int = dpList[position].biaya!!.toInt()
                            val model = ModelDaftarProduksi(
                                id = dpList[position].id,
                                tanggal = dpList[position].tanggal,
                                blok = dpList[position].blok,
                                varietas = dpList[position].varietas,
                                berat = dpList[position].berat,
                                proses = dpList[position].proses,
                                biaya = biayaDPList + biayaUpdate - biayaAsal,
                                tahap = dpList[position].tahap
                            )
                            dpList.removeAt(position)
                            dpList.add(position,model)

                            db.updateBiayaHandPick(dpList.get(holder.position).id!!.toInt(),proses)

                            Toast.makeText(context, "Biaya Berhasil Diubah", Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                            notifyDataSetChanged()
                            alertDialog.dismiss()
                        }
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
                notifyDataSetChanged()
                alertDialog.dismiss()
            }
            dialog.batal_submit.setOnClickListener{
                alertDialog.dismiss()
            }
        }

        holder.itemView.btn_dp_detail.setOnClickListener(View.OnClickListener {
            if (dpList[position].proses != "-") {
                var intent = Intent()
                intent = Intent(context, ReportView::class.java)
                intent.putExtra("id", dpList[holder.position].id!!.toString())
                intent.putExtra("tanggal", dpList[position].tanggal)
                intent.putExtra("varietas", dpList[position].varietas)
                intent.putExtra("blok", dpList[position].blok)
                intent.putExtra("berat", dpList[position].berat.toString() + " Kg")
                intent.putExtra("proses", dpList[position].proses)
                val biaya = produk.formatRupiah(dpList[position].biaya!!.toDouble())
                intent.putExtra("biaya", biaya)
                context!!.startActivity(intent)
            } else {
                Toast.makeText(context,"Pilih Proses Terlebih Dahulu",Toast.LENGTH_SHORT).show()
            }
        })

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
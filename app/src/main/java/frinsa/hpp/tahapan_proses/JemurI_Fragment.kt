package frinsa.hpp.tahapan_proses

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frinsa.hpp.R
import frinsa.hpp.data.*
import frinsa.hpp.data.tahap.jemurSatu
import frinsa.hpp.lanjut_produksi.posisi
import frinsa.hpp.lanjut_produksi.spList
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_jemur2_.*
import kotlinx.android.synthetic.main.fragment_jemur2_.view.*
import java.text.SimpleDateFormat
import java.util.*


class JemurI_Fragment: Fragment(),View.OnClickListener{
    var idData: Int = 0
    var Varietas : String = ""
    var Blok : String = ""
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosJemur2: String
    private lateinit var db : DBPanen
    private lateinit var produk : Produk

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jemur2_, container, false)

        //set text varietas dan blok berdasarkan yang dipilih
        view.tv_varietas_jemur2.text = Varietas
        view.tv_blok_jemur2.text = Blok
        view.btn_kirim_jemur2.setOnClickListener(this)
        view.btn_datepicker_jemur2.setOnClickListener(this)
        db = DBPanen(requireContext())
        produk = Produk(requireContext())

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_jemur2 -> {
                val valid = validasiForm()
                println(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.create()
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnim_Fade
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                    val jemur1 = jemurSatu(
                        tanggal = tvTgl,
                        berat = edtBerat.toDouble(),
                        biaya = edtOngkosJemur2.toInt()
                    )
                        val success = db.updateStandard<jemurSatu>(
                            idData,
                            jemur1,
                            TABLE_JEMUR1,
                            COL_ID_PRODUKSI_JEMUR1,
                            COL_TGL_JEMUR1,
                            COL_BERAT_JEMUR1,
                            COL_BIAYA_JEMUR1)
                        if (success) {
                            val result = db.updateStatus(idData, "jemurI")
                            if (result) {
                                alertDialog.dismiss()
                                activity?.finish()
                            }
                            if (posisi.size > 1) {
                                posisi.forEach {
                                    produk.deleteProduksiById(it)
                                }
                            }

                        }
                    }
                    dialog.batal_submit.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }
            }
            R.id.btn_datepicker_jemur2 -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_jemur2.text = dateFormat.format(now.time)
                    },
                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }
        }
    }
    private fun validasiForm(): Boolean {
        var valid: Boolean = false
        //ambil value dari form
        tvTgl = input_tgl_jemur2.text.toString()
        edtBerat = et_berat_jemur2.text.toString()
        edtOngkosJemur2 = et_ongkos_jemur_jemur2.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_jemur2.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_jemur2.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosJemur2.isEmpty()) {
            isEmptyFields = true
            et_ongkos_jemur_jemur2.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}

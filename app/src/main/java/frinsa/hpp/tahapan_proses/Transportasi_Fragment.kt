package frinsa.hpp.tahapan_proses

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import frinsa.hpp.R
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.tahap.Transportasi
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_suton_grader_.*
import kotlinx.android.synthetic.main.fragment_transportasi_.*
import kotlinx.android.synthetic.main.fragment_transportasi_.view.*
import java.text.SimpleDateFormat
import java.util.*

class Transportasi_Fragment: Fragment(), View.OnClickListener {
    var idData: Int = 0
    var Varietas = String()
    var Blok = String()
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosTransportasi: String
    private lateinit var edtOngkosPengawalan: String
    private lateinit var edtOngkosBongkar: String
    private lateinit var db : DBPanen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transportasi_, container, false)
        tv_varietas_transportasi.text
        tv_blok_transportasi.text
        view.btn_kirim_transportasi.setOnClickListener(this)
        view.btn_datepicker_transportasi.setOnClickListener(this)
        db = DBPanen(requireContext())

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_transportasi -> {
                val valid = validasiForm()

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                    val transportasi = Transportasi(
                        tanggal = tvTgl,
                        berat = edtBerat.toDouble(),
                        biaya_bongkar = edtOngkosBongkar.toInt(),
                        biaya_kawal = edtOngkosPengawalan.toInt(),
                        biaya_transport = edtOngkosTransportasi.toInt()
                    )
                        val success = db.updateTransportasi(idData, transportasi)
                        if (success) {
                            val result = db.updateStatus(idData, "transportasi")
                            if (result) {
                                alertDialog.dismiss()
                                activity?.finish()
                            }

                        }
                    }
                    dialog.batal_submit.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }
            }
            R.id.btn_datepicker_transportasi -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_transportasi.text = dateFormat.format(now.time)
                    },
                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }
        }
    }

    fun toastMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    private fun validasiForm(): Boolean {
        var valid: Boolean = false
        //ambil value dari form
        tvTgl = input_tgl_transportasi.text.toString()
        edtBerat = et_berat_transportasi.text.toString()
        edtOngkosTransportasi = et_ongkos_transportasi.text.toString()
        edtOngkosPengawalan = et_ongkos_pengawalan_transportasi.text.toString()
        edtOngkosBongkar = et_ongkos_bongkar_transportasi.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_transportasi.error ="Pilih Tanggal"
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_transportasi.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosTransportasi.isEmpty()) {
            isEmptyFields = true
            et_ongkos_transportasi.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosPengawalan.isEmpty()) {
            isEmptyFields = true
            et_ongkos_pengawalan_transportasi.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosBongkar.isEmpty()) {
            isEmptyFields = true
            et_ongkos_bongkar_transportasi.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }

}
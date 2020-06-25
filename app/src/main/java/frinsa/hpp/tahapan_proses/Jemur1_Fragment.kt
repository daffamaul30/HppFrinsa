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
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_jemur1_.*
import kotlinx.android.synthetic.main.fragment_jemur1_.view.*
import java.text.SimpleDateFormat
import java.util.*

class Jemur1_Fragment : Fragment(), View.OnClickListener {
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosTransport: String
    private lateinit var edtOngkosPengawalan: String
    private lateinit var edtOngkosBongkar: String
    private lateinit var edtOngkosJemur: String
    private lateinit var edtKadarAir: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jemur1_, container, false)

        //set text varietas dan blok berdasarkan yang dipilih

        view.btn_kirim_jemur1.setOnClickListener(this)
        view.btn_datepicker_jemur1.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_jemur1 -> {
                val valid = validasiForm()
                println(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE

                        //test getData

                        alertDialog.dismiss()
                        activity?.finish()
                    }
                    dialog.batal_submit.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }
            }
            R.id.btn_datepicker_jemur1 -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_jemur1.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_jemur1.text.toString()
        edtBerat = et_berat_jemur1.text.toString()
        edtOngkosPengawalan = et_ongkos_pengawalan_jemur1.text.toString()
        edtOngkosTransport = et_ongkos_transportasi_jemur1.text.toString()
        edtOngkosJemur = et_ongkos_jemur_jemur1.text.toString()
        edtOngkosBongkar = et_ongkos_bongkar_jemur1.text.toString()
        edtKadarAir = et_kadar_air_jemur1.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_jemur1.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_jemur1.error = "Field ini tidak boleh kosong"
        }

        if (edtKadarAir.isEmpty()) {
            isEmptyFields = true
            et_kadar_air_jemur1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosTransport.isEmpty()) {
            isEmptyFields = true
            et_ongkos_transportasi_jemur1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosPengawalan.isEmpty()) {
            isEmptyFields = true
            et_ongkos_pengawalan_jemur1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosJemur.isEmpty()) {
            isEmptyFields = true
            et_ongkos_jemur_jemur1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosBongkar.isEmpty()) {
            isEmptyFields = true
            et_ongkos_bongkar_jemur1.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}

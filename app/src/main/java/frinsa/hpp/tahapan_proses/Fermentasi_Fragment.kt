package frinsa.hpp.tahapan_proses

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import frinsa.hpp.R
import frinsa.hpp.ReviewHasilBeli
import kotlinx.android.synthetic.main.activity_input_beli.*
import kotlinx.android.synthetic.main.activity_input_panen.*
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_fermentasi_.*
import kotlinx.android.synthetic.main.fragment_fermentasi_.view.*
import kotlinx.android.synthetic.main.fragment_fermentasi_.view.input_tgl_fermentasi
import java.text.SimpleDateFormat
import java.util.*

class Fermentasi_Fragment : Fragment(), View.OnClickListener {
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosFermentasi: String
    private lateinit var edtOngkosMuat: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fermentasi_, container, false)

        //set text varietas dan blok berdasarkan yang dipilih

        view.btn_kirim_fermentasi.setOnClickListener(this)
        view.btn_datepicker_fermentasi.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_fermentasi -> {
                val valid = validasiForm()

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
            R.id.btn_datepicker_fermentasi -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_fermentasi.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_fermentasi.text.toString()
        edtBerat = et_berat_fermentasi.text.toString()
        edtOngkosFermentasi = et_ongkos_fermentasi.text.toString()
        edtOngkosMuat = et_muat_fermentasi.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_fermentasi.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_fermentasi.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosFermentasi.isEmpty()) {
            isEmptyFields = true
            et_ongkos_fermentasi.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosMuat.isEmpty()) {
            isEmptyFields = true
            et_muat_fermentasi.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}

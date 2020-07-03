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
import frinsa.hpp.data.TABLE_HULLING
import frinsa.hpp.data.tahap.Hulling
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_hand_pick_.*
import kotlinx.android.synthetic.main.fragment_hulling_.*
import kotlinx.android.synthetic.main.fragment_hulling_.view.*
import java.text.SimpleDateFormat
import java.util.*


class Hulling_Fragment : Fragment(), View.OnClickListener {
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtKadarAir: String
    private lateinit var edtOngkosHulling: String
    private lateinit var db : DBPanen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hulling_, container, false)

        //set text varietas dan blok berdasarkan yang dipilih

        view.btn_kirim_hulling.setOnClickListener(this)
        view.btn_datepicker_hulling.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_hulling -> {
                val valid = validasiForm()
                println(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                    val hulling = Hulling(
                        tanggal = tvTgl,
                        biaya = edtBerat.toInt(),
                        berat = edtOngkosHulling.toDouble(),
                        kadarAir = edtKadarAir.toDouble()
                    )
                        db.insertKadarAir(hulling, TABLE_HULLING)
                        //test getData
                        alertDialog.dismiss()
                        activity?.finish()
                    }
                    dialog.batal_submit.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }
            }
            R.id.btn_datepicker_hulling -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_hulling.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_hulling.text.toString()
        edtBerat = et_berat_hulling.text.toString()
        edtOngkosHulling = et_ongkos_hand_pick.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_hulling.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_hulling.error = "Field ini tidak boleh kosong"
        }

        if (edtKadarAir.isEmpty()) {
            isEmptyFields = true
            et_kadar_air_hulling.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosHulling.isEmpty()) {
            isEmptyFields = true
            et_ongkos_hulling.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}
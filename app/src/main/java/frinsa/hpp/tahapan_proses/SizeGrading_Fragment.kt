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
import frinsa.hpp.data.*
import frinsa.hpp.data.tahap.sizeGrading
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_size_grading_.*
import kotlinx.android.synthetic.main.fragment_size_grading_.view.*
import java.text.SimpleDateFormat
import java.util.*

class SizeGrading_Fragment : Fragment(), View.OnClickListener {
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosSizeGrading: String
    private lateinit var db : DBPanen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_size_grading_, container, false)

        view.btn_kirim_size_grading.setOnClickListener(this)
        view.btn_datepicker_size_grading.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_size_grading -> {
                val valid = validasiForm()

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                    val sizeGrading = sizeGrading(
                        id2 = 0,
                        tanggal = tvTgl,
                        berat = edtBerat.toDouble(),
                        biaya = edtOngkosSizeGrading.toInt()
                    )
                        db.insertStandard<sizeGrading>(sizeGrading, TABLE_SIZE_GRADING, COL_ID_SIZE_GRADING, COL_TGL_SIZE_GRADING, COL_BERAT_SIZE_GRADING, COL_BIAYA_SIZE_GRADING)
                        //test getData

                        alertDialog.dismiss()
                        activity?.finish()
                    }
                    dialog.batal_submit.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }
            }
            R.id.btn_datepicker_size_grading -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_size_grading.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_size_grading.text.toString()
        edtBerat = et_berat_size_grading.text.toString()
        edtOngkosSizeGrading = et_ongkos_size_grading.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_size_grading.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_size_grading.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosSizeGrading.isEmpty()) {
            isEmptyFields = true
            et_berat_size_grading.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}
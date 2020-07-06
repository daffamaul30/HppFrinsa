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
import frinsa.hpp.data.tahap.Standard

import frinsa.hpp.data.tahap.colorSorter

import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_color_sorter_.*
import kotlinx.android.synthetic.main.fragment_color_sorter_.view.*
import java.text.SimpleDateFormat
import java.util.*


class ColorSorter_Fragment : Fragment(), View.OnClickListener  {
    var idData: Int = 0
    var Varietas : String = ""
    var Blok : String = ""
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosSorter: String
    private lateinit var db : DBPanen
    private val context = this

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_color_sorter_, container, false)
        //set text varietas dan blok berdasarkan yang dipilih
        view.tv_blok_color_sorter.text = Blok
        view.tv_varietas_color_sorter.text = Varietas
        view.btn_kirim_color_sorter.setOnClickListener(this)
        view.btn_datepicker_color_sorter.setOnClickListener(this)
        db = DBPanen(requireContext())
        return view
    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_color_sorter -> {
                val valid = validasiForm()
                println(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                        val colorSorter = colorSorter(
                            tanggal = tvTgl ,
                            berat = edtBerat.toDouble(),
                            biaya = edtOngkosSorter.toInt()
                        )
                        val success = db.updateStandard<colorSorter>(
                            idData,
                            colorSorter,
                            TABLE_COLOR_SORTER,
                            COL_ID_PRODUKSI_COLOR_SORTER,
                            COL_TGL_COLOR_SORTER,
                            COL_BERAT_COLOR_SORTER,
                            COL_BIAYA_COLOR_SORTER)
                        if (success) {
                            val result = db.updateStatus(idData, "color sorter")
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
            R.id.btn_datepicker_color_sorter -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_color_sorter.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_color_sorter.text.toString()
        edtBerat = et_berat_color_sorter.text.toString()
        edtOngkosSorter = et_ongkos_color_sorter.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_color_sorter.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_color_sorter.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosSorter.isEmpty()) {
            isEmptyFields = true
            et_ongkos_color_sorter.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}

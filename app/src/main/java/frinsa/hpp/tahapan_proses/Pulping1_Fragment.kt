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
import android.widget.Toast
import frinsa.hpp.R
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.tahap.pulpingSatu
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_jemur_kadar_air.*
import kotlinx.android.synthetic.main.fragment_pulping1_.*
import kotlinx.android.synthetic.main.fragment_pulping1_.view.*
import java.text.SimpleDateFormat
import java.util.*

class Pulping1_Fragment : Fragment(), View.OnClickListener {
    var idData: Int = 0
    var Varietas : String = ""
    var Blok : String = ""
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkospulping1: String
    private lateinit var edtOngkosFermentasiPulping: String
    private lateinit var edtOngkosCuciPulping: String
    private lateinit var edtOngkosJemurPulping: String
    private lateinit var edtOngkosMuatPulping: String
    private lateinit var db : DBPanen


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pulping1_, container, false)
        view.tv_varietas_pulping1.text = Varietas
        view.tv_blok_pulping1.text = Blok
        view.btn_kirim_pulping1.setOnClickListener(this)
        view.btn_datepicker_pulping1.setOnClickListener(this)
        db = DBPanen(requireContext())

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_pulping1 -> {
                val valid = validasiForm()

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                    val pulping1 = pulpingSatu(
                        tanggal = tvTgl,
                        berat = edtBerat.toDouble(),
                        biaya_muat = edtOngkosMuatPulping.toInt(),
                        biaya_fermentasi = edtOngkosFermentasiPulping.toInt(),
                        biaya_cuci = edtOngkosCuciPulping.toInt(),
                        biaya_jemur = edtOngkosJemurPulping.toInt(),
                        biaya_pulping = edtOngkospulping1.toInt()
                    )
                        val success = db.updatePulping1(idData, pulping1)
                        if (success) {
                            val result = db.updateStatus(idData, "pulping Ceri-Gabah Basah")
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
            R.id.btn_datepicker_pulping1 -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_pulping1.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_pulping1.text.toString()
        edtBerat = et_berat_pulping1.text.toString()
        edtOngkospulping1 = et_ongkos_pulping1.text.toString()
        edtOngkosFermentasiPulping = et_ongkos_fermentasi_pulping1.text.toString()
        edtOngkosCuciPulping = et_ongkos_cuci_pulping1.text.toString()
        edtOngkosJemurPulping = et_ongkos_jemur_pulping1.text.toString()
        edtOngkosMuatPulping = et_ongkos_muat_pulping1.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_pulping1.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_pulping1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkospulping1.isEmpty()) {
            isEmptyFields = true
            et_ongkos_pulping1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosFermentasiPulping.isEmpty()) {
            isEmptyFields = true
            et_ongkos_fermentasi_pulping1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosCuciPulping.isEmpty()) {
            isEmptyFields = true
            et_ongkos_cuci_pulping1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosJemurPulping.isEmpty()) {
            isEmptyFields = true
            et_ongkos_jemur_pulping1.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosMuatPulping.isEmpty()) {
            isEmptyFields = true
            et_ongkos_muat_pulping1.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}
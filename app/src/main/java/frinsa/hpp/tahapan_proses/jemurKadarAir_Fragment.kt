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
import frinsa.hpp.data.*
import frinsa.hpp.data.tahap.jemurKadarAir
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_jemur_kadar_air.*
import kotlinx.android.synthetic.main.fragment_jemur_kadar_air.view.*
import java.text.SimpleDateFormat
import java.util.*

class jemurKadarAir_Fragment : Fragment(), View.OnClickListener {
    var idData: Int = 0
    var Varietas : String = ""
    var Blok : String = ""
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosJemur: String
    private lateinit var edtKadarAir: String
    private lateinit var db : DBPanen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_jemur_kadar_air, container, false)

        //set text varietas dan blok berdasarkan yang dipilih
        view.tv_varietas_jemurKadarAir.text = Varietas
        view.tv_blok_jemurKadarAir.text = Blok
        view.btn_kirim_jemurKadarAir.setOnClickListener(this)
        view.btn_datepicker_jemurKadarAir.setOnClickListener(this)
        db = DBPanen(requireContext())

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_jemurKadarAir -> {
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
                    val jemurkadarAir = jemurKadarAir(
                        tanggal = tvTgl,
                        berat = edtBerat.toDouble(),
                        kadarAir = edtKadarAir.toDouble(),
                        biaya = edtOngkosJemur.toInt()
                    )
                        val success = db.updateKadarAir(
                            idData,
                            jemurkadarAir,
                            TABLE_JEMUR_KADAR_AIR,
                            COL_ID_PRODUKSI_TRANSPORTASI,
                            COL_TGL_KADAR_AIR,
                            COL_BERAT_KADAR_AIR,
                            COL_KDR_AIR_KADAR_AIR,
                            COL_BIAYA_JEMUR_KADAR_AIR)
                        if (success) {
                            val result = db.updateStatus(idData, "jemur Kadar Air")
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
            R.id.btn_datepicker_jemurKadarAir -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_jemurKadarAir.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_jemurKadarAir.text.toString()
        edtBerat = et_berat_jemurKadarAir.text.toString()
        edtOngkosJemur = et_ongkos_jemur_jemurKadarAir.text.toString()
        edtKadarAir = et_ongkos_jemur_jemurKadarAir.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_jemurKadarAir.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_jemurKadarAir.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosJemur.isEmpty()) {
            isEmptyFields = true
            et_ongkos_jemur_jemurKadarAir.error = "Field ini tidak boleh kosong"
        }

        if (edtKadarAir.isEmpty()) {
            isEmptyFields = true
            et_kadar_air_jemurKadarAir.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}

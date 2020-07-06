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
import frinsa.hpp.data.tahap.handPick
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.fragment_hand_pick_.*
import kotlinx.android.synthetic.main.fragment_hand_pick_.view.*
import java.text.SimpleDateFormat
import java.util.*


class HandPick_Fragment : Fragment() , View.OnClickListener {
    var idData: Int = 0
    var Varietas = String()
    var Blok = String()
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    //Deklarasi semua edit text / textview yg akan divalidasi
    private lateinit var tvTgl: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosPick: String
    private lateinit var db : DBPanen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hand_pick_, container, false)

        //set text varietas dan blok berdasarkan yang dipilih
        tv_blok_hand_pick.text
        tv_varietas_hand_pick.text
        view.btn_kirim_hand_pick.setOnClickListener(this)
        view.btn_datepicker_hand_pick.setOnClickListener(this)
        db = DBPanen(requireContext())

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_kirim_hand_pick -> {
                val valid = validasiForm()
                println(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(requireContext()).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        //INSERT TO DATABASE
                    val HandPick = handPick(
                        tanggal = tvTgl,
                        berat = edtBerat.toDouble(),
                        biaya = edtOngkosPick.toInt()
                    )
                        val success = db.updateStandard<handPick>(
                            idData,
                            HandPick,
                            TABLE_HAND_PICK,
                            COL_ID_PRODUKSI_HAND_PICK,
                            COL_TGL_HAND_PICK,
                            COL_BERAT_HAND_PICK,
                            COL_BIAYA_HAND_PICK)
                        if (success) {
                            val result = db.updateStatus(idData, "hand pick")
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
            R.id.btn_datepicker_hand_pick -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                    requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        now.set(Calendar.YEAR, year)
                        now.set(Calendar.MONTH, month)
                        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        input_tgl_hand_pick.text = dateFormat.format(now.time)
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
        tvTgl = input_tgl_hand_pick.text.toString()
        edtBerat = et_berat_hand_pick.text.toString()
        edtOngkosPick = et_ongkos_hand_pick.text.toString()

        var isEmptyFields = false

        if (tvTgl == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl_hand_pick.setError("Pilih tanggal")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat_hand_pick.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosPick.isEmpty()) {
            isEmptyFields = true
            et_ongkos_hand_pick.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }
}

package frinsa.hpp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_input_panen.*
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.edt_dialog_tmbh_varietas
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InputPanen : AppCompatActivity(), View.OnClickListener {
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    private val blok: MutableList<String> = ArrayList()
    private val varietas: MutableList<String> = ArrayList()
    private val proses: MutableList<String> = ArrayList()

    private lateinit var spinVarietas: String
    private lateinit var spinBlok: String
    private lateinit var spinProses: String
    private lateinit var tvTanggal: String
    private lateinit var edtBerat: String
    private lateinit var edtKolektif: String
    private lateinit var edtBiaya: String

    private var isiNanti: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_panen)

        //Input Field Kolektif Dari Disables
        setDisable(et_kolektif,tv_kolektif)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Hasil Panen"
        }

        //Handling Checkbox
        cb_kolektif.setOnClickListener(this)
        cb_isi_nanti.setOnClickListener(this)

        //call setSpinner function
        setSpinner()

        //DatePicker
        btn_datepicker.setOnClickListener(this)

        //Dialog Box
        btn_tmbh_varietas.setOnClickListener(this)
        btn_tmbh_blok.setOnClickListener(this)
        btn_tmbh_proses.setOnClickListener(this)
        btn_kirim_panen.setOnClickListener(this)
    }

    fun setSpinner() {
        //Spinner Varietas
        val spinnerVarietas:Spinner = findViewById(R.id.spinner_varietas)

        varietas.add(0, "Pilih Varietas")
        varietas.add("Arabica")
        varietas.add("Robusta")

        //Load dari database

        //Style and populate the spinner
        val adapterVarietas = ArrayAdapter(this, android.R.layout.simple_spinner_item, varietas)
        //Dropdown layout style
        adapterVarietas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        spinnerVarietas.adapter = adapterVarietas

        spinnerVarietas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinVarietas = parent.getItemAtPosition(position).toString()
                if (parent.getItemAtPosition(position) === "Pilih Varietas" ) {

                } else {
                    Toast.makeText(parent.context, spinVarietas, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }

        //Spinner Blok
        val spinnerBlok:Spinner = findViewById(R.id.spinner_blok)

        blok.add(0, "Pilih Blok")
        blok.add("A")
        blok.add("B")

        //Load dari database

        //Style and populate the spinner
        val adapterBlok = ArrayAdapter(this, android.R.layout.simple_spinner_item, blok)
        //Dropdown layout style
        adapterBlok.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        spinnerBlok.adapter = adapterBlok

        spinnerBlok.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinBlok = parent.getItemAtPosition(position).toString()
                if (parent.getItemAtPosition(position) === "Pilih Blok" ) {

                } else {
                    Toast.makeText(parent.context, spinBlok, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }

        //Spinner Proses
        val spinnerProses:Spinner = findViewById(R.id.spinner_proses)

        proses.add(0, "Pilih Proses")
        proses.add("A")
        proses.add("B")

        //Load dari database

        //Style and populate the spinner
        val adapterProses = ArrayAdapter(this, android.R.layout.simple_spinner_item, proses)
        //Dropdown layout style
        adapterProses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        spinnerProses.adapter = adapterProses

        spinnerProses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinProses = parent.getItemAtPosition(position).toString()
                if (parent.getItemAtPosition(position) === "Pilih Proses" ) {

                } else {
                    Toast.makeText(parent.context, spinProses, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
    }

    fun setDisable(a: EditText, b: TextView) {
        a.isEnabled = false
        b.setTextColor(Color.parseColor("#c2a7a9"))
    }

    fun setEnable(a: EditText, b: TextView) {
        a.isEnabled = true
        b.setTextColor(Color.parseColor("#000000"))
    }

    fun validationKosong(): Boolean {
        var valid: Boolean = false
        tvTanggal = input_tgl.text.toString()
        edtBerat = et_berat.text.toString()
        edtKolektif = et_kolektif.text.toString()
        edtBiaya = et_biaya.text.toString()

        var isEmptyFields = false

        if (tvTanggal == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl.setError("Pilih tanggal")
        }

        if (spinVarietas == "Pilih Varietas") {
            isEmptyFields = true
        }

        if (spinBlok == "Pilih Blok") {
            isEmptyFields = true
        }

        if (spinProses == "Pilih Proses" && isiNanti == false) {
            isEmptyFields = true
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat.error = "Field ini tidak boleh kosong"
        }

        if (et_kolektif.isEnabled && edtKolektif.isEmpty()) {
            isEmptyFields = true
            et_kolektif.error = "Field ini tidak boleh kosong"
        }

        if (edtBiaya.isEmpty()) {
            isEmptyFields = true
            et_biaya.error = "Field ini tidak boleh kosong"
        }

        if (!isEmptyFields) {
            valid = true
        }
        return valid
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_datepicker -> {
                val now = Calendar.getInstance()
                val datePicker = DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    now.set(Calendar.YEAR, year)
                    now.set(Calendar.MONTH, month)
                    now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    input_tgl.text = dateFormat.format(now.time)
                },
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
                )
            datePicker.show()
            }
            R.id.btn_tmbh_varietas -> {
                val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_tmbh_varietas, null)
                val builder = AlertDialog.Builder(this).setView(dialog).setTitle("Tambah Varietas")
                val alertDialog =  builder.show()

                dialog.submit_tmbh_varietas.setOnClickListener{
                    val edtTambahVarietas: EditText = dialog.edt_dialog_tmbh_varietas
                    val inputTambahVarietas = edtTambahVarietas.text.toString()
                    var isEmptyFields = false

                    if (inputTambahVarietas.isEmpty()) {
                        isEmptyFields = true
                        edtTambahVarietas.error = "Field ini tidak boleh kosong"
                    }else {
                        varietas.add(inputTambahVarietas)
                        alertDialog.dismiss()
                    }
                }
                dialog.batal_tmbh_varietas.setOnClickListener{
                    alertDialog.dismiss()
                }
            }
            R.id.btn_tmbh_blok -> {
                val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_tmbh_varietas, null)
                val builder = AlertDialog.Builder(this).setView(dialog).setTitle("Tambah Blok")
                dialog.edt_dialog_tmbh_varietas.hint = "Masukkan Blok Baru"
                val alertDialog =  builder.show()

                dialog.submit_tmbh_varietas.setOnClickListener{
                    val edtTambahVarietas: EditText = dialog.edt_dialog_tmbh_varietas
                    val inputTambahBlok = edtTambahVarietas.text.toString()
                    var isEmptyFields = false

                    if (inputTambahBlok.isEmpty()) {
                        isEmptyFields = true
                        edtTambahVarietas.error = "Field ini tidak boleh kosong"
                    }else {
                        blok.add(inputTambahBlok)
                        alertDialog.dismiss()
                    }
                }
                dialog.batal_tmbh_varietas.setOnClickListener{
                    alertDialog.dismiss()
                }
            }
            R.id.btn_tmbh_proses -> {
                val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_tmbh_varietas, null)
                val builder = AlertDialog.Builder(this).setView(dialog).setTitle("Tambah Proses")
                dialog.edt_dialog_tmbh_varietas.hint = "Masukkan Proses Baru"
                val alertDialog =  builder.show()

                dialog.submit_tmbh_varietas.setOnClickListener{
                    val edtTambahVarietas: EditText = dialog.edt_dialog_tmbh_varietas
                    val inputTambahProses = edtTambahVarietas.text.toString()
                    var isEmptyFields = false

                    if (inputTambahProses.isEmpty()) {
                        isEmptyFields = true
                        edtTambahVarietas.error = "Field ini tidak boleh kosong"
                    }else {
                        proses.add(inputTambahProses)
                        alertDialog.dismiss()
                    }
                }
                dialog.batal_tmbh_varietas.setOnClickListener{
                    alertDialog.dismiss()
                }
            }
            R.id.cb_kolektif -> {
                if (cb_kolektif.isChecked) {
                    setEnable(et_kolektif,tv_kolektif)
                } else {
                    setDisable(et_kolektif,tv_kolektif)
                }
            }
            R.id.cb_isi_nanti -> {
                if (cb_isi_nanti.isChecked) {
                    isiNanti = true
                    spinner_proses.isEnabled = false
                    tv_proses.setTextColor(Color.parseColor("#c2a7a9"))
                } else {
                    isiNanti = false
                    spinner_proses.isEnabled = true
                    tv_proses.setTextColor(Color.parseColor("#000000"))
                }
            }
            R.id.btn_kirim_panen -> {
                //Validasi inputan kosong
                val valid = validationKosong()
                println(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(this).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        val kolektif = if (edtKolektif.isEmpty()) "-" else edtKolektif
                        val proses = if (isiNanti) "-" else spinProses
                        //Ambil data untuk ke database

                        //Intent menggunakan putextra
                        val intent = Intent(this@InputPanen, ReviewHasilPanen::class.java)
                        intent.putExtra(ReviewHasilPanen.EXTRA_TGL, tvTanggal)
                        intent.putExtra(ReviewHasilPanen.EXTRA_VARIETAS, spinVarietas)
                        intent.putExtra(ReviewHasilPanen.EXTRA_BLOK, spinBlok)
                        intent.putExtra(ReviewHasilPanen.EXTRA_BERAT, edtBerat)
                        intent.putExtra(ReviewHasilPanen.EXTRA_KOLEKTIF, kolektif)
                        intent.putExtra(ReviewHasilPanen.EXTRA_BIAYA, edtBiaya)
                        intent.putExtra(ReviewHasilPanen.EXTRA_PROSES, proses)
                        startActivity(intent)
                        finish()

                        alertDialog.dismiss()
                    }
                    dialog.batal_submit.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }
            }
        }
    }
}

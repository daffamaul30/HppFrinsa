package frinsa.hpp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_input_panen.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.edt_dialog_tmbh_varietas
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InputPanen : AppCompatActivity(), View.OnClickListener {
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ROOT)

    private val blok: MutableList<String> = ArrayList()
    private val varietas: MutableList<String> = ArrayList()
    private val proses: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_panen)

        var edtKolektif: EditText = findViewById(R.id.et_kolektif)
        var tvKolektif: TextView = findViewById(R.id.tv_kolektif)
        edtKolektif.isEnabled = false
//        tvKolektif.setTextColor()

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Hasil Panen"
        }

        //call setSpinner function
        setSpinner()

        //DatePicker
        btn_datepicker.setOnClickListener(this)

        //Dialog Box
        btn_tmbh_varietas.setOnClickListener(this)
        btn_tmbh_blok.setOnClickListener(this)
        btn_tmbh_proses.setOnClickListener(this)
    }

    fun setSpinner() {
        //Spinner Varietas
        val spinnerVarietas:Spinner = findViewById(R.id.spinner_varietas)

        varietas.add(0, "Pilih Varietas")
        varietas.add("Arabica")
        varietas.add("Robusta")

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
                if (parent.getItemAtPosition(position) === "Pilih Varietas" ) {

                } else {
                    var item = parent.getItemAtPosition(position).toString()
                    Toast.makeText(parent.context, item, Toast.LENGTH_SHORT).show()
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
                if (parent.getItemAtPosition(position) === "Pilih Blok" ) {

                } else {
                    var item = parent.getItemAtPosition(position).toString()
                    Toast.makeText(parent.context, item, Toast.LENGTH_SHORT).show()
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
                if (parent.getItemAtPosition(position) === "Pilih Proses" ) {

                } else {
                    var item = parent.getItemAtPosition(position).toString()
                    Toast.makeText(parent.context, item, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
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
        }
    }
}

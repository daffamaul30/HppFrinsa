package frinsa.hpp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import frinsa.hpp.data.*
import frinsa.hpp.data.tahap.*
import kotlinx.android.synthetic.main.activity_input_panen.*
import kotlinx.android.synthetic.main.dialog_submit.view.*
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

    private lateinit var spinVarietas: String
    private lateinit var spinBlok: String
    private lateinit var spinProses: String
    private lateinit var tvTanggal: String
    private lateinit var edtBerat: String
    private lateinit var edtOngkosPetik: String
    private lateinit var edtOjek: String
    private lateinit var edtOngkosCuci: String

    private var isiNanti: Boolean = false

    private val context = this
    private lateinit var db: DBPanen
    private lateinit var vari: Varietas
    private lateinit var blk: Blok
    private lateinit var pros: Proses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_panen)

        //CREATE DATABASE
        db = DBPanen(context)
        //CREATE VARIETAS OBJECT
        vari = Varietas(context)
        //CREATE BLOK OBJECT
        blk = Blok(context)
        //CREATE PROSES OBJECT
        pros = Proses(context)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Hasil Panen"
        }

        //Handling Checkbox
        cb_isi_nanti.setOnClickListener(this )

        //call setSpinner function
        setSpinnerVarietas()
        setSpinnerBlok()
        setSpinnerProses()

        //DatePicker
        btn_datepicker.setOnClickListener(this)

        //Dialog Box
        btn_tmbh_varietas.setOnClickListener(this)
        btn_tmbh_blok.setOnClickListener(this)
        btn_kirim_panen.setOnClickListener(this)
    }

    fun setSpinnerVarietas() {
        //Spinner Varietas
        val spinnerVarietas:Spinner = findViewById(R.id.spinner_varietas)

        val varietas = vari.getVarietas()
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
                    //
                } else {
                    Toast.makeText(parent.context, spinVarietas, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
    }

    fun setSpinnerBlok() {
        //Spinner Blok
        val spinnerBlok:Spinner = findViewById(R.id.spinner_blok)

        val blok = blk.getBlok()
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
                    //
                } else {
                    Toast.makeText(parent.context, spinBlok, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
    }

    fun setSpinnerProses() {
        //Spinner Proses
        val spinnerProses:Spinner = findViewById(R.id.spinner_proses)

        val proses = pros.getProses()
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
                    //
                } else {
                    Toast.makeText(parent.context, spinProses, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun toastMessage(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

    private fun validationKosong(): Boolean {
        var valid: Boolean = false
        tvTanggal = input_tgl.text.toString()
        edtBerat = et_berat.text.toString()
        edtOngkosPetik = et_ongkos_petik.text.toString()
        edtOjek = et_ojek.text.toString()
        edtOngkosCuci = et_ongkos_cuci.text.toString()

        var isEmptyFields = false

        if (tvTanggal == "DD/MM/YYYY") {
            isEmptyFields = true
            input_tgl.setError("Pilih tanggal")
        }

        if (spinVarietas == "Pilih Varietas") {
            isEmptyFields = true
            toastMessage("Varietas tidak boleh kosong")
        }

        if (spinBlok == "Pilih Blok") {
            isEmptyFields = true
            toastMessage("Blok tidak boleh kosong")
        }

        if (spinProses == "Pilih Proses" && isiNanti == false) {
            isEmptyFields = true
            toastMessage("Proses tidak boleh kosong")
        }

        if (edtBerat.isEmpty()) {
            isEmptyFields = true
            et_berat.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosPetik.isEmpty()) {
            isEmptyFields = true
            et_ongkos_petik.error = "Field ini tidak boleh kosong"
        }

        if (edtOjek.isEmpty()) {
            isEmptyFields = true
            et_ojek.error = "Field ini tidak boleh kosong"
        }

        if (edtOngkosCuci.isEmpty()) {
            isEmptyFields = true
            et_ongkos_cuci.error = "Field ini tidak boleh kosong"
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

                    if (inputTambahVarietas.isEmpty()) {
                        edtTambahVarietas.error = "Field ini tidak boleh kosong"
                    }else {
                        val vari =
                            Varietas(name = inputTambahVarietas)
//                        db.insertVarietas(vari)
                        db.insertSpin<Varietas>(vari, TABLE_VARIETAS)

                        setSpinnerVarietas()
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

                    if (inputTambahBlok.isEmpty()) {
                        edtTambahVarietas.error = "Field ini tidak boleh kosong"
                    }else {
                        val blk = Blok(name = inputTambahBlok)
//                        db.insertBlok(blk)
                        db.insertSpin<Blok>(blk, TABLE_BLOK)
                        setSpinnerBlok()
                        alertDialog.dismiss()
                    }
                }
                dialog.batal_tmbh_varietas.setOnClickListener{
                    alertDialog.dismiss()
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
//                println1(valid)

                if (valid) {
                    val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_submit, null)
                    val builder = AlertDialog.Builder(this).setView(dialog).setTitle("")
                    val alertDialog =  builder.show()

                    dialog.submit_submit.setOnClickListener {
                        val proses = if (isiNanti) "-" else spinProses
                        val biaya = edtOngkosPetik.toInt() + edtOjek.toInt() + edtOngkosCuci.toInt()

                        //INSERT TO DATABASE
                        val produksi = Produksi(
                            sumber = "Panen",
                            beli_dari = "-",
                            bentuk = "ceri",
                            varietas = spinVarietas,
                            blok = spinBlok,
                            proses = proses,
                            status = "petik"
                        )
                        val petik = Petik(
                            tgl_petik = tvTanggal,
                            berat = edtBerat.toDouble(),
                            biaya_petik = edtOngkosPetik.toInt(),
                            biaya_ojek = edtOjek.toInt(),
                            biaya_cuci = edtOngkosCuci.toInt()
                        )
                        val produk = Produk(context = this)

                        produk.insertFirst(produksi, petik)

                        //INSERT DATA TO DB

//                        db.insertPanen(produksi, petik)
//                        db.insertFermentasi(Fermentasi())
//                        db.insertTransportasi(Transportasi())
//                        db.insertPulping1(pulpingSatu())
//                        db.insertStandard<pulpingDua>(
//                            pulpingDua(),
//                            TABLE_PULPING2,
//                            COL_ID_PRODUKSI_PULPING2,
//                            COL_TGL_PULPING2,
//                            COL_BERAT_PULPING2,
//                            COL_BIAYA_PULPING2)
//                        db.insertKadarAir(
//                            jemurKadarAir(),
//                            TABLE_JEMUR_KADAR_AIR,
//                            COL_ID_PRODUKSI_KADAR_AIR,
//                            COL_TGL_KADAR_AIR,
//                            COL_BERAT_KADAR_AIR,
//                            COL_KDR_AIR_KADAR_AIR,
//                            COL_BIAYA_JEMUR_KADAR_AIR)
//                        db.insertStandard<jemurSatu>(
//                            jemurSatu(),
//                            TABLE_JEMUR1,
//                            COL_ID_PRODUKSI_JEMUR1,
//                            COL_TGL_JEMUR1,
//                            COL_BERAT_JEMUR1,
//                            COL_BIAYA_JEMUR1)
//                        db.insertStandard<jemurDua>(
//                            jemurDua(),
//                            TABLE_JEMUR2,
//                            COL_ID_PRODUKSI_JEMUR2,
//                            COL_TGL_JEMUR2,
//                            COL_BERAT_JEMUR2,
//                            COL_BIAYA_JEMUR2)
//                        db.insertKadarAir<Hulling>(
//                            Hulling(),
//                            TABLE_HULLING,
//                            COL_ID_PRODUKSI_HULLING,
//                            COL_TGL_HULLING,
//                            COL_BERAT_HULLING,
//                            COL_KDR_AIR_HULLING,
//                            COL_BIAYA_HULLING)
//                        db.insertStandard<sutonGrader>(
//                            sutonGrader(),
//                            TABLE_SUTON_GRADER,
//                            COL_ID_PRODUKSI_SUTON_GRADER,
//                            COL_TGL_SUTON_GRADER,
//                            COL_BERAT_SUTON_GRADER,
//                            COL_BIAYA_SUTON_GRADER)
//                        db.insertStandard<sizeGrading>(
//                            sizeGrading(),
//                            TABLE_SIZE_GRADING,
//                            COL_ID_PRODUKSI_SIZE_GRADING,
//                            COL_TGL_SIZE_GRADING,
//                            COL_BERAT_SIZE_GRADING,
//                            COL_BIAYA_SIZE_GRADING)
//                        db.insertStandard<colorSorter>(
//                            colorSorter(),
//                            TABLE_COLOR_SORTER,
//                            COL_ID_PRODUKSI_COLOR_SORTER,
//                            COL_TGL_COLOR_SORTER,
//                            COL_BERAT_COLOR_SORTER,
//                            COL_BIAYA_COLOR_SORTER)
//                        db.insertStandard<handPick>(
//                            handPick(),
//                            TABLE_HAND_PICK,
//                            COL_ID_PRODUKSI_HAND_PICK,
//                            COL_TGL_HAND_PICK,
//                            COL_BERAT_HAND_PICK,
//                            COL_BIAYA_HAND_PICK)

                        //Intent menggunakan putextra
                        val intent = Intent(this@InputPanen, ReviewHasilPanen::class.java)
                        intent.putExtra(ReviewHasilPanen.EXTRA_TGL, tvTanggal)
                        intent.putExtra(ReviewHasilPanen.EXTRA_VARIETAS, spinVarietas)
                        intent.putExtra(ReviewHasilPanen.EXTRA_BLOK, spinBlok)
                        intent.putExtra(ReviewHasilPanen.EXTRA_BERAT, edtBerat)
                        intent.putExtra(ReviewHasilPanen.EXTRA_BIAYA, biaya.toString())
                        intent.putExtra(ReviewHasilPanen.EXTRA_ONGKOS_PETIK, edtOngkosPetik)
                        intent.putExtra(ReviewHasilPanen.EXTRA_OJEK, edtOjek)
                        intent.putExtra(ReviewHasilPanen.EXTRA_ONGKOS_CUCI, edtOngkosCuci)
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

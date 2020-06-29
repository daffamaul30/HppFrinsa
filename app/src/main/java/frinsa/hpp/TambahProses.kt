package frinsa.hpp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import frinsa.hpp.data.Proses
import frinsa.hpp.data.Varietas
import kotlinx.android.synthetic.main.activity_tambah_proses.*
import kotlinx.android.synthetic.main.activity_tambah_proses.view.*
import kotlinx.android.synthetic.main.dialog_help_proses_baru.view.*
import kotlinx.android.synthetic.main.dialog_submit_tmbh_proses.*
import kotlinx.android.synthetic.main.dialog_submit_tmbh_proses.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.batal_tmbh_varietas
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.submit_tmbh_varietas

class TambahProses : AppCompatActivity(), View.OnClickListener {
    private var context = this
    private var urutan = Array(11, { i -> i+1})
    private val urut: MutableList<Any> = ArrayList()
    private var daftarStep = mapOf<String, String>().toMutableMap()
    val lis: MutableList<Any> = ArrayList()

    private lateinit var spinUrutan: String

    private lateinit var cbPetik: String
    private lateinit var cbFermentasi: String
    private lateinit var cbTransportasi: String
    private lateinit var cbPulping1: String
    private lateinit var cbPulping2: String
    private lateinit var cbJemurKadarAir: String
    private lateinit var cbJemur1: String
    private lateinit var cbJemur2: String
    private lateinit var cbHulling: String
    private lateinit var cbSutonGrader: String
    private lateinit var cbSizeGrading: String
    private lateinit var cbColorSorter: String
    private lateinit var cbHandPick: String

    private lateinit var spinPetik: String
    private lateinit var spinFermentasi: String
    private lateinit var spinTransportasi: String
    private lateinit var spinPulping1: String
    private lateinit var spinPulping2: String
    private lateinit var spinJemurKadarAir: String
    private lateinit var spinJemur1: String
    private lateinit var spinJemur2: String
    private lateinit var spinHulling: String
    private lateinit var spinSutonGrader: String
    private lateinit var spinSizeGrading: String
    private lateinit var spinColroSorter: String
    private lateinit var spinHandPick: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_proses)

        setSpinner()

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Tambah Proses"
        }

        btn_help_proses_baru.setOnClickListener(this)
        batal_tmbh_proses.setOnClickListener(this)
        submit_tmbh_proses.setOnClickListener(this)
        cek_fermentasi.setOnClickListener(this)
        cek_pulping1.setOnClickListener(this)
        cek_pulping2.setOnClickListener(this)
        cek_jemur_kadar_air.setOnClickListener(this)
        cek_jemur1.setOnClickListener(this)
        cek_jemur2.setOnClickListener(this)
    }

    private fun cek(checkBox: CheckBox, spinner: Spinner) {
        if (checkBox.isChecked) {
            spinner.isEnabled = true
        } else {
            spinner.isEnabled = false
        }
    }

    private fun setVariabel(spinner: Spinner, text:String) {
        when (spinner) {
            urut_petik -> {
               spinPetik = text
            }
            urut_fermentasi -> {
                spinFermentasi = text
            }
            urut_transportasi -> {
                spinTransportasi = text
            }
            urut_pulping1 -> {
                spinPulping1 = text
            }
            urut_pulping2 -> {
                spinPulping2 = text
            }
            urut_jemur_kadar_air -> {
                spinJemurKadarAir = text
            }
            urut_jemur1 -> {
                spinJemur1 = text
            }
            urut_jemur2 -> {
                spinJemur2 = text
            }
            urut_hulling -> {
                spinHulling = text
            }
            urut_suton_grader -> {
                spinSutonGrader = text
            }
            urut_size_grading -> {
                spinSizeGrading = text
            }
            urut_color_sorter -> {
                spinColroSorter = text
            }
            urut_hand_pick -> {
                spinHandPick = text
            }
        }
    }

    private fun spinner(spinner: Spinner, checkBox: CheckBox){
        val listP = urutan
        urut.clear()
        urut.add(0, "-")
        if (listP.size > 0) {
            for (i in 0 until listP.size) {
                urut.add(listP[i])
            }
        }
        //Style and populate the spinner
        val adapterProses = ArrayAdapter(this, android.R.layout.simple_spinner_item, urut)
        //Dropdown layout style
        adapterProses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        spinner.adapter = adapterProses

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinUrutan = parent.getItemAtPosition(position).toString()
                setVariabel(spinner,spinUrutan)
                if (parent.getItemAtPosition(position) === "-" ) {
                    //
                } else {
//                    Toast.makeText(parent.context, spinUrutan, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                //
            }
        }
        cek(checkBox, spinner)
    }

    private fun setSpinner() {
        spinner(urut_petik, cek_petik)
        spinner(urut_fermentasi, cek_fermentasi)
        spinner(urut_transportasi, cek_transportasi)
        spinner(urut_pulping1, cek_pulping1)
        spinner(urut_pulping2, cek_pulping2)
        spinner(urut_jemur_kadar_air, cek_jemur_kadar_air)
        spinner(urut_jemur1, cek_jemur1)
        spinner(urut_jemur2, cek_jemur2)
        spinner(urut_hulling, cek_hulling)
        spinner(urut_suton_grader, cek_suton_graderg)
        spinner(urut_size_grading, cek_size_grading)
        spinner(urut_color_sorter, cek_color_sorter)
        spinner(urut_hand_pick, cek_hand_pick)
    }

    private fun toastMessage(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

    private fun getStep(): Pair<String, Boolean> {
        var isEmptySpin = false
        var isEmptyFields = false
        var valid = false
        var step: String = ""

        if (edt_dialog_tmbh_proses2.text.isEmpty()) {
            isEmptyFields = true
            edt_dialog_tmbh_proses2.error = "Field ini tidak boleh kosong"
        }

        if (cek_petik.isChecked) {
            if (spinPetik.toString() != "-") {
                daftarStep.put(spinPetik,"petik")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_fermentasi.isChecked) {
            if (spinFermentasi.toString() != "-") {
                daftarStep.put(spinFermentasi,"fermentasi")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_transportasi.isChecked) {
            if (spinTransportasi.toString() != "-") {
                daftarStep.put(spinTransportasi,"transportasi")
            }
            else{
                isEmptySpin = true
            }

        }
        if (cek_pulping1.isChecked) {
            if (spinPulping1.toString() != "-") {
                daftarStep.put(spinPulping1,"pulping Ceri-Gabah Basah")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_pulping2.isChecked) {
            if (spinPulping2.toString() != "-") {
                daftarStep.put(spinPulping2,"pulping")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_jemur_kadar_air.isChecked) {
            if (spinJemurKadarAir.toString() != "-") {
                daftarStep.put(spinJemurKadarAir,"jemur Kadar Air")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_jemur1.isChecked) {
            if (spinJemur1.toString() != "-") {
                daftarStep.put(spinJemur1,"jemurI")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_jemur2.isChecked) {
            if (spinJemur2.toString() != "-") {
                daftarStep.put(spinJemur2,"jemurII")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_hulling.isChecked) {
            if (spinHulling.toString() != "-") {
                daftarStep.put(spinHulling,"hulling")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_suton_graderg.isChecked) {
            if (spinSutonGrader.toString() != "-") {
                daftarStep.put(spinSutonGrader,"suton grader")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_size_grading.isChecked) {
            if (spinSizeGrading.toString() != "-") {
                daftarStep.put(spinSizeGrading,"size grading")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_color_sorter.isChecked) {
            if (spinColroSorter.toString() != "-") {
                daftarStep.put(spinColroSorter,"color sorter")
            }
            else{
                isEmptySpin = true
            }
        }
        if (cek_hand_pick.isChecked) {
            if (spinHandPick.toString() != "-") {
                daftarStep.put(spinHandPick,"hand pick")
            }
            else{
                isEmptySpin = true
            }
        }
        println(daftarStep)
        if (!isEmptySpin && !isEmptyFields) {
            valid = true
            step = mapToString(daftarStep)
        } else if (isEmptySpin){
            toastMessage("Urutkan tahapan proses")
        }
        return Pair(step, valid)
    }

    private fun mapToString(step: MutableMap<String, String>): String {
        var stepString: String = ""
        if (step.size >= 7) {
            for (i in 1 until step.size+1) {
                lis.add(step.getValue(i.toString()).toString())
            }
            stepString = lis.joinToString(separator= ",")
        }
        println(stepString)
        return stepString
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cek_fermentasi -> {
                cek(cek_fermentasi, urut_fermentasi)
            }
            R.id.cek_pulping1 -> {
                cek(cek_pulping1, urut_pulping1)
            }
            R.id.cek_pulping2 -> {
                cek(cek_pulping2, urut_pulping2)
            }
            R.id.cek_jemur_kadar_air -> {
                cek(cek_jemur_kadar_air, urut_jemur_kadar_air)
            }
            R.id.cek_jemur1 -> {
                cek(cek_jemur1, urut_jemur1)
            }
            R.id.cek_jemur2 -> {
                cek(cek_jemur2, urut_jemur2)
            }
            R.id.btn_help_proses_baru -> {
                val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_help_proses_baru, null)
                val builder = AlertDialog.Builder(context).setView(dialog)
                val alertDialog =  builder.show()

                dialog.ok_help_proses_baru.setOnClickListener{
                    alertDialog.dismiss()
                }
//                Toast.makeText(this,spinPetik,Toast.LENGTH_SHORT).show()
            }
            R.id.batal_tmbh_proses -> {
                finish()
            }
            R.id.submit_tmbh_proses -> {
                val (step,valid) = getStep()
                print(step)
                if (valid && step.isNotEmpty()) {
                    var i = 1
                    var stringBuilder = StringBuilder()
                    lis.forEach {
                        stringBuilder.append("$i " + it).append("\n")
                        i += 1
                    }
                    lis.clear()

                    val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_submit_tmbh_proses,null)
                    val builder = AlertDialog.Builder(this).setView(dialog).setTitle("PERIKSA KEMBALI!")
                    dialog.nama_proses.text = "Nama Proses : " + edt_dialog_tmbh_proses2.text.toString()
                    dialog.daftarStep.text = stringBuilder.toString()

                    val alertDialog =  builder.show()

                    dialog.submit_tmbh_proses.setOnClickListener{
//                        val edtTambahVarietas: EditText = dialog.edt_dialog_tmbh_varietas
//                        val inputTambahVarietas = edtTambahVarietas.text.toString()

                        //Insert to DB
                        val pros = Proses(context)
                        pros.addProses(nama_proses.text.toString(), step)
                        alertDialog.dismiss()
                    }
                    dialog.batal_tmbh_proses.setOnClickListener{
                        alertDialog.dismiss()
                    }
                }

            }
        }
    }


}
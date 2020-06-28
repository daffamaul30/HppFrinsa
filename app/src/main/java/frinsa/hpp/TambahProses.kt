package frinsa.hpp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_tambah_proses.*
import kotlinx.android.synthetic.main.dialog_help_proses_baru.view.*

class TambahProses : AppCompatActivity(), View.OnClickListener {
    private var context = this
    private var urutan = Array(11, { i -> i+1})
    private val urut: MutableList<Any> = ArrayList()
    private lateinit var spinUrutan: String

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
    }

    private fun cek(checkBox: CheckBox, spinner: Spinner) {
        if (checkBox.isChecked) {
            spinner.isEnabled = true
        } else {
            spinner.isEnabled = false
        }
    }

    private fun spinner(spinner: Spinner, checkBox: CheckBox) {
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cek_fermentasi -> {

            }
            R.id.cek_pulping1 -> {
                Toast.makeText(context,"MASOK",Toast.LENGTH_SHORT).show()
            }
            R.id.btn_help_proses_baru -> {
                val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_help_proses_baru, null)
                val builder = AlertDialog.Builder(context).setView(dialog)
                val alertDialog =  builder.show()

                dialog.ok_help_proses_baru.setOnClickListener{
                    alertDialog.dismiss()
                }
            }
            R.id.batal_tmbh_proses -> {
                finish()
            }
            R.id.submit_tmbh_proses -> {
                Toast.makeText(context,edt_dialog_tmbh_proses2.text.toString(),Toast.LENGTH_SHORT).show()
                val valid = validation()
                if (valid) {

                }
            }
        }
    }

    private fun validation(): Boolean {
        var valid: Boolean = false

        return valid
    }

}
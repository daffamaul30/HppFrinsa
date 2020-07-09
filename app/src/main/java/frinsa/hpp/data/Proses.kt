package frinsa.hpp.data

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import frinsa.hpp.R
import kotlinx.android.synthetic.main.dialog_proses_isi_nanti.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*

class Proses {
    private val proses: MutableList<String> = ArrayList()
    private var daftarStep = mapOf<String, String>().toMutableMap()
    var id: Int = 0
    var name: String = ""
    var step: String = ""
    lateinit var context: Context
    private lateinit var db: DBPanen

    constructor(context: Context) {
        this.context = context
        db = DBPanen(this.context)
    }

    constructor(name: String, step: String) {
        this.name = name
        this.step = step
    }

    constructor(){}

    fun addProses(name: String, step: String) {
        val proses = Proses(name, step)
        db.insertProses(proses)
    }

    fun getProses(): MutableList<String> {
        val listP = db.readProses()
        proses.clear()
        proses.add(0, "Pilih Proses")
        if (listP.size > 0) {
            for (i in 0 until listP.size) {
                proses.add(listP[i].name)
            }
        }
        return proses
    }

    fun getStepProses(): MutableMap<String, String> {
        val listP = db.readProses()
        if (listP.size > 0) {
            for (i in 0 until listP.size) {
                daftarStep.put(listP[i].name, listP[i].step)
            }
        }
        return daftarStep
    }

    fun addProsesIsiNanti(idData: Int): String {
        val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_proses_isi_nanti, null)
        val builder = AlertDialog.Builder(context).setView(dialog)
        val alertDialog =  builder.create()
        alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnim_Up_Down
        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val spinProses = setSpinnerProses(dialog.spinner_proses_isi_nanti)

        dialog.submit_proses_isi_nanti.setOnClickListener{

            if (spinProses == "Pilih Proses") {
                Toast.makeText(context, "Proses harus dipilih", Toast.LENGTH_SHORT).show()
            }else {
                db.updateProses(idData, spinProses)
                alertDialog.dismiss()
            }
        }
        dialog.batal_proses_isi_nanti.setOnClickListener{
            alertDialog.dismiss()
        }
        return spinProses
    }
    fun setSpinnerProses(A: Spinner): String {
        var spinProses: String = ""
//        val spinnerProses: Spinner = findViewById(R.id.spinner_proses_isi_nanti)

        val proses = getProses()
        //Style and populate the spinner
        val adapterProses = ArrayAdapter(context, android.R.layout.simple_spinner_item, proses)
        //Dropdown layout style
        adapterProses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        A.adapter = adapterProses

        A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        return spinProses
    }

}
package frinsa.hpp.data

import android.app.AlertDialog
import android.content.Context
import android.content.LocusId
import android.view.LayoutInflater
import android.widget.EditText
import frinsa.hpp.R
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*

class Varietas {
    private val varietas: MutableList<String> = ArrayList()
    var id: Int = 0
    var name: String = ""
    lateinit var context: Context
    private lateinit var db: DBPanen

    constructor(name: String) {
        this.name = name
    }
    constructor(context: Context){
        this.context = context
        db = DBPanen(this.context)
    }
    constructor(){}

    fun addVarietas() {
        val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_tmbh_varietas, null)
        val builder = AlertDialog.Builder(context).setView(dialog).setTitle("Tambah Varietas")
        val alertDialog =  builder.show()

        dialog.submit_tmbh_varietas.setOnClickListener{
            val edtTambahVarietas: EditText = dialog.edt_dialog_tmbh_varietas
            val inputTambahVarietas = edtTambahVarietas.text.toString()

            if (inputTambahVarietas.isEmpty()) {
                edtTambahVarietas.error = "Field ini tidak boleh kosong"
            }else {
                val vari =
                    Varietas(name = inputTambahVarietas)
                db.insertVarietas(vari)
                alertDialog.dismiss()
            }
        }
    }

    fun getVarietas(): MutableList<String> {
        val listV = db.readVarietas()
        varietas.clear()
        varietas.add(0, "Pilih Varietas")
        if (listV.size > 0) {
            for (i in 0 until listV.size) {
                varietas.add(listV[i].name)
            }
        }
        return varietas
    }
}
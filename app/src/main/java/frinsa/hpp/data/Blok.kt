package frinsa.hpp.data

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import frinsa.hpp.R
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*

class Blok: Varietas {
    private val blok: MutableList<String> = ArrayList()
    private lateinit var db: DBPanen

    constructor(name: String) {
        this.name = name
    }

    constructor(context: Context) {
        this.context = context
        db = DBPanen(this.context)
    }

    constructor() {}

    fun addBlok(){
        val dialog = LayoutInflater.from(context).inflate(R.layout.dialog_tmbh_varietas, null)
        val builder = AlertDialog.Builder(context).setView(dialog).setTitle("Tambah Blok")
        dialog.edt_dialog_tmbh_varietas.hint = "Masukkan Blok Baru"
        val alertDialog =  builder.show()
//        dialog.edt_dialog_tmbh_varietas.requestFocus()

        dialog.submit_tmbh_varietas.setOnClickListener{
            val edtTambahVarietas: EditText = dialog.edt_dialog_tmbh_varietas
            val inputTambahBlok = edtTambahVarietas.text.toString()

            if (inputTambahBlok.isEmpty()) {
                edtTambahVarietas.error = "Field ini tidak boleh kosong"
            }else {
                val blk = Blok(name = inputTambahBlok)
//                db.insertBlok(blk)
                db.insertSpin<Blok>(blk, TABLE_BLOK)
                alertDialog.dismiss()
            }
        }
        dialog.batal_tmbh_varietas.setOnClickListener{
            alertDialog.dismiss()
        }
    }

    fun getBlok(): MutableList<String> {
        val listB = db.readSpin(TABLE_BLOK)
        blok.clear()
        blok.add(0, "Pilih Blok")
        if (listB.size > 0) {
            for (i in 0 until listB.size) {
                blok.add(listB[i].name)
            }
        }
        return blok
    }
}
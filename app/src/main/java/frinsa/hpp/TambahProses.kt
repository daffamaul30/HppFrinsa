package frinsa.hpp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import frinsa.hpp.data.Varietas
import kotlinx.android.synthetic.main.activity_tambah_proses.*
import kotlinx.android.synthetic.main.dialog_help_proses_baru.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*

class TambahProses : AppCompatActivity(), View.OnClickListener {
    private var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_proses)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Tambah Proses"
        }

        btn_help_proses_baru.setOnClickListener(this)
        batal_tmbh_proses.setOnClickListener(this)
    }

    private fun setSpinner() {

    }

    override fun onClick(v: View) {
        when (v.id) {
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
//                val valid =
            }
        }
    }

    private fun validation() {
        //
    }

}
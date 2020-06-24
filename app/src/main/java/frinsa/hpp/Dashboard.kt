package frinsa.hpp

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import frinsa.hpp.daftar_produksi.MainDaftarProduksi
import frinsa.hpp.tahapan_proses.TahapanProses
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.dialog_menu_mulai_produksi.view.*

class Dashboard : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        card_daftar.setOnClickListener(this)
        card_mulai.setOnClickListener(this)
        card_lanjut.setOnClickListener(this)

//        card_mulai.setOnTouchListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_mulai -> {
                val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_menu_mulai_produksi, null)
                val builder = AlertDialog.Builder(this).setView(dialog)
                val alertDialog =  builder.show()
                alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.card_panen_sendiri.setOnClickListener{
                    val intent = Intent(this@Dashboard, InputPanen::class.java)
                    startActivity(intent)
                    alertDialog.dismiss()
                }
                dialog.card_beli.setOnClickListener{
                    val intent = Intent(this@Dashboard, InputBeli::class.java)
                    startActivity(intent)
                    alertDialog.dismiss()
                }
            }
            R.id.card_daftar -> {
                val intent = Intent(this@Dashboard, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
            R.id.card_lanjut -> {
                val intent = Intent(this@Dashboard, TahapanProses::class.java)
                startActivity(intent)
            }
        }
    }

}
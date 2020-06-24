package frinsa.hpp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import frinsa.hpp.daftar_produksi.MainDaftarProduksi
import kotlinx.android.synthetic.main.activity_dashboard.*

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
                val intent = Intent(this@Dashboard, InputPanen::class.java)
                startActivity(intent)
            }
            R.id.card_daftar -> {
                val intent = Intent(this@Dashboard, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
            R.id.card_lanjut -> {
                val intent = Intent(this@Dashboard, SubProses::class.java)
                startActivity(intent)
            }
        }
    }

}
package frinsa.hpp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_review_hasil_beli.*
import kotlinx.android.synthetic.main.activity_review_hasil_panen.*
import kotlinx.android.synthetic.main.activity_review_hasil_panen.btn_kembali_dashboard

class ReviewHasilBeli : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TGL  = "extra_tgl"
        const val EXTRA_VARIETAS = "extra_varietas"
        const val EXTRA_BLOK = "extra_blok"
        const val EXTRA_BERAT = "extra_berat"
        const val EXTRA_KOLEKTIF = "extra_kolektif"
        const val EXTRA_BIAYA = "extra_biaya"
        const val EXTRA_ONGKOS_PETIK= "ongkos_petik"
        const val EXTRA_ONGKOS_CUCI = "ongkos_cuci"
        const val EXTRA_PROSES = "extra_proses"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_hasil_beli)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Beli Dari Kebun Lain"
        }

        //Ambil data yang dikirim page InputPanen
        val tgl = intent.getStringExtra(EXTRA_TGL)
        val varietas = intent.getStringExtra(EXTRA_VARIETAS)
        val blok = intent.getStringExtra(EXTRA_BLOK)
        val berat = intent.getStringExtra(EXTRA_BERAT)
        val kolektif = intent.getStringExtra(EXTRA_KOLEKTIF)
        val biaya = intent.getStringExtra(EXTRA_BIAYA)
        val ongkosPetik = intent.getStringExtra(EXTRA_ONGKOS_PETIK)
        val ongkosCuci = intent.getStringExtra(EXTRA_ONGKOS_CUCI)
        val proses = intent.getStringExtra(EXTRA_PROSES)

        //Set ke value
        value_tanggal_beli.text = tgl
        value_varietas_beli.text = varietas
        value_blok_beli.text = blok
        value_berat_beli.text = berat
        value_total_biaya_beli.text = biaya
        value_kolektif_beli.text = kolektif
        value_harga_beli.text = ongkosPetik
        value_ongkos_cuci_beli.text = ongkosCuci
        value_proses_beli.text = proses

        btn_kembali_dashboard_beli.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_kembali_dashboard_beli) {
            val intent = Intent(this@ReviewHasilBeli, Dashboard::class.java)
            startActivity(intent)
            finish()
        }
    }
}
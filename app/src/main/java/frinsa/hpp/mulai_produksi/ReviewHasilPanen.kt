package frinsa.hpp.mulai_produksi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import frinsa.hpp.Dashboard
import frinsa.hpp.R
import frinsa.hpp.data.Produk
import kotlinx.android.synthetic.main.activity_review_hasil_panen.*

class ReviewHasilPanen : AppCompatActivity(), View.OnClickListener {
    val produk: Produk = Produk(this)

    companion object {
        const val EXTRA_TGL  = "extra_tgl"
        const val EXTRA_VARIETAS = "extra_varietas"
        const val EXTRA_BLOK = "extra_blok"
        const val EXTRA_BERAT = "extra_berat"
        const val EXTRA_BIAYA = "extra_biaya"
        const val EXTRA_ONGKOS_PETIK= "ongkos_petik"
        const val EXTRA_OJEK = "ojek"
        const val EXTRA_ONGKOS_CUCI = "ongkos_cuci"
        const val EXTRA_PROSES = "extra_proses"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_hasil_panen)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Hasil Panen"
        }

        //Ambil data yang dikirim page InputPanen
        val tgl = intent.getStringExtra(EXTRA_TGL)
        val varietas = intent.getStringExtra(EXTRA_VARIETAS)
        val blok = intent.getStringExtra(EXTRA_BLOK)
        val berat = intent.getStringExtra(EXTRA_BERAT)
        val biaya = intent.getStringExtra(EXTRA_BIAYA)
        val ongkosPetik = intent.getStringExtra(EXTRA_ONGKOS_PETIK)
        val ojek = intent.getStringExtra(EXTRA_OJEK)
        val ongkosCuci = intent.getStringExtra(EXTRA_ONGKOS_CUCI)
        val proses = intent.getStringExtra(EXTRA_PROSES)

        //Set ke value
        value_tanggal.text = tgl
        value_varietas.text = varietas
        value_blok.text = blok
        value_berat.text = berat + " Kg"
        value_total_biaya.text = produk.formatRupiah(biaya!!.toDouble())
        value_ongkos_petik.text = produk.formatRupiah(ongkosPetik!!.toDouble())
        value_ojek.text = produk.formatRupiah(ojek!!.toDouble())
        value_ongkos_cuci.text = produk.formatRupiah(ongkosCuci!!.toDouble())
        value_proses.text = proses

        btn_kembali_dashboard.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_kembali_dashboard) {
            finish()
        }
    }
}
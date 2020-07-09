package frinsa.hpp.mulai_produksi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import frinsa.hpp.Dashboard
import frinsa.hpp.R
import frinsa.hpp.data.Produk
import kotlinx.android.synthetic.main.activity_review_hasil_beli.*

class ReviewHasilBeli : AppCompatActivity(), View.OnClickListener {
    val produk: Produk = Produk(this)

    companion object {
        const val EXTRA_TITLE = "beli"
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

        //Ambil data yang dikirim page InputPanen
        val title = intent.getStringExtra(EXTRA_TITLE)
        val tgl = intent.getStringExtra(EXTRA_TGL)
        val varietas = intent.getStringExtra(EXTRA_VARIETAS)
        val blok = intent.getStringExtra(EXTRA_BLOK)
        val berat = intent.getStringExtra(EXTRA_BERAT)
        val kolektif = intent.getStringExtra(EXTRA_KOLEKTIF)
        val biaya = intent.getStringExtra(EXTRA_BIAYA)
        val ongkosPetik = intent.getStringExtra(EXTRA_ONGKOS_PETIK)
        val ongkosCuci = intent.getStringExtra(EXTRA_ONGKOS_CUCI)
        val proses = intent.getStringExtra(EXTRA_PROSES)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Beli $title"
        }

        //Set ke value
        value_tanggal_beli.text = tgl
        value_varietas_beli.text = varietas
        value_blok_beli.text = blok
        value_berat_beli.text = berat + " Kg"
        value_total_biaya_beli.text = produk.formatRupiah(biaya!!.toDouble())
        value_kolektif_beli.text = kolektif
        value_harga_beli.text = produk.formatRupiah(ongkosPetik!!.toDouble())
        value_ongkos_cuci_beli.text = produk.formatRupiah(ongkosCuci!!.toDouble())
        value_proses_beli.text = proses

        btn_kembali_dashboard_beli.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_kembali_dashboard_beli) {
            finish()
        }
    }
}
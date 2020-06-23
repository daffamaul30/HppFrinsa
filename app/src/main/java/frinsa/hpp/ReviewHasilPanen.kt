package frinsa.hpp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_review_hasil_panen.*

class ReviewHasilPanen : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TGL  = "extra_tgl"
        const val EXTRA_VARIETAS = "extra_varietas"
        const val EXTRA_BLOK = "extra_blok"
        const val EXTRA_BERAT = "extra_berat"
        const val EXTRA_KOLEKTIF = "extra_kolektif"
        const val EXTRA_BIAYA = "extra_biaya"
        const val EXTRA_ONGKOS_PETIK= "ongkos_petik"
        const val EXTRA_OJEK = "ojek"
        const val EXTRA_ONGKOS_CUCI = "ongkos_cuci"
        const val EXTRA_PROSES = "extra_proses"
        const val EXTRA_KODE_KOLEKTIF = "isi nanti"
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
        val kolektif = intent.getStringExtra(EXTRA_KOLEKTIF)
        val biaya = intent.getStringExtra(EXTRA_BIAYA)
        val ongkosPetik = intent.getStringExtra(EXTRA_ONGKOS_PETIK)
        val ojek = intent.getStringExtra(EXTRA_OJEK)
        val ongkosCuci = intent.getStringExtra(EXTRA_ONGKOS_CUCI)
        val proses = intent.getStringExtra(EXTRA_PROSES)
        val kodeKolektif = intent.getStringExtra(EXTRA_KODE_KOLEKTIF)

        //Set ke value
        value_tanggal.text = tgl
        value_varietas.text = varietas
        value_blok.text = blok
        value_berat.text = berat
        value_kolektif.text = kolektif
        value_total_biaya.text = biaya
        value_ongkos_petik.text = ongkosPetik
        value_ojek.text = ojek
        value_ongkos_cuci.text = ongkosCuci
        value_proses.text = proses

        if (kodeKolektif == "aktif") {
            tv_ongkos_petik.text = "Harga Ceri"
        }

        btn_kembali_dashboard.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_kembali_dashboard) {
            val intent = Intent(this@ReviewHasilPanen, Dashboard::class.java)
            startActivity(intent)
            finish()
        }
    }
}
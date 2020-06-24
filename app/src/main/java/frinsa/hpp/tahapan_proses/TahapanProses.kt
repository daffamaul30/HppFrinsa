package frinsa.hpp.tahapan_proses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import frinsa.hpp.R

class TahapanProses : AppCompatActivity() {
    companion object{
        const val KODE_FRAG = "kode"
        const val TITLE = "title"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahapan_proses)
        val kode = intent.getStringExtra(KODE_FRAG)
        val judul = intent.getStringExtra(TITLE)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = ""
        }
    }
}
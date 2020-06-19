package frinsa.hpp.daftar_produksi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import frinsa.hpp.R
import kotlinx.android.synthetic.main.tab_layout_daftar_produksi.*

class MainDaftarProduksi : AppCompatActivity() {

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapterDaftarProduksi(supportFragmentManager)
        adapter.populateFragment(FragmentProses(),"Proses")
        adapter.populateFragment(FragmentSelesai(),"Selesai")
        viewPager.adapter = adapter
        tabDaftarProduksi.setupWithViewPager(viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_layout_daftar_produksi)
        setupViewPager(viewPagerDaftarProduksi)
    }

}
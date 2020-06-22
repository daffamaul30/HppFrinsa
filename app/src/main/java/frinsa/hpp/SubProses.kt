package frinsa.hpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import frinsa.hpp.daftar_produksi.DaftarProduksiAdapter
import frinsa.hpp.daftar_produksi.FragmentProses
import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import kotlinx.android.synthetic.main.fragment_produksi.*

class SubProses: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_proses)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "SubProses Kumulatif"
        }
        val DPlist = ModelDaftarProduksi(
            "27-04-2020",
            "A",
            "Arabica",
            10.0,
            "Full-Wash",
            2000000.00,
            "terakhir"
        )
        val dpList: MutableList<ModelDaftarProduksi> = ArrayList()
        dpList.add(DPlist)
        dpList.add(DPlist)
        dpList.add(DPlist)
        dpList.add(DPlist)
        dpList.add(DPlist)


        rv_produksi.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = SubProsesAdapter(this, dpList)
        rv_produksi.adapter = cardViewHeroAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        if (searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        return true
    }

}
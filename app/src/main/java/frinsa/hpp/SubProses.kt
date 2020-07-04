package frinsa.hpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.Produk
import kotlinx.android.synthetic.main.activity_sub_proses.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.tan

class SubProses: AppCompatActivity() {
//    private val context = this
    private lateinit var db : DBPanen
    private lateinit var produk : Produk
    val spList: MutableList<ModelDaftarProduksi> = ArrayList()
    val displayList: MutableList<ModelDaftarProduksi> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_proses)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Lanjutkan Produksi"
        }

        produk = Produk()
        db = DBPanen(this)
        val data = db.readPanen()
        println(data.size)
        data.forEach() {
            spList.add(
                ModelDaftarProduksi(
                    tanggal = it.petik?.tgl_petik,
                    blok = it.produksi?.blok,
                    varietas = it.produksi?.varietas,
                    berat = 0.0,
                    proses = it.produksi?.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi?.status
                )
            )
            displayList.addAll(spList)
        }

//        val DPlist = ModelDaftarProduksi(
//            "27-04-2020",
//            "A",
//            "Arabica",
//            10.0,
//            "Full-Wash",
//            2000000.00,
//            "Pulping"
//        )
//
//        val DPlist1 = ModelDaftarProduksi(
//            "22-01-2020",
//            "B",
//            "Luwak White",
//            12.0,
//            "Lact Fully Washed",
//            1000000.00,
//            "Hulling"
//        )
//        val DPlist2 = ModelDaftarProduksi(
//            "22-02-2020",
//            "C",
//            "Grande",
//            20.0,
//            "Wet Hulled",
//            2021000.00,
//            "Jemur"
//        )
//        val DPlist3 = ModelDaftarProduksi(
//            "11-03-2020",
//            "D",
//            "Mocca",
//            15.0,
//            "Natural",
//            2910000.00,
//            "Color Sorter"
//        )
//        spList.add(DPlist)
//        spList.add(DPlist1)
//        spList.add(DPlist2)
//        spList.add(DPlist3)
//        spList.add(DPlist)
//        displayList.addAll(spList)
        rv_hasil_produksi.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = SubProsesAdapter(this, displayList)
        rv_hasil_produksi.adapter = cardViewHeroAdapter
    }
//    private fun viewProses(){
//        val displayproses : Pair<MutableList<Panen>, MutableList<Cherry>> = data.getPanen()
//        val adapterproses = SubProsesAdapter (this, displayproses)
//    }
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


                    if (newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        spList.forEach(){
                            if (it.proses?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            if (it.tanggal?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            if (it.varietas?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            if (it.blok?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            if (it.tahap?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                        }
                        rv_hasil_produksi.adapter?.notifyDataSetChanged()
                    }else{
                        displayList.clear()
                        displayList.addAll(spList)
                        rv_hasil_produksi.adapter?.notifyDataSetChanged()
                    }

                    return true
                }
            })
        }
        return true
    }
}
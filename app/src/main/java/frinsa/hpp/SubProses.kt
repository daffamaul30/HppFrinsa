package frinsa.hpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.DBPanen
import kotlinx.android.synthetic.main.activity_sub_proses.*
import java.util.*
import kotlin.collections.ArrayList

class SubProses: AppCompatActivity() {
//    private val context = this
    private lateinit var data : DBPanen
    val spList: MutableList<ModelDaftarProduksi> = ArrayList()
    val displayList: MutableList<ModelDaftarProduksi> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_proses)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "SubProses Kumulatif"
        }

//        rv_hasil_produksi.layoutManager = LinearLayoutManager(this)
////        val cardViewHeroAdapter = SubProsesAdapter(this, FragmentProses.getDPList())
////        rv_hasil_produksi.adapter = cardViewHeroAdapter
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

        val DPlist1 = ModelDaftarProduksi(
            "27-04-2020",
            "A",
            "Arabica",
            10.0,
            "Lact Fully Washed",
            2000000.00,
            "terakhir"
        )
        val DPlist2 = ModelDaftarProduksi(
            "27-04-2020",
            "A",
            "Arabica",
            10.0,
            "Wet Hulled",
            2000000.00,
            "terakhir"
        )
        val DPlist3 = ModelDaftarProduksi(
            "27-04-2020",
            "A",
            "Arabica",
            10.0,
            "Natural",
            2000000.00,
            "terakhir"
        )
        spList.add(DPlist)
        spList.add(DPlist1)
        spList.add(DPlist2)
        spList.add(DPlist3)
        spList.add(DPlist)
        displayList.addAll(spList)
        data = DBPanen(this)
//        data.getPanen()
//        var proses : ArrayList() = data.getPanen()
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
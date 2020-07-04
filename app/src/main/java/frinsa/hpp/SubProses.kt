package frinsa.hpp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.Produk
import frinsa.hpp.tahapan_proses.TahapanProses
import kotlinx.android.synthetic.main.activity_sub_proses.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.tan

class SubProses: AppCompatActivity(), View.OnClickListener {
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

        btn_proses.setOnClickListener(this)

        produk = Produk()
        db = DBPanen(this)
        val data = db.readPanen()
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
        }
        displayList.addAll(spList)
        rv_hasil_produksi.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = SubProsesAdapter(this, displayList)
        rv_hasil_produksi.adapter = cardViewHeroAdapter
    }
//    private fun viewProses(){
//        val displayproses : Pair<MutableList<Panen>, MutableList<Cherry>> = data.getPanen()
//        val adapterproses = SubProsesAdapter (this, displayproses)
//    }

    private fun validation(): Pair<Boolean, String?> {
        var valid = true
        if (posisi.size > 1) {
            for (i in 0 until posisi.size) {
                if (i == posisi.size-1) {
                    break
                } else {
                    if ((spList.get(posisi.get(i)).proses == spList.get(posisi.get(i+1)).proses) || (spList.get(posisi.get(i)).tahap == spList.get(posisi.get(i+1)).tahap)) {
                        valid = false
                    }
                }
            }
            //MERGE DATANYA DI DATABASE
        }

        return Pair(valid,spList.get(posisi.get(0)).proses)
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


                    if (newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        spList.forEach(){
                            if (it.proses?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            else if (it.tanggal?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            else if (it.varietas?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            else if (it.blok?.toLowerCase(Locale.getDefault())!!.contains(search)){
                                displayList.add(it)
                            }
                            else if (it.tahap?.toLowerCase(Locale.getDefault())!!.contains(search)){
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

    fun getCode(): String {
        val list = it.split(".")
    }

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_proses -> {
                val (valid,name) = validation()
                Toast.makeText(this, valid.toString(), Toast.LENGTH_SHORT).show()

                if (valid) {
                    val step = name.toString()?.let { db.getStepProses(it) }
                    val kode =
//                    var stringBuilder = StringBuilder()
//                    posisi.forEach {
//                        stringBuilder.append(spList.get(it).proses).append(" ").append(spList.get(it).tahap).append("\n")
//                    }
//                    println(stringBuilder)
//                    Toast.makeText(this, stringBuilder, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SubProses, TahapanProses::class.java)
                    intent.putExtra(TahapanProses.KODE_FRAG, kode)
                    intent.putExtra(TahapanProses.TITLE, kode.capitalizeWords())
                }
            }
        }
    }
}
package frinsa.hpp


import android.graphics.Color

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

import frinsa.hpp.data.Produksi
import kotlinx.android.synthetic.main.activity_input_panen.*

import frinsa.hpp.tahapan_proses.TahapanProses

import kotlinx.android.synthetic.main.activity_sub_proses.*
import kotlinx.android.synthetic.main.cardviewproses.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.tan

class SubProses: AppCompatActivity(), View.OnClickListener {
//    private val context = this
    private lateinit var db : DBPanen
    private lateinit var produk : Produk
    val spList: MutableList<ModelDaftarProduksi> = ArrayList()
    val displayList: MutableList<ModelDaftarProduksi> = ArrayList()
    val listID: MutableList<Int> = ArrayList()
    var id: Int = 0
    var varietas = String()
    var Block = String()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_proses)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Lanjutkan Produksi"
        }

        btn_proses.setOnClickListener(this)

        produk = Produk()
        db = DBPanen(this)
        val data = db.getAllData()
        data.forEach() {
            spList.add(
                ModelDaftarProduksi(
                    id = it.produksi?.id_produksi,
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

    private fun validation(): Pair<Boolean, String?> {
        var valid = true
        id = spList.get(posisi.get(0)).id!!
        Block = spList.get(posisi.get(0)).blok!!
        varietas = spList.get(posisi.get(0)).varietas!!
        if (posisi.size > 1) {
            for (i in 0 until posisi.size) {
                spList.get(posisi.get(i)).id?.let { listID.add(it) }
                if (i == posisi.size-1) {
                    break
                } else {
                    if ((spList.get(posisi.get(i)).proses != spList.get(posisi.get(i+1)).proses) || (spList.get(posisi.get(i)).tahap != spList.get(posisi.get(i+1)).tahap)) {
                        valid = false
                    }
                }
            }
            //MERGE DATANYA DI DATABASE (UPDATE LAGI VAR ID NYA, VAR VARIETAS NYA, VAR BLOK NYA)
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

    fun getCode(step: String): String {
        val list = step.split(",")
        var current = list.indexOf(spList.get(0).tahap)
        var code = list.get(current+1)

        return code
    }

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_proses -> {
                val (valid,name) = validation()
                Toast.makeText(this, valid.toString(), Toast.LENGTH_SHORT).show()

                if (valid) {
                    val step = name.toString()?.let { db.getStepProses(it) }
                    val kode = getCode(step)
                    Toast.makeText(this, kode, Toast.LENGTH_SHORT).show()
//                    var stringBuilder = StringBuilder()
//                    posisi.forEach {
//                        stringBuilder.append(spList.get(it).proses).append(" ").append(spList.get(it).tahap).append("\n")
//                    }
//                    println(stringBuilder)
//                    Toast.makeText(this, stringBuilder, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SubProses, TahapanProses::class.java)
                    intent.putExtra(TahapanProses.KODE_FRAG, kode)
                    intent.putExtra(TahapanProses.TITLE, kode.capitalizeWords())
                    intent.putExtra(TahapanProses.ID, id)
                    intent.putExtra(TahapanProses.VARIETAS, varietas)
                    intent.putExtra(TahapanProses.BLOK, Block)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
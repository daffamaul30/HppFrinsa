package frinsa.hpp.lanjut_produksi


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import frinsa.hpp.R

import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.data.*

import frinsa.hpp.tahapan_proses.TahapanProses

import kotlinx.android.synthetic.main.activity_sub_proses.*
import kotlinx.android.synthetic.main.dialog_proses_isi_nanti.view.*
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.dialog_tmbh_varietas.view.*
import java.util.*
import kotlin.collections.ArrayList

class SubProses: AppCompatActivity(), View.OnClickListener {
//    private val context = this
    private lateinit var db : DBPanen
    private lateinit var produk : Produk
    private lateinit var pros : Proses
    val spList: MutableList<ModelDaftarProduksi> = ArrayList()
    val displayList: MutableList<ModelDaftarProduksi> = ArrayList()
    val listID: MutableList<Int> = ArrayList()
    var id: Int = 0
    var varietas: String = ""
    var Block: String = ""
    private var Berat: Double = 0.0
    private lateinit var spinProses: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_proses)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Lanjutkan Produksi"
        }

        btn_proses.setOnClickListener(this)

        produk = Produk(this)
        pros = Proses(this)
        db = DBPanen(this)
        val data = db.getAllData2("<>")
        data.forEach() {
            Berat = produk.getLastWeight(it)
            spList.add(
                ModelDaftarProduksi(
                    id = it.produksi.id_produksi,
                    tanggal = it.petik.tgl_petik,
                    blok = it.produksi.blok,
                    varietas = it.produksi.varietas,
                    berat = Berat,
                    proses = it.produksi.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi.status
                )
            )
        }
        displayList.addAll(spList)

        rv_hasil_produksi.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter =
            SubProsesAdapter(this, displayList)
        rv_hasil_produksi.adapter = cardViewHeroAdapter
    }

    private fun validation(): Pair<Boolean, String> {
        var proses = ""
        var valid = true
        if (posisi.size == 1) {
            proses = spList.get(posisi.get(0)).proses.toString()
            id = spList.get(posisi.get(0)).id!!
            Block = spList.get(posisi.get(0)).blok.toString()
            varietas = spList.get(posisi.get(0)).varietas.toString()
        }
        else if (posisi.size > 1) {
            proses = spList.get(posisi.get(0)).proses.toString()
            for (i in 0 until posisi.size) {
                spList.get(posisi.get(i)).id.let {
                    if (it != null) {
                        listID.add(it)
                    }
                }
                if (i == posisi.size-1) {
                    break
                } else {
                    if ((spList.get(posisi.get(i)).proses != spList.get(
                            posisi.get(i+1)).proses) || (spList.get(
                            posisi.get(i)).tahap != spList.get(
                            posisi.get(i+1)).tahap)) {
                        valid = false
                        Toast.makeText(this, "Pilih item dengan proses dan tahap yang sama", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        else {
            valid = false
            proses = ""
        }
        if (posisi.size > 1 && valid) {
            //MERGE DATANYA DI DATABASE (UPDATE LAGI VAR ID NYA, VAR VARIETAS NYA, VAR BLOK NYA)
            val merge = produk.mergeData(posisi, spList)
            id = merge.produksi.id_produksi
            Block = merge.produksi.blok
            varietas = merge.produksi.varietas
            proses = if (merge.produksi.proses == "") "-" else merge.produksi.proses
        }
        println("1" + proses)
        println(id)


//        Toast.makeText(this, proses, Toast.LENGTH_SHORT).show()
        return Pair(valid, proses)
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
        var current = list.indexOf(spList.get(posisi.get(0)).tahap)
//        Toast.makeText(this, list.get(current), Toast.LENGTH_SHORT).show()
        var code = list.get(current+1)

        return code
    }

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_proses -> {
                var (valid,name) = validation()
//                Toast.makeText(this, valid.toString(), Toast.LENGTH_SHORT).show()

                if (valid && name == "-") {
//                        pros.addProsesIsiNanti(id)
                    val dialog =
                        LayoutInflater.from(this).inflate(R.layout.dialog_proses_isi_nanti, null)
                    val builder = AlertDialog.Builder(this).setView(dialog)
                    val alertDialog = builder.create()
                    alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnim_Up_Down
                    alertDialog.show()
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    setSpinnerProses(dialog.spinner_proses_isi_nanti)

                    dialog.submit_proses_isi_nanti.setOnClickListener {

                        if (spinProses == "Pilih Proses") {
                            Toast.makeText(this, "Proses harus dipilih", Toast.LENGTH_SHORT).show()
                        } else {
                            db.updateProses(id, spinProses)
                            pindah(spinProses)
                            alertDialog.dismiss()
                        }
                    }
                    dialog.batal_proses_isi_nanti.setOnClickListener {
                        alertDialog.dismiss()
                    }
                }
                else if (valid && name != "-") {
                    pindah(name)
                }
            }
        }
    }

    fun pindah(name: String) {
        val step = db.getStepProses(name)
//                    Toast.makeText(this, step, Toast.LENGTH_SHORT).show()
        val kode = getCode(step)
//                    Toast.makeText(this, kode, Toast.LENGTH_SHORT).show()
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

    fun setSpinnerProses(A: Spinner) {
//        val spinnerProses: Spinner = findViewById(R.id.spinner_proses_isi_nanti)

        val proses = pros.getProses()
        //Style and populate the spinner
        val adapterProses = ArrayAdapter(this, android.R.layout.simple_spinner_item, proses)
        //Dropdown layout style
        adapterProses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        A.adapter = adapterProses

        A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinProses = parent.getItemAtPosition(position).toString()
                if (parent.getItemAtPosition(position) === "Pilih Proses" ) {
                    //
                } else {
                    Toast.makeText(parent.context, spinProses, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }
    }
}
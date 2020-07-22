package frinsa.hpp.daftar_produksi

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import frinsa.hpp.R
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.Produk
import kotlinx.android.synthetic.main.fragment_produksi.*
import kotlinx.android.synthetic.main.fragment_produksi_selesai.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentSelesai: Fragment(), View.OnClickListener {
    private lateinit var db : DBPanen
    private lateinit var produk : Produk
    val dpSList: MutableList<ModelDaftarProduksi> = ArrayList()
    val displaySList: MutableList<ModelDaftarProduksi> = ArrayList()
    val listID: MutableList<Int> = ArrayList()
    private var Berat: Double = 0.0

    lateinit var v : View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        v = inflater.inflate(R.layout.fragment_produksi_selesai,container,false)
        return v
    }

    //Load Data CardView di RecyclerView pada Fragment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        produk = Produk()
        db = DBPanen(requireContext())
        val data = db.getAllData2("=")
        data.forEach() {
            Berat = produk.getLastWeight(it)
            dpSList.add(
                ModelDaftarProduksi(
                    id = it.produksi?.id_produksi,
                    tanggal = it.petik?.tgl_petik,
                    blok = it.produksi?.blok,
                    varietas = it.produksi?.varietas,
                    berat = Berat,
                    proses = it.produksi?.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi?.status
                )
            )
        }
        displaySList.addAll(dpSList)

        rv_produksi_selesai.layoutManager = LinearLayoutManager(context)
        rv_produksi_selesai.setHasFixedSize(true)
        rv_produksi_selesai.adapter = DaftarProduksiAdapter(context,displaySList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        val searchView = SearchView((context as MainDaftarProduksi).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.menu_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()){
                    refreshDataS()
                    displaySList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    dpSList.forEach {
                        if (it.proses?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displaySList.add(it)
                        }
                        else if (it.tanggal?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displaySList.add(it)
                        }
                        else if (it.varietas?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displaySList.add(it)
                        }
                        else if (it.blok?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displaySList.add(it)
                        }
                        else if (it.tahap?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displaySList.add(it)
                        }
                    }
                    rv_produksi_selesai.adapter?.notifyDataSetChanged()
                }
                else{
//                    displaySList.clear()
//                    displaySList.addAll(dpSList)
                    refreshDataS()
                    rv_produksi_selesai.adapter?.notifyDataSetChanged()
                }
                return true
            }
        })
    }

    fun refreshDataS(){
        Log.e("DB","Refresh Data S")
        this.dpSList.clear()
        this.displaySList.clear()
        val data = db.getAllData2("=")
        data.forEach() {
            Berat = produk.getLastWeight(it)
            this.dpSList.add(
                ModelDaftarProduksi(
                    id = it.produksi?.id_produksi,
                    tanggal = it.petik?.tgl_petik,
                    blok = it.produksi?.blok,
                    varietas = it.produksi?.varietas,
                    berat = if (it.produksi.proses == "-") it.petik.berat else Berat,
                    proses = it.produksi?.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi?.status
                )
            )
        }
        displaySList.addAll(dpSList)
        rv_produksi_selesai.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        Log.e("DEBUG","onResume of fragment Selesai")
        super.onResume()
    }

    override fun onPause() {
        Log.e("DEBUG","onPause of fragment Selesai")
        super.onPause()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            Log.e("Visibilty Selesai","True")
        }else{
            Log.e("Visibilty Selesai","False")
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_dp_delete -> {

            }
        }
    }
}
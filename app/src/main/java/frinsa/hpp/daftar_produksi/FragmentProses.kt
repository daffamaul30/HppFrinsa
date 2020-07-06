package frinsa.hpp.daftar_produksi

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import frinsa.hpp.R
import frinsa.hpp.data.DBPanen
import frinsa.hpp.data.Produk
import kotlinx.android.synthetic.main.fragment_produksi.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentProses: Fragment() {
    private lateinit var db : DBPanen
    private lateinit var produk : Produk
    val dpPList: MutableList<ModelDaftarProduksi> = ArrayList()
    val displayPList: MutableList<ModelDaftarProduksi> = ArrayList()
    val listID: MutableList<Int> = ArrayList()
    private var Berat: Double = 0.0


    lateinit var v :View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        v = inflater.inflate(R.layout.fragment_produksi,container,false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        produk = Produk()
        db = DBPanen(requireContext())
        val data = db.getAllData()
        data.forEach() {
            Berat = produk.getLastWeight(it)
            dpPList.add(
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
        displayPList.addAll(dpPList)

        rv_produksi.layoutManager = LinearLayoutManager(context)
        rv_produksi.setHasFixedSize(true)
        rv_produksi.adapter = DaftarProduksiAdapter(context,displayPList)
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
                    displayPList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    dpPList.forEach {
                        if (it.proses?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displayPList.add(it)
                        }
                        else if (it.tanggal?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displayPList.add(it)
                        }
                        else if (it.varietas?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displayPList.add(it)
                        }
                        else if (it.blok?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displayPList.add(it)
                        }
                        else if (it.tahap?.toLowerCase(Locale.getDefault())!!.contains(search)){
                            displayPList.add(it)
                        }
                    }
                    rv_produksi.adapter?.notifyDataSetChanged()
                }
                else{
                    displayPList.clear()
                    displayPList.addAll(dpPList)
                    rv_produksi.adapter?.notifyDataSetChanged()
                }
                return true
            }
        })
    }

}
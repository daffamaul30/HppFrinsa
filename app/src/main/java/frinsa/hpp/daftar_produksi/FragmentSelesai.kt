package frinsa.hpp.daftar_produksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import frinsa.hpp.R
import kotlinx.android.synthetic.main.fragment_produksi.*

class FragmentSelesai: Fragment() {

    lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_produksi,container,false)
        return v
    }

    //Load Data CardView di RecyclerView pada Fragment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val DPlist = ModelDaftarProduksi(
            0,
            "27-04-2020",
            "A",
            "Arabica",
            10.0,
            "Full-Wash",
            2000000,
            "terakhir"
        )
        val dpList: MutableList<ModelDaftarProduksi> = ArrayList()
        dpList.add(DPlist)
        dpList.add(DPlist)
        dpList.add(DPlist)
        dpList.add(DPlist)
        dpList.add(DPlist)

        rv_produksi.layoutManager = LinearLayoutManager(context)
        rv_produksi.setHasFixedSize(true)
        rv_produksi.adapter = DaftarProduksiAdapter(context,dpList)
    }
}
package frinsa.hpp.daftar_produksi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapterDaftarProduksi(fm: FragmentManager?): FragmentPagerAdapter(fm!!) {

    override fun getItem(position: Int): Fragment{
        if (position==0){
            return FragmentProses()
        }else{
            return FragmentSelesai()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int) :String{
        if (position==0){
            return "Proses"
        }else{
            return "Selesai"
        }
    }
}
package frinsa.hpp.daftar_produksi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapterDaftarProduksi(fm: FragmentManager?): FragmentPagerAdapter(fm!!) {
    //var fragmentList = arrayListOf<Fragment>()
    //var titleList = arrayListOf<String>()

//    fun populateFragment(fragment: Fragment, title:String){
//        fragmentList.add(fragment)
//        titleList.add(title)
//    }

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
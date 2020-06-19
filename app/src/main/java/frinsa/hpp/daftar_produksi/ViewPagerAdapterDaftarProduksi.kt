package frinsa.hpp.daftar_produksi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapterDaftarProduksi(fm: FragmentManager?): FragmentPagerAdapter(fm!!) {
    var fragmentList = arrayListOf<Fragment>()
    var titleList = arrayListOf<String>()

    fun populateFragment(fragment: Fragment, title:String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]
}
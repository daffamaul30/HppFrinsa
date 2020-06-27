package frinsa.hpp.tahapan_proses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import frinsa.hpp.R

class TahapanProses : AppCompatActivity() {
    companion object{
        const val KODE_FRAG = "kode"
        const val TITLE = "title"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahapan_proses)
        val kode = intent.getStringExtra(KODE_FRAG)
//        val kode = "pulping1"
        val judul = intent.getStringExtra(TITLE)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = judul
        }
        when (kode) {
            "fermentasi" -> fermentasi()
            "transportasi" -> transportasi()
            "hulling" -> hulling()
            "suton grader" -> sutonGrader()
            "size grading" -> sizeGrading()
            "color sorter" -> colorSorter()
            "hand pick" -> handPick()
            "pulping Ceri-Gabah Basah" -> pulping1()
            "pulping" -> pulping2()
            "jemur Kadar Air" -> jemurKadarAir()
            "jemurI" -> jemurI()
            "jemurII" -> jemurII()
        }
    }

    fun fermentasi() {
        val fer = Fermentasi_Fragment()
        val fragFer: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragFer.replace(R.id.container_tahapan,fer)
        fragFer.commit()
    }

    fun transportasi() {
        val trans = Transportasi_Fragment()
        val fragTrans: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.container_tahapan,trans)
        fragTrans.commit()
    }

    fun hulling() {
        val hull = Hulling_Fragment()
        val fragHull: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragHull.replace(R.id.container_tahapan,hull)
        fragHull.commit()
    }

    fun sutonGrader() {
        val sg = SutonGrader_Fragment()
        val fragSG: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragSG.replace(R.id.container_tahapan,sg)
        fragSG.commit()
    }

    fun sizeGrading() {
        val sg2 = SizeGrading_Fragment()
        val fragSG2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragSG2.replace(R.id.container_tahapan,sg2)
        fragSG2.commit()
    }

    fun colorSorter() {
        val cs = ColorSorter_Fragment()
        val fragCS: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragCS.replace(R.id.container_tahapan,cs)
        fragCS.commit()

    }

    fun handPick() {
        val hp = HandPick_Fragment()
        val fragHP: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragHP.replace(R.id.container_tahapan,hp)
        fragHP.commit()
    }

    fun pulping1() {
        val pul1 = Pulping1_Fragment()
        val fragPul1: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragPul1.replace(R.id.container_tahapan,pul1)
        fragPul1.commit()
    }

    fun pulping2() {
        val pul2 = Pulping2_Fragment()
        val fragPul2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragPul2.replace(R.id.container_tahapan,pul2)
        fragPul2.commit()
    }

    fun jemurKadarAir() {
        val jemKA = jemurKadarAir_Fragment()
        val fragJemKA: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJemKA.replace(R.id.container_tahapan,jemKA)
        fragJemKA.commit()
    }

    fun jemurI() {
        val jem2 = JemurI_Fragment()
        val fragJem2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJem2.replace(R.id.container_tahapan,jem2)
        fragJem2.commit()
    }

    fun jemurII() {
        val jem3 = JemurII_Fragment()
        val fragJem3: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJem3.replace(R.id.container_tahapan,jem3)
        fragJem3.commit()
    }

}
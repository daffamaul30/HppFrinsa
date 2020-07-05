package frinsa.hpp.tahapan_proses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import frinsa.hpp.R

class TahapanProses : AppCompatActivity() {
    var id: Int = 0
    companion object{
        const val KODE_FRAG = "kode"
        const val TITLE = "title"
        const val ID = "id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahapan_proses)
        val kode = intent.getStringExtra(KODE_FRAG)
        val judul = intent.getStringExtra(TITLE)
        id = intent.getIntExtra(ID,-1)

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
        fer.idData = id
        val fragFer: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragFer.replace(R.id.container_tahapan,fer)
        fragFer.commit()
    }

    fun transportasi() {
        val trans = Transportasi_Fragment()
        trans.idData = id
        val fragTrans: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.container_tahapan,trans)
        fragTrans.commit()
    }

    fun hulling() {
        val hull = Hulling_Fragment()
        hull.idData = id
        val fragHull: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragHull.replace(R.id.container_tahapan,hull)
        fragHull.commit()
    }

    fun sutonGrader() {
        val sg = SutonGrader_Fragment()
        sg.idData = id
        val fragSG: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragSG.replace(R.id.container_tahapan,sg)
        fragSG.commit()
    }

    fun sizeGrading() {
        val sg2 = SizeGrading_Fragment()
        sg2.idData = id
        val fragSG2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragSG2.replace(R.id.container_tahapan,sg2)
        fragSG2.commit()
    }

    fun colorSorter() {
        val cs = ColorSorter_Fragment()
        cs.idData = id
        val fragCS: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragCS.replace(R.id.container_tahapan,cs)
        fragCS.commit()

    }

    fun handPick() {
        val hp = HandPick_Fragment()
        hp.idData = id
        val fragHP: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragHP.replace(R.id.container_tahapan,hp)
        fragHP.commit()
    }

    fun pulping1() {
        val pul1 = Pulping1_Fragment()
        pul1.idData = id
        val fragPul1: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragPul1.replace(R.id.container_tahapan,pul1)
        fragPul1.commit()
    }

    fun pulping2() {
        val pul2 = Pulping2_Fragment()
        pul2.idData = id
        val fragPul2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragPul2.replace(R.id.container_tahapan,pul2)
        fragPul2.commit()
    }

    fun jemurKadarAir() {
        val jemKA = jemurKadarAir_Fragment()
        jemKA.idData = id
        val fragJemKA: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJemKA.replace(R.id.container_tahapan,jemKA)
        fragJemKA.commit()
    }

    fun jemurI() {
        val jem2 = JemurI_Fragment()
        jem2.idData = id
        val fragJem2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJem2.replace(R.id.container_tahapan,jem2)
        fragJem2.commit()
    }

    fun jemurII() {
        val jem3 = JemurII_Fragment()
        jem3.idData = id
        val fragJem3: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJem3.replace(R.id.container_tahapan,jem3)
        fragJem3.commit()
    }

}
package frinsa.hpp.tahapan_proses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import frinsa.hpp.R



class TahapanProses : AppCompatActivity() {
    var id: Int = 0
    lateinit var blok: String
    lateinit var varietas: String
    companion object{
        const val KODE_FRAG = "kode"
        const val TITLE = "title"
        const val ID = "id"
        const val VARIETAS = "Varietas"
        const val BLOK = "Blok"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahapan_proses)
        val kode = intent.getStringExtra(KODE_FRAG)
        val judul = intent.getStringExtra(TITLE)
        id = intent.getIntExtra(ID,-1)
        blok = intent.getStringExtra(BLOK)
        varietas = intent.getStringExtra(VARIETAS)

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
        fer.Varietas = varietas
        fer.Blok = blok
        val fragFer: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragFer.replace(R.id.container_tahapan,fer)
        fragFer.commit()
    }

    fun transportasi() {
        val trans = Transportasi_Fragment()
        trans.idData = id
        trans.Varietas = varietas
        trans.Blok = blok
        val fragTrans: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.container_tahapan,trans)
        fragTrans.commit()
    }

    fun hulling() {
        val hull = Hulling_Fragment()
        hull.idData = id
        hull.Varietas = varietas
        hull.Blok = blok
        val fragHull: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragHull.replace(R.id.container_tahapan,hull)
        fragHull.commit()
    }

    fun sutonGrader() {
        val sg = SutonGrader_Fragment()
        sg.idData = id
        sg.Varietas = varietas
        sg.Blok = blok
        val fragSG: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragSG.replace(R.id.container_tahapan,sg)
        fragSG.commit()
    }

    fun sizeGrading() {
        val sg2 = SizeGrading_Fragment()
        sg2.idData = id
        sg2.Varietas = varietas
        sg2.Blok = blok
        val fragSG2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragSG2.replace(R.id.container_tahapan,sg2)
        fragSG2.commit()
    }

    fun colorSorter() {
        val cs = ColorSorter_Fragment()
        cs.idData = id
        cs.Varietas = varietas
        cs.Blok = blok
        val fragCS: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragCS.replace(R.id.container_tahapan,cs)
        fragCS.commit()

    }

    fun handPick() {
        val hp = HandPick_Fragment()
        hp.idData = id
        hp.Varietas = varietas
        hp.Blok = blok
        val fragHP: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragHP.replace(R.id.container_tahapan,hp)
        fragHP.commit()
    }

    fun pulping1() {
        val pul1 = Pulping1_Fragment()
        pul1.idData = id
        pul1.Varietas = varietas
        pul1.Blok = blok
        val fragPul1: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragPul1.replace(R.id.container_tahapan,pul1)
        fragPul1.commit()
    }

    fun pulping2() {
        val pul2 = Pulping2_Fragment()
        pul2.idData = id
        pul2.Varietas = varietas
        pul2.Blok = blok
        val fragPul2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragPul2.replace(R.id.container_tahapan,pul2)
        fragPul2.commit()
    }

    fun jemurKadarAir() {
        val jemKA = jemurKadarAir_Fragment()
        jemKA.idData = id
        jemKA.Varietas = varietas
        jemKA.Blok = blok
        val fragJemKA: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJemKA.replace(R.id.container_tahapan,jemKA)
        fragJemKA.commit()
    }

    fun jemurI() {
        val jem2 = JemurI_Fragment()
        jem2.idData = id
        jem2.Varietas = varietas
        jem2.Blok = blok
        val fragJem2: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJem2.replace(R.id.container_tahapan,jem2)
        fragJem2.commit()
    }

    fun jemurII() {
        val jem3 = JemurII_Fragment()
        jem3.idData = id
        jem3.Varietas = varietas
        jem3.Blok = blok
        val fragJem3: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragJem3.replace(R.id.container_tahapan,jem3)
        fragJem3.commit()
    }

}
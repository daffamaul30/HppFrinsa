package frinsa.hpp

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import frinsa.hpp.daftar_produksi.MainDaftarProduksi
import frinsa.hpp.data.Blok
import frinsa.hpp.data.Varietas
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.dialog_menu_mulai_produksi.view.*


class Dashboard : AppCompatActivity(), View.OnClickListener {
    private lateinit var mOpen: Animation
    private lateinit var mClose: Animation
    private lateinit var mCross: Animation
    private lateinit var mNormal: Animation

    private var isOpen: Boolean = false

    private lateinit var vari: Varietas
    private lateinit var blk: Blok
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        vari = Varietas(context)
        blk = Blok(context)

        mOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        mClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        mCross = AnimationUtils.loadAnimation(this, R.anim.cross)
        mNormal = AnimationUtils.loadAnimation(this, R.anim.normal)

        card_daftar.setOnClickListener(this)
        card_mulai.setOnClickListener(this)
        card_lanjut.setOnClickListener(this)
        floatingActionButton.setOnClickListener(this)

//        card_mulai.setOnTouchListener(this)
    }

    private fun close() {
        varietas_baru.animation = mClose
        blok_baru.animation = mClose
        proses_baru.animation = mClose
//                    floatingActionButton.animation = mNormal
        floatingActionButton.setImageResource(R.drawable.ic_baseline_add_24)


        tv_varietas_baru.visibility = View.INVISIBLE
        tv_blok_baru.visibility = View.INVISIBLE
        tv_proses_baru.visibility = View.INVISIBLE

        isOpen = false
    }

    private fun open() {
        varietas_baru.animation = mOpen
        blok_baru.animation = mOpen
        proses_baru.animation = mOpen
//                    floatingActionButton.animation = mCross
        floatingActionButton.setImageResource(R.drawable.ic_baseline_close_24)

        tv_varietas_baru.visibility = View.VISIBLE
        tv_blok_baru.visibility = View.VISIBLE
        tv_proses_baru.visibility = View.VISIBLE

        isOpen = true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_mulai -> {
                val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_menu_mulai_produksi, null)
                val builder = AlertDialog.Builder(this).setView(dialog)
                val alertDialog =  builder.show()
                alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.card_panen_sendiri.setOnClickListener{
                    val intent = Intent(this@Dashboard, InputPanen::class.java)
                    startActivity(intent)
                    alertDialog.dismiss()
                }
                dialog.card_beli.setOnClickListener{
                    val intent = Intent(this@Dashboard, InputBeli::class.java)
                    startActivity(intent)
                    alertDialog.dismiss()
                }
            }
            R.id.card_daftar -> {
                val intent = Intent(this@Dashboard, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
            R.id.card_lanjut -> {
                val intent = Intent(this@Dashboard, SubProses::class.java)
//                val intent = Intent(this@Dashboard, TahapanProses::class.java)
                startActivity(intent)
            }
            R.id.floatingActionButton -> {
                if (isOpen) {
                    close()
                } else {
                    open()
                    varietas_baru.setOnClickListener{
                        vari.addVarietas()
                    }
                    blok_baru.setOnClickListener {
                        blk.addBlok()
                    }

                }
            }
        }
    }

}
package frinsa.hpp

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.kishan.askpermission.AskPermission
import com.kishan.askpermission.ErrorCallback
import com.kishan.askpermission.PermissionCallback
import com.kishan.askpermission.PermissionInterface
import frinsa.hpp.daftar_produksi.MainDaftarProduksi
import frinsa.hpp.data.Blok
import frinsa.hpp.data.Varietas
import frinsa.hpp.data.writeExcel
import frinsa.hpp.lanjut_produksi.SubProses
import frinsa.hpp.mulai_produksi.InputBeli
import frinsa.hpp.mulai_produksi.InputPanen
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_input_beli.*
import kotlinx.android.synthetic.main.detail_biaya.*
import kotlinx.android.synthetic.main.dialog_menu_mulai_produksi.view.*
import kotlinx.android.synthetic.main.dialog_set_tanggal_export.*
import kotlinx.android.synthetic.main.dialog_set_tanggal_export.view.*
import kotlinx.android.synthetic.main.dialog_submit.view.*
import kotlinx.android.synthetic.main.dialog_submit_exit.view.*
import java.text.SimpleDateFormat
import java.util.*


class Dashboard : AppCompatActivity(), View.OnClickListener, PermissionCallback, ErrorCallback {
    private lateinit var mOpen: Animation
    private lateinit var mClose: Animation
    private lateinit var mRotate1: Animation
    private lateinit var mRotate2: Animation

    private val dateFormat = SimpleDateFormat("yyy/MM/dd", Locale.ROOT)

    private var isOpen: Boolean = false
    private lateinit var excel: writeExcel
    private lateinit var vari: Varietas
    private lateinit var blk: Blok
    private val context = this
    private val REQUEST_PERMISSIONS = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        //

        //
        reqPermission()
        vari = Varietas(context)
        blk = Blok(context)
        excel = writeExcel(context)
        
        mOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        mClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        mRotate1 = AnimationUtils.loadAnimation(this, R.anim.rotate1)
        mRotate2 = AnimationUtils.loadAnimation(this, R.anim.rotate2)

        card_daftar.setOnClickListener(this)
        card_mulai.setOnClickListener(this)
        card_lanjut.setOnClickListener(this)
        floatingActionButton.setOnClickListener(this)
    }

    private fun close() {
        varietas_baru.startAnimation(mClose)
        blok_baru.startAnimation(mClose)
        proses_baru.startAnimation(mClose)
        export_btn.startAnimation(mClose)
        tv_varietas_baru.startAnimation(mClose)
        tv_blok_baru.startAnimation(mClose)
        tv_proses_baru.startAnimation(mClose)
        tv_export.startAnimation(mClose)
        floatingActionButton.startAnimation(mRotate2)

        varietas_baru.isClickable = false
        blok_baru.isClickable = false
        proses_baru.isClickable = false
        //import_btn.isClickable = false
        export_btn.isClickable = false

        isOpen = false
    }

    private fun open() {
        varietas_baru.startAnimation(mOpen)
        blok_baru.startAnimation(mOpen)
        proses_baru.startAnimation(mOpen)
        export_btn.startAnimation(mOpen)
        tv_varietas_baru.startAnimation(mOpen)
        tv_blok_baru.startAnimation(mOpen)
        tv_proses_baru.startAnimation(mOpen)
        tv_export.startAnimation(mOpen)
        floatingActionButton.startAnimation(mRotate1)

        isOpen = true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_mulai -> {
                if (isOpen) {
                    close()
                }
                val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_menu_mulai_produksi, null)
                val builder = AlertDialog.Builder(this).setView(dialog)
                val alertDialog =  builder.create()
                alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnim
                alertDialog.show()
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
                if (isOpen) {
                    close()
                }
                val intent = Intent(this@Dashboard, MainDaftarProduksi::class.java)
                startActivity(intent)
            }
            R.id.card_lanjut -> {
                if (isOpen) {
                    close()
                }
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
                    proses_baru.setOnClickListener {
                        val intent = Intent(this@Dashboard, TambahProses::class.java)
                        startActivity(intent)
                        close()
                    }
                    export_btn.setOnClickListener {
                        val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_set_tanggal_export, null)
                        val builder = AlertDialog.Builder(this).setView(dialog)
                        val alertDialog =  builder.create()
                        alertDialog.window?.attributes?.windowAnimations =
                            R.style.DialogAnim_Fade
                        alertDialog.show()
                        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                        dialog.tgl_dari.setOnClickListener(View.OnClickListener {
                            dialogTgl(dialog.tgl_dari)
                        })
                        dialog.tgl_sampai.setOnClickListener(View.OnClickListener {
                            dialogTgl(dialog.tgl_sampai)
                        })

                        dialog.submit_atur_tanggal_export.setOnClickListener {
                            var valid = true
                            if (dialog.tgl_dari.text.isEmpty()) {
                                valid = false
                                dialog.tgl_dari.error = "Harud diisi!"
                            }
                            if (dialog.tgl_sampai.text.isEmpty()) {
                                valid = false
                                dialog.tgl_sampai.error = "Harud diisi!"
                            }
                            if (dialog.tgl_dari.text.toString() > dialog.tgl_sampai.text.toString()) {
                                valid = false
                                Toast.makeText(this, "Tanggal awal harus sebelum tanggal akhir", Toast.LENGTH_SHORT).show()
                            }
                            if (valid) {
                                ekspor(dialog.tgl_dari.text.toString(), dialog.tgl_sampai.text.toString())

                                alertDialog.dismiss()
                            }

                        }
                        dialog.batal_atur_tanggal_export.setOnClickListener{
                            alertDialog.dismiss()
                        }
                    }
                }
            }
        }
    }

    fun dialogTgl(id: EditText) {
        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                now.set(Calendar.YEAR, year)
                now.set(Calendar.MONTH, month)
                now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                id.setText(dateFormat.format(now.time))
            },
            now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    fun ekspor(d1: String, d2: String) {
//        Toast.makeText(this, d1, Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, d2, Toast.LENGTH_SHORT).show()
        val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_submit, null)
        val builder = AlertDialog.Builder(this).setView(dialog)
        dialog.tv_submit.text = "Apakah rentang tanggal sudah benar?"
        val alertDialog =  builder.create()
        alertDialog.window?.attributes?.windowAnimations =
            R.style.DialogAnim_Fade
        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.submit_submit.setOnClickListener {
            excel.export(d1, d2)

            alertDialog.dismiss()
        }
        dialog.batal_submit.setOnClickListener{
            alertDialog.dismiss()
        }
    }


    override fun onBackPressed() {
//        super.onBackPressed()
        val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_submit_exit, null)
        val builder = AlertDialog.Builder(this).setView(dialog)
        val alertDialog =  builder.create()
        alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnim_Fade
        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
        dialog.submit_submit_exit.setOnClickListener{
            finish()
            alertDialog.dismiss()
        }
        dialog.batal_submit_exit.setOnClickListener{
            alertDialog.dismiss()
        }
    }
    fun reqPermission() {

        AskPermission.Builder(this).setPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .setCallback(this)
            .setErrorCallback(this)
            .request(REQUEST_PERMISSIONS)
    }

    override fun onShowSettings(
        permissionInterface: PermissionInterface,
        requestCode: Int
    ) {
        val builder =
            androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setMessage("We need permissions for this app. Open setting screen?")
        builder.setPositiveButton(
            "oke"
        ) { dialog, which -> permissionInterface.onSettingsShown() }
        builder.setNegativeButton("cancel", null)
        builder.show()
    }

    override fun onShowRationalDialog(
        permissionInterface: PermissionInterface,
        requestCode: Int
    ) {
        val builder =
            androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setMessage("Permission dibutuhkan untuk membuat data excel")
        builder.setPositiveButton(
            "oke"
        ) { dialog, which -> permissionInterface.onDialogShown() }
        builder.setNegativeButton("cancel", null)
        builder.show()
    }

    override fun onPermissionsGranted(requestCode: Int) {
//        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionsDenied(requestCode: Int) {
//        Toast.makeText(this, "Permissions Denied.", Toast.LENGTH_LONG).show()
    }

}
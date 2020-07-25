package frinsa.hpp.data

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.opencsv.CSVWriter
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import frinsa.hpp.daftar_produksi.ModelDaftarProduksi
import frinsa.hpp.lanjut_produksi.spList
import java.io.FileWriter
import java.io.IOException
import java.sql.Timestamp

import java.util.*

class writeExcel(val context: Context?) {
    private lateinit var db : DBPanen
    val spList: MutableList<ModelDaftarProduksi> = ArrayList()
    val spList2: MutableList<ModelDaftarProduksi> = ArrayList()
    private lateinit var produk : Produk
    private var Berat: Double = 0.0

    fun export(date1: String,date2: String){
        db = DBPanen(this.context!!)
        produk = Produk(this.context!!)
        var data = db.getAllByDate(date1,date2)
        data.forEach() {
            Berat = produk.getLastWeight(it)
            spList.add(
                ModelDaftarProduksi(
                    id = it.produksi.id_produksi,
                    tanggal = it.petik.tgl_petik,
                    blok = it.produksi.blok,
                    varietas = it.produksi.varietas,
                    berat = if (it.produksi.proses == "-") it.petik.berat else Berat,
                    proses = it.produksi.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi.status
                )
            )

        }
        val CSV_HEADER = arrayOf<String>("id", "tanggal", "blok", "varietas","berat","proses","biaya","tahap","status")
        var fileWriter: FileWriter? = null
        var csvWriter: CSVWriter? = null

        var dir = Environment.getExternalStorageDirectory().toString()+"/hppfrinsa_"+date1+"_"+date2+".csv"
        println(dir)

        try {

            fileWriter = FileWriter(dir)

            // write String Array
            csvWriter = CSVWriter(fileWriter,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)
            val data = db.getAllData2("<>")
            csvWriter.writeNext(CSV_HEADER)

            for (x in spList) {
                val data = arrayOf<String>(
                    x.id!!.toString(),
                    x.tanggal!!,
                    x.blok!!,
                    x.varietas!!,
                    x.berat!!.toString(),
                    x.proses!!,
                    x.biaya!!.toString(),
                    x.tahap!!)

                csvWriter.writeNext(data)
            }
            println("Write CSV using CSVWriter successfully!")
            Toast.makeText(this.context!!, "Exported as "+dir, Toast.LENGTH_LONG).show()

        } catch (e: Exception) {
            println("Writing CSV error!")
            Toast.makeText(this.context!!, "EXPORT ERROR", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        } finally {
            try {
                fileWriter!!.flush()
                fileWriter.close()
                csvWriter!!.close()
            } catch (e: IOException) {
                println("Flushing/closing error!")
                e.printStackTrace()
            }
        }
    }

    fun export(){
        db = DBPanen(this.context!!)
        produk = Produk(this.context!!)
        var data = db.getAllData2("<>")
        println(data)
        data.forEach() {
            Berat = produk.getLastWeight(it)
            spList.add(
                ModelDaftarProduksi(
                    id = it.produksi.id_produksi,
                    tanggal = it.petik.tgl_petik,
                    blok = it.produksi.blok,
                    varietas = it.produksi.varietas,
                    berat = if (it.produksi.proses == "-") it.petik.berat else Berat,
                    proses = it.produksi.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi.status
                )
            )

        }
        data = db.getAllData2("=")
        data.forEach() {
            Berat = produk.getLastWeight(it)
            spList2.add(
                ModelDaftarProduksi(
                    id = it.produksi.id_produksi,
                    tanggal = it.petik.tgl_petik,
                    blok = it.produksi.blok,
                    varietas = it.produksi.varietas,
                    berat = if (it.produksi.proses == "-") it.petik.berat else Berat,
                    proses = it.produksi.proses,
                    biaya = produk.getTotalBiaya(it),
                    tahap = it.produksi.status
                )
            )

        }


        val CSV_HEADER = arrayOf<String>("id", "tanggal", "blok", "varietas","berat","proses","biaya","tahap","status")
        var fileWriter: FileWriter? = null
        var csvWriter: CSVWriter? = null
        var waktu = Timestamp(System.currentTimeMillis()).toString().substring(0,Timestamp(System.currentTimeMillis()).toString().length-7)
        waktu = waktu.replace(':',' ')
        println(waktu)
        var dir = Environment.getExternalStorageDirectory().toString()+"/hppfrinsa_"+waktu+".csv"
        println(dir)

        try {

            fileWriter = FileWriter(dir)

            // write String Array
            csvWriter = CSVWriter(fileWriter,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)
            val data = db.getAllData2("<>")
            csvWriter.writeNext(CSV_HEADER)

            for (x in spList) {
                val data = arrayOf<String>(
                    x.id!!.toString(),
                    x.tanggal!!,
                    x.blok!!,
                    x.varietas!!,
                    x.berat!!.toString(),
                    x.proses!!,
                    x.biaya!!.toString(),
                    x.tahap!!,
                    "not complete")

                csvWriter.writeNext(data)
            }
            for (x in spList2) {
                val data = arrayOf<String>(
                    x.id!!.toString(),
                    x.tanggal!!,
                    x.blok!!,
                    x.varietas!!,
                    x.berat!!.toString(),
                    x.proses!!,
                    x.biaya!!.toString(),
                    x.tahap!!,
                    "completed")

                csvWriter.writeNext(data)
            }

            println("Write CSV using CSVWriter successfully!")
            Toast.makeText(this.context!!, "Exported as "+dir, Toast.LENGTH_LONG).show()

        } catch (e: Exception) {
            println("Writing CSV error!")
            Toast.makeText(this.context!!, "EXPORT ERROR", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        } finally {
            try {
                fileWriter!!.flush()
                fileWriter.close()
                csvWriter!!.close()
            } catch (e: IOException) {
                println("Flushing/closing error!")
                e.printStackTrace()
            }
        }
    }
}

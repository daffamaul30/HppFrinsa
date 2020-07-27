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
import io.github.rybalkinsd.kohttp.dsl.httpPost
import io.github.rybalkinsd.kohttp.ext.url
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.sql.Timestamp

import java.util.*

class writeExcel(val context: Context?) {
    private lateinit var db : DBPanen
    private lateinit var produk : Produk
    private var Berat: Double = 0.0
    val CSV_HEADER = arrayOf<String>("ID", "TANGGAL PETIK","SUMBER","BELI DARI", "BLOK", "VARIETAS","TOTAL BERAT","PROSES","TOTAL BIAYA","TAHAP",
        "BERAT(PETIK)","BIAYA(PETIK)","BIAYA OJEK(PETIK)","BIAYA CUCI(PETIK)",
        "TANGGAL(FERMENTASI)","BERAT(FERMENTASI)","BIAYA(FERMENTASI)","BIAYA MUAT(FERMENTASI)",
        "TANGGAL(TRANSPORTASI)","BERAT(TRANSPORTASI)","BIAYA BONGKAR(TRANSPORTASI)","BIAYA KAWAL(TRANSPORTASI)","BIAYA TRANSPORT(TRANSPORTASI)",
        "TANGGAL(PULPING1)","BERAT(PULPING1)","BIAYA JEMUR(PULPING1)","BIAYA(PULPING1)",
        "TANGGAL(PULPING2)","BERAT(PULPING2)","BIAYA(PULPING2)",
        "TANGGAL(JEMUR KADAR AIR)","KADAR AIR","BERAT(JEMUR KADAR AIR)","BIAYA(JEMUR KADAR AIR)",
        "TANGGAL(JEMUR1)","BERAT(JEMUR1)","BIAYA(JEMUR1)",
        "TANGGAL(JEMUR2)","BERAT(JEMUR2)","BIAYA(JEMUR2)",
        "TANGGAL(HULLING)","BERAT(HULLING)","BIAYA(HULLING)","KADAR AIR(HULLING)",
        "TANGGAL(SUTON GRADER)","BERAT(SUTON GRADER)","BIAYA(SUTON GRADER)",
        "TANGGAL(SIZE GRADING)","BERAT(SIZE GRADING)","BIAYA(SIZE GRADING)",
        "TANGGAL(COLOR SORTER)","BERAT(COLOR SORTER)","BIAYA(COLOR SORTER)",
        "TANGGAL(HANDPICK)","BERAT(HANDPICK)","BIAYA(HANDPICK)"
    )

    fun export(date1: String,date2: String){
        db = DBPanen(this.context!!)
        produk = Produk(this.context!!)
        var data = db.getAllByDate(date1,date2)
//        var data = db.getAllData2("<>")
        val d1 = date1.replace("/", "-")
        val d2 = date2.replace("/", "-")

        var fileWriter: FileWriter? = null
        var csvWriter: CSVWriter? = null

        var dir = Environment.getExternalStorageDirectory().toString()+"/hppfrinsa_"+d1+"_"+d2+".csv"
        println(dir)

        try {

            fileWriter = FileWriter(dir)

            // write String Array
            csvWriter = CSVWriter(fileWriter,
                CSVWriter.DEFAULT_SEPARATOR,
//                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)
            csvWriter.writeNext(CSV_HEADER)

            data.forEach() {
                Berat = produk.getLastWeight(it)
                val berat = if (it.produksi.proses == "-") it.petik.berat else Berat
                val x = arrayOf<String>(
                    it.produksi.id_produksi.toString(),
                    it.petik.tgl_petik,
                    it.produksi.sumber,
                    it.produksi.beli_dari,
                    it.produksi.blok,
                    it.produksi.varietas,
                    berat.toString()+" KG",
                    it.produksi.proses,
                    "Rp. "+produk.getTotalBiaya(it).toString(),
                    it.produksi.status,
                    //petik
                    it.petik.berat.toString()+" KG",
                    "Rp. "+it.petik.biaya_petik.toString(),
                    "Rp. "+it.petik.biaya_ojek.toString(),
                    "Rp. "+it.petik.biaya_cuci.toString(),
                    //fermentasi
                    it.fermentasi.tanggal,
                    it.fermentasi.berat.toString()+" KG",
                    "Rp. "+it.fermentasi.biaya_fermentasi.toString(),
                    "Rp. "+it.fermentasi.biaya_muat.toString(),
                    //transportasi
                    it.transport.tanggal,
                    it.transport.berat.toString()+" KG",
                    "Rp. "+it.transport.biaya_bongkar.toString(),
                    "Rp. "+it.transport.biaya_kawal.toString(),
                    "Rp. "+it.transport.biaya_transport.toString(),
                    //PULPING1
                    it.pulping1.tanggal,
                    it.pulping1.berat.toString()+" KG",
                    "Rp. "+it.pulping1.biaya_jemur.toString(),
                    "Rp. "+it.pulping1.biaya_pulping.toString(),
                    //PULPING2
                    it.pulping2.tanggal,
                    it.pulping2.berat.toString()+" KG",
                    "Rp. "+it.pulping2.biaya.toString(),
                    //JEMUR KADAR AIR
                    it.jemurKadarAir.tanggal,
                    it.jemurKadarAir.kadarAir.toString(),
                    it.jemurKadarAir.berat.toString()+" KG",
                    "Rp. "+it.jemurKadarAir.biaya.toString(),
                    //JEMUR1
                    it.jemur1.tanggal,
                    it.jemur1.berat.toString()+" KG",
                    "Rp. "+it.jemur1.biaya.toString(),
                    //JEMUR2
                    it.jemur2.tanggal,
                    it.jemur2.berat.toString()+" KG",
                    "Rp. "+it.jemur2.biaya.toString(),
                    //hulling
                    it.hulling.tanggal,
                    it.hulling.berat.toString()+" KG",
                    "Rp."+ it.hulling.biaya.toString(),
                    it.hulling.kadarAir.toString(),
                    //suton grader
                    it.sutonGrader.tanggal,
                    it.sutonGrader.berat.toString()+" KG",
                    "Rp. "+it.sutonGrader.biaya.toString(),
                    //size grading
                    it.sizeGrading.tanggal,
                    it.sizeGrading.berat.toString()+" KG",
                    "Rp. "+it.sizeGrading.biaya.toString(),
                    //colorsorter
                    it.colorSorter.tanggal,
                    it.colorSorter.berat.toString()+" KG",
                    "Rp. "+it.colorSorter.biaya.toString(),
                    //handpick
                    it.handPick.tanggal,
                    it.handPick.berat.toString()+" KG",
                    "Rp. "+it.handPick.biaya.toString()

                )

                csvWriter.writeNext(x)
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
    fun backupToDrive(myfile: File){
        val token = "ya29.a0AfH6SMAROkES_d6dJwm_Xo18qf3IL-U0VUIu64c_vaFj9XBv4dmOIqFDFbY2Ydsf3d8JifJVozTQI4lz5iAYMmnIuqzOIPI_AzqBkieJ882jUvJm88KgAt5Ivd7kM1LtQ4bAu-SXTSzvM5FzcOzAiIFamkuUq82Qjso"
         //val yourFile = file
        val fileUri = this.javaClass.getResource(myfile.toString())?.toURI()

        println("UPLOAD START")
        val response : Response = httpPost {
            url("https://www.googleapis.com/upload/drive/v3/files?uploadType=media")
            //host="www.googleapis.com"
            //path="/upload/drive/v3/files?uploadType=media"
           header {
                "Authorization" to "Bearer $token"

            }

            body("application/json") {
                string("""{"login":"user","email":"john.doe@gmail.com"}""")
            }
        }
        println(response)
        println("UPLOAD SUCCESS")

    }
    fun export(){
        db = DBPanen(this.context!!)
        produk = Produk(this.context!!)
        var data = db.getAllData2("<>")


        var fileWriter: FileWriter? = null
        var csvWriter: CSVWriter? = null
        var waktu = Timestamp(System.currentTimeMillis()).toString().substring(0,Timestamp(System.currentTimeMillis()).toString().length-7)
        waktu = waktu.replace(':',' ')
        var dir = Environment.getExternalStorageDirectory().toString()+"/hppfrinsa_"+waktu+".csv"

        try {

            fileWriter = FileWriter(dir)

            // write String Array
            csvWriter = CSVWriter(fileWriter,
                CSVWriter.DEFAULT_SEPARATOR,
//                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)
            csvWriter.writeNext(CSV_HEADER)

            data.forEach() {
                Berat = produk.getLastWeight(it)
                val berat = if (it.produksi.proses == "-") it.petik.berat else Berat
                val x = arrayOf<String>(
                    it.produksi.id_produksi.toString(),
                    it.petik.tgl_petik,
                    it.produksi.sumber,
                    it.produksi.beli_dari,
                    it.produksi.blok,
                    it.produksi.varietas,
                    berat.toString()+" KG",
                    it.produksi.proses,
                    "Rp. "+produk.getTotalBiaya(it).toString(),
                    it.produksi.status,
                    //petik
                    it.petik.berat.toString()+" KG",
                    "Rp. "+it.petik.biaya_petik.toString(),
                    "Rp. "+it.petik.biaya_ojek.toString(),
                    "Rp. "+it.petik.biaya_cuci.toString(),
                    //fermentasi
                    it.fermentasi.tanggal,
                    it.fermentasi.berat.toString()+" KG",
                    "Rp. "+it.fermentasi.biaya_fermentasi.toString(),
                    "Rp. "+it.fermentasi.biaya_muat.toString(),
                    //transportasi
                    it.transport.tanggal,
                    it.transport.berat.toString()+" KG",
                    "Rp. "+it.transport.biaya_bongkar.toString(),
                    "Rp. "+it.transport.biaya_kawal.toString(),
                    "Rp. "+it.transport.biaya_transport.toString(),
                    //PULPING1
                    it.pulping1.tanggal,
                    it.pulping1.berat.toString()+" KG",
                    "Rp. "+it.pulping1.biaya_jemur.toString(),
                    "Rp. "+it.pulping1.biaya_pulping.toString(),
                    //PULPING2
                    it.pulping2.tanggal,
                    it.pulping2.berat.toString()+" KG",
                    "Rp. "+it.pulping2.biaya.toString(),
                    //JEMUR KADAR AIR
                    it.jemurKadarAir.tanggal,
                    it.jemurKadarAir.kadarAir.toString(),
                    it.jemurKadarAir.berat.toString()+" KG",
                    "Rp. "+it.jemurKadarAir.biaya.toString(),
                    //JEMUR1
                    it.jemur1.tanggal,
                    it.jemur1.berat.toString()+" KG",
                    "Rp. "+it.jemur1.biaya.toString(),
                    //JEMUR2
                    it.jemur2.tanggal,
                    it.jemur2.berat.toString()+" KG",
                    "Rp. "+it.jemur2.biaya.toString(),
                    //hulling
                    it.hulling.tanggal,
                    it.hulling.berat.toString()+" KG",
                    "Rp."+ it.hulling.biaya.toString(),
                    it.hulling.kadarAir.toString(),
                    //suton grader
                    it.sutonGrader.tanggal,
                    it.sutonGrader.berat.toString()+" KG",
                    "Rp. "+it.sutonGrader.biaya.toString(),
                    //size grading
                    it.sizeGrading.tanggal,
                    it.sizeGrading.berat.toString()+" KG",
                    "Rp. "+it.sizeGrading.biaya.toString(),
                    //colorsorter
                    it.colorSorter.tanggal,
                    it.colorSorter.berat.toString()+" KG",
                    "Rp. "+it.colorSorter.biaya.toString(),
                    //handpick
                    it.handPick.tanggal,
                    it.handPick.berat.toString()+" KG",
                    "Rp. "+it.handPick.biaya.toString()

                    )

                csvWriter.writeNext(x)
            }
            data = db.getAllData2("=")
            data.forEach() {
                Berat = produk.getLastWeight(it)
                val berat = if (it.produksi.proses == "-") it.petik.berat else Berat
                val x = arrayOf<String>(
                    it.produksi.id_produksi.toString(),
                    it.petik.tgl_petik,
                    it.produksi.sumber,
                    it.produksi.beli_dari,
                    it.produksi.blok,
                    it.produksi.varietas,
                    berat.toString()+" KG",
                    it.produksi.proses,
                    "Rp. "+produk.getTotalBiaya(it).toString(),
                    it.produksi.status,
                    //petik
                    it.petik.berat.toString()+" KG",
                    "Rp. "+it.petik.biaya_petik.toString(),
                    "Rp. "+it.petik.biaya_ojek.toString(),
                    "Rp. "+it.petik.biaya_cuci.toString(),
                    //fermentasi
                    it.fermentasi.tanggal,
                    it.fermentasi.berat.toString()+" KG",
                    "Rp. "+it.fermentasi.biaya_fermentasi.toString(),
                    "Rp. "+it.fermentasi.biaya_muat.toString(),
                    //transportasi
                    it.transport.tanggal,
                    it.transport.berat.toString()+" KG",
                    "Rp. "+it.transport.biaya_bongkar.toString(),
                    "Rp. "+it.transport.biaya_kawal.toString(),
                    "Rp. "+it.transport.biaya_transport.toString(),
                    //PULPING1
                    it.pulping1.tanggal,
                    it.pulping1.berat.toString()+" KG",
                    "Rp. "+it.pulping1.biaya_jemur.toString(),
                    "Rp. "+it.pulping1.biaya_pulping.toString(),
                    //PULPING2
                    it.pulping2.tanggal,
                    it.pulping2.berat.toString()+" KG",
                    "Rp. "+it.pulping2.biaya.toString(),
                    //JEMUR KADAR AIR
                    it.jemurKadarAir.tanggal,
                    it.jemurKadarAir.kadarAir.toString(),
                    it.jemurKadarAir.berat.toString()+" KG",
                    "Rp. "+it.jemurKadarAir.biaya.toString(),
                    //JEMUR1
                    it.jemur1.tanggal,
                    it.jemur1.berat.toString()+" KG",
                    "Rp. "+it.jemur1.biaya.toString(),
                    //JEMUR2
                    it.jemur2.tanggal,
                    it.jemur2.berat.toString()+" KG",
                    "Rp. "+it.jemur2.biaya.toString(),
                    //hulling
                    it.hulling.tanggal,
                    it.hulling.berat.toString()+" KG",
                    "Rp."+ it.hulling.biaya.toString(),
                    it.hulling.kadarAir.toString(),
                    //suton grader
                    it.sutonGrader.tanggal,
                    it.sutonGrader.berat.toString()+" KG",
                    "Rp. "+it.sutonGrader.biaya.toString(),
                    //size grading
                    it.sizeGrading.tanggal,
                    it.sizeGrading.berat.toString()+" KG",
                    "Rp. "+it.sizeGrading.biaya.toString(),
                    //colorsorter
                    it.colorSorter.tanggal,
                    it.colorSorter.berat.toString()+" KG",
                    "Rp. "+it.colorSorter.biaya.toString(),
                    //handpick
                    it.handPick.tanggal,
                    it.handPick.berat.toString()+" KG",
                    "Rp. "+it.handPick.biaya.toString()

                )

                csvWriter.writeNext(x)
            }

            println("Write CSV using CSVWriter successfully!")
            Toast.makeText(this.context!!, "Exported as "+dir, Toast.LENGTH_LONG).show()
//            val ini : File = File(dir)
//            println(File(dir).exists())
//            println(File("/storage/emulated/0/Hpp.csv").exists())
//            File("/storage/emulated/0/Hpp.csv").readLines()
            //File("/storage/emulated/0/data.txt").writeText("Hello world!")
            //this.backupToDrive(File(dir))
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

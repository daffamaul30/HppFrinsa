package frinsa.hpp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import frinsa.hpp.data.tahap.*

val DATABASE_NAME ="MyDB"
val COL_STATUS = "status"
//Spinner
val TABLE_VARIETAS="Varietas"
val TABLE_BLOK="Blok"
val TABLE_PROSES="Proses"
val COL_NAME = "name"
val COL_ID = "id"
val COL_STEP = "step"
//Standard
val COL_TGL = "tanggal"
val COL_BERAT = "berat"
val COL_BIAYA = "biaya"
val TABLE_SUTON_GRADER = "Suton_Grader"
val TABLE_SIZE_GRADING = "Size_Grading"
val TABLE_COLOR_SORTER = "Color_Sorter"
val TABLE_HAND_PICK = "Hand_Pick"
val TABLE_JEMUR1 = "Jemur_Satu"
val TABLE_JEMUR2 = "Jemur_Dua"
val TABLE_PULPING2 = "Pulping_Dua"
//Kadar Air
val COL_KDR_AIR = "kadar-air"
val TABLE_HULLING = "Hulling"
val TABLE_JEMUR_KADAR_AIR = "Jemur_Kadar_Air"
//Fermentasi
val COL_BIAYA_FERMENTASI = "biaya_fermentasi"
val COL_BIAYA_MUAT = "biaya_muat"
val TABLE_FERMEN = "Fermentasi"
//Transportasi
val COL_BIAYA_TRANSPORT = "biaya_transport"
val COL_BIAYA_KAWAL = "biaya_kawal"
val COL_BIAYA_BONGKAR = "biaya_bongkar"
val TABLE_TRANSPORTASI = "Transportasi"
//Pulping1
val COL_BIAYA_PULPING = "biaya_pulping"
val COL_BIAYA_JEMUR = "biaya_jemur"
val TABLE_PULPING1 = "pulping_satu"
//Produk
val TABLE_PRODUKSI = "Produksi"
val COL_ID_PRODUKSI = "id_Produksi"
//val COL_TANGGAL = "Tanggal"
val COL_SUMBER = "sumber"
val COL_BELI_DARI = "beli_dari"
val COL_BENTUK ="bentuk"
val COL_VARI = "varietas"
val COL_BLOKP = "blok"
//val COL_BERAT_PRODUKSI = "Berat"
val COL_PROSES_PRODUKSI = "proses"
val COL_STATUS_PRODUKSI = "status"
//Petik
val TABLE_PETIK = "Petik"
val COL_ID_PETIK = "id_petik"
val COL_ID_PETIK_PRODUKSI = "id_produksi"
val COL_TGL_PETIK = "tanggal"
val COL_BERAT_PETIK = "berat"
val COL_BIAYA_PETIK = "biaya_petik"
val COL_BIAYA_OJEK = "biaya_ojek"
val COL_BIAYA_CUCI = "biaya_cuci"

class DBPanen(var context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null, 1) {

    val createTableProduksi = "CREATE TABLE " + TABLE_PRODUKSI + "(" +
            COL_ID_PRODUKSI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COL_TANGGAL + "DATE, " +
            COL_SUMBER + " VARCHAR(30), " +
            COL_BELI_DARI + " INTEGER, " +
            COL_BENTUK + " VARCHAR(30), " +
            COL_VARI + " VARCHAR(30), " +
            COL_BLOKP + " VARCHAR(30), " +
//            COL_BERAT_PRODUKSI + "REAL, " +
            COL_PROSES_PRODUKSI+ " VARCHAR(30), " +
            COL_STATUS_PRODUKSI+ " VARCHAR(30) )"

    val createTablePetik = "CREATE TABLE " + TABLE_PETIK + "(" +
            COL_ID_PETIK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PETIK_PRODUKSI + " INTEGER, " +
            COL_TGL_PETIK + " DATE, " +
            COL_BERAT_PETIK + " REAL, " +
            COL_BIAYA_PETIK + " INTEGER, " +
            COL_BIAYA_OJEK + " INTEGER, " +
            COL_BIAYA_CUCI + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PETIK_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableFermentasi = "CREATE TABLE " + TABLE_FERMEN + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI + " INTEGER, " +
            COL_TGL + " DATE, " +
            COL_BERAT + " REAL, " +
            COL_BIAYA_FERMENTASI + " INTEGER, " +
            COL_BIAYA_MUAT + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableTransportasi = "CREATE TABLE " + TABLE_TRANSPORTASI + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI + " INTEGER, " +
            COL_TGL + " DATE, " +
            COL_BERAT + " REAL, " +
            COL_BIAYA_TRANSPORT + " INTEGER, " +
            COL_BIAYA_KAWAL + " INTEGER, " +
            COL_BIAYA_BONGKAR + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTablePulping1 = "CREATE TABLE " + TABLE_PULPING1 + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI + " INTEGER, " +
            COL_TGL + " DATE, " +
            COL_BERAT + " REAL, " +
            COL_BIAYA_PULPING + " INTEGER, " +
            COL_BIAYA_FERMENTASI + " INTEGER, " +
            COL_BIAYA_CUCI + " INTEGER, " +
            COL_BIAYA_JEMUR + " INTEGER, " +
            COL_BIAYA_MUAT + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    fun createTableSpinner(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50))"

    fun createTableProses(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50), " +
            COL_STEP + " TEXT)"

    fun createTableStandard(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI + " INTEGER, " +
            COL_TGL + " DATE, " +
            COL_BERAT + " REAL, " +
            COL_BIAYA + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    fun createTableKadarAir(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI + " INTEGER, " +
            COL_TGL + " DATE, " +
            COL_BERAT + " REAL, " +
            COL_KDR_AIR + " REAL, " +
            COL_BIAYA + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"


    fun selectAll(TABLE_NAME: String) = "Select * from " + TABLE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTableSpinner(TABLE_VARIETAS))
        db?.execSQL(createTableSpinner(TABLE_BLOK))
        db?.execSQL(createTableProses(TABLE_PROSES))
        db?.execSQL(createTableKadarAir(TABLE_JEMUR_KADAR_AIR))
        db?.execSQL(createTableKadarAir(TABLE_HULLING))
        db?.execSQL(createTableStandard(TABLE_PULPING2))
        db?.execSQL(createTableStandard(TABLE_JEMUR1))
        db?.execSQL(createTableStandard(TABLE_JEMUR2))
        db?.execSQL(createTableStandard(TABLE_SUTON_GRADER))
        db?.execSQL(createTableStandard(TABLE_SIZE_GRADING))
        db?.execSQL(createTableStandard(TABLE_COLOR_SORTER))
        db?.execSQL(createTableStandard(TABLE_HAND_PICK))
        db?.execSQL(createTableProduksi)
        db?.execSQL(createTablePetik)
        db?.execSQL(createTableFermentasi)
        db?.execSQL(createTableTransportasi)
        db?.execSQL(createTablePulping1)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_VARIETAS)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_BLOK)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PROSES)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PRODUKSI)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PETIK)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_HULLING)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_SUTON_GRADER)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_SIZE_GRADING)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_COLOR_SORTER)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_HAND_PICK)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PULPING2)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_JEMUR_KADAR_AIR)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_JEMUR1)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_JEMUR2)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_FERMEN)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_TRANSPORTASI)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PULPING1)
        onCreate(db)
    }

    fun insertPanen(pr: Produksi, pe: Petik) {
        val db = this.writableDatabase

        //Tabel Produksi
        var cvP = ContentValues()

//        cvP.put(COL_TANGGAL, pr.tgl_produksi)
        cvP.put(COL_SUMBER, pr.sumber)
        cvP.put(COL_BELI_DARI, pr.beli_dari)
        cvP.put(COL_BENTUK, pr.bentuk)
        cvP.put(COL_VARI, pr.varietas)
        cvP.put(COL_BLOKP, pr.blok)
        cvP.put(COL_PROSES_PRODUKSI, pr.proses)
        cvP.put(COL_STATUS_PRODUKSI, pr.status)
        var resultPR = db.insert(TABLE_PRODUKSI, null, cvP)

        //Tabel Petik
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cvC = ContentValues()
        cvC.put(COL_ID_PETIK_PRODUKSI, idProduksi)
        cvC.put(COL_TGL_PETIK, pe.tgl_petik)
        cvC.put(COL_BERAT_PETIK, pe.berat)
        cvC.put(COL_BIAYA_PETIK, pe.biaya_petik)
        cvC.put(COL_BIAYA_OJEK, pe.biaya_ojek)
        cvC.put(COL_BIAYA_CUCI, pe.biaya_cuci)
        var resultPE = db.insert(TABLE_PETIK, null, cvC)

        if ((resultPR == -1.toLong()) || (resultPE == -1.toLong())) {
//            println(resultPR)
//            println(resultPE)
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }

        //TEST GET DATA
        val panen = readPanen()
        println(panen.size)
        if (panen.size > 0) {
            for (i in 0..(panen.size-1)) {
                print(panen.get(i).produksi.id_produksi)
                println(panen.get(i).petik.id_petik_produksi)
                if (panen.get(i).produksi.id_produksi == panen.get(i).petik.id_petik_produksi) {
                    kotlin.io.println("MASOK")
                    println("""
                                        DATA KE-${i+1}
                                        PRODUKSI
                                        id Produksi = ${panen.get(i).produksi.id_produksi}
                                        sumber = ${panen.get(i).produksi.sumber}
                                        beli dari = ${panen.get(i).produksi.beli_dari}
                                        bentuk = ${panen.get(i).produksi.bentuk}
                                        varietas = ${panen.get(i).produksi.varietas}
                                        blok = ${panen.get(i).produksi.blok}
                                        proses = ${panen.get(i).produksi.proses}
                                        status = ${panen.get(i).produksi.status}
                                        \n
                                        PETIK
                                        id Petik = ${panen.get(i).petik.id_petik}
                                        id Produksi = ${panen.get(i).petik.id_petik_produksi}
                                        tanggal Petik = ${panen.get(i).petik.tgl_petik}
                                        berat = ${panen.get(i).petik.berat}
                                        biaya petik = ${panen.get(i).petik.biaya_petik}
                                        biaya ojek = ${panen.get(i).petik.biaya_ojek}
                                        biaya cuci = ${panen.get(i).petik.biaya_cuci}
                                    """.trimIndent())
                }
            }
        }
    }

    fun readPanen():  MutableList<Produk> {
//        var daftarproduk: MutableList<Produksi> = ArrayList()
//        var daftarpetik: MutableList<Petik> = ArrayList()
        var  list : MutableList<Produk> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PRODUKSI JOIN $TABLE_PETIK ON " + TABLE_PETIK + "." + COL_ID_PETIK_PRODUKSI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI, null)
        if (result.moveToFirst()) {
            do {
                var produksi = Produksi()
                produksi.id_produksi = result.getString(result.getColumnIndex(COL_ID_PRODUKSI)).toInt()
                produksi.sumber = result.getString(result.getColumnIndex(COL_SUMBER))
                produksi.beli_dari = result.getString(result.getColumnIndex(COL_BELI_DARI))
                produksi.bentuk = result.getString(result.getColumnIndex(COL_BENTUK))
                produksi.varietas = result.getString(result.getColumnIndex(COL_VARI))
                produksi.blok = result.getString(result.getColumnIndex(COL_BLOKP))
                produksi.proses = result.getString(result.getColumnIndex(COL_PROSES_PRODUKSI))
                produksi.status = result.getString(result.getColumnIndex(COL_STATUS))

                var petik = Petik()
                petik.id_petik = result.getString(result.getColumnIndex(COL_ID_PETIK)).toInt()
                petik.id_petik_produksi = result.getString(result.getColumnIndex(COL_ID_PETIK_PRODUKSI)).toInt()
                petik.tgl_petik = result.getString(result.getColumnIndex(COL_TGL_PETIK))
                petik.berat = result.getString(result.getColumnIndex(COL_BERAT_PETIK)).toDouble()
                petik.biaya_petik = result.getString(result.getColumnIndex(COL_BIAYA_PETIK)).toInt()
                petik.biaya_ojek = result.getString(result.getColumnIndex(COL_BIAYA_OJEK)).toInt()
                petik.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI)).toInt()
//                daftarproduk.add(produksi)
//                daftarpetik.add(petik)
                var produk = Produk(produksi,petik)
                list.add(produk)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun insertFermentasi(f: Fermentasi) {
        val db = this.writableDatabase

        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI, f.id2)
        cv.put(COL_TGL, f.tanggal)
        cv.put(COL_BERAT, f.berat)
        cv.put(COL_BIAYA_FERMENTASI, f.biaya_fermentasi)
        cv.put(COL_BIAYA_MUAT, f.biaya_muat)
        var result = db.insert(TABLE_FERMEN, null, cv)

        if (result == -1.toLong()) {
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun insertTransportasi(t: Transportasi) {
        val db = this.writableDatabase

        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI, t.id2)
        cv.put(COL_TGL, t.tanggal)
        cv.put(COL_BERAT, t.berat)
        cv.put(COL_BIAYA_TRANSPORT, t.biaya_transport)
        cv.put(COL_BIAYA_KAWAL, t.biaya_kawal)
        cv.put(COL_BIAYA_BONGKAR, t.biaya_bongkar)
        var result = db.insert(TABLE_TRANSPORTASI, null, cv)

        if (result == -1.toLong()) {
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun insertPulping1(p: pulpingSatu) {
        val db = this.writableDatabase

        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI, p.id2)
        cv.put(COL_TGL, p.tanggal)
        cv.put(COL_BERAT, p.berat)
        cv.put(COL_BIAYA_PULPING, p.biaya_pulping)
        cv.put(COL_BIAYA_FERMENTASI, p.biaya_fermentasi)
        cv.put(COL_BIAYA_CUCI, p.biaya_cuci)
        cv.put(COL_BIAYA_JEMUR, p.biaya_jemur)
        cv.put(COL_BIAYA_MUAT, p.biaya_muat)
        var result = db.insert(TABLE_PULPING1, null, cv)

        if (result == -1.toLong()) {
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun <T: Standard> insertStandard(data: T, TABLE_NAME: String) {
        val db = this.writableDatabase

        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI, data.id2)
        cv.put(COL_TGL, data.tanggal)
        cv.put(COL_BERAT, data.berat)
        cv.put(COL_BIAYA, data.biaya)
        var result = db.insert(TABLE_NAME, null, cv)

        if (result == -1.toLong()) {
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun getIdProduksi(TABLE_NAME: String): Int {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        result.moveToLast()
        return result.getString(result.getColumnIndex(COL_ID_PRODUKSI)).toInt()
    }

//    fun insertVarietas(v: Varietas) {
//        val db = this.writableDatabase
//        var cv = ContentValues()
//        cv.put(COL_NAME, v.name)
//
//        var result = db.insert(TABLE_VARIETAS, null, cv)
//        if (result == -1.toLong()) {
//            toastMessage("Gagal")
//        } else {
//            toastMessage("Berhasil")
//        }
//    }
//
//    fun insertBlok(b: Blok) {
//        val db = this.writableDatabase
//        var cv = ContentValues()
//        cv.put(COL_NAME, b.name)
//
//        var result = db.insert(TABLE_BLOK, null, cv)
//        if (result == -1.toLong()) {
//            toastMessage("Gagal")
//        } else {
//            toastMessage("Berhasil")
//        }
//    }

    fun <T: Varietas> insertSpin(data: T, TABLE_NAME: String) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, data.name)

        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            toastMessage("Gagal")
        } else {
            toastMessage("Berhasil")
        }
    }

    fun insertProses(p: Proses) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, p.name)
        cv.put(COL_STEP, p.step)

        var result = db.insert(TABLE_PROSES, null, cv)
        if (result == -1.toLong()) {
            toastMessage("Gagal")
        } else {
            toastMessage("Berhasil")
        }
    }

    fun readSpin(TABLE_NAME: String): MutableList<Varietas> {
        var list: MutableList<Varietas> = ArrayList()

        val db = this.readableDatabase
        val result = db.rawQuery(selectAll(TABLE_NAME), null)
        if (result.moveToFirst()) {
            if (TABLE_NAME == TABLE_VARIETAS) {
                do {
                    var varietas = Varietas()
                    varietas.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                    varietas.name = result.getString(result.getColumnIndex(COL_NAME))
                    list.add(varietas)
                }while (result.moveToNext())
            }
            else if (TABLE_NAME == TABLE_BLOK) {
                do {
                    var blok = Blok()
                    blok.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                    blok.name = result.getString(result.getColumnIndex(COL_NAME))
                    list.add(blok)
                }while (result.moveToNext())
            }
        }
        result.close()
        db.close()
        return list
    }

//    fun readVarietas(): MutableList<Varietas> {
//        var list: MutableList<Varietas> = ArrayList()
//
//        val db = this.readableDatabase
//        val result = db.rawQuery(selectAll(TABLE_VARIETAS), null)
//        if (result.moveToFirst()) {
//            do {
//                var varietas = Varietas()
//                varietas.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                varietas.name = result.getString(result.getColumnIndex(COL_NAME))
//                list.add(varietas)
//            }while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//        return list
//    }
//
//    fun readBlok(): MutableList<Blok> {
//        var list: MutableList<Blok> = ArrayList()
//
//        val db = this.readableDatabase
//        val result = db.rawQuery(selectAll(TABLE_BLOK), null)
//        if (result.moveToFirst()) {
//            do {
//                var blok = Blok()
//                blok.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                blok.name = result.getString(result.getColumnIndex(COL_NAME))
//                list.add(blok)
//            }while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//        return list
//    }

    fun readProses(): MutableList<Proses> {
        var list: MutableList<Proses> = ArrayList()

        val db = this.readableDatabase
        val result = db.rawQuery(selectAll(TABLE_PROSES), null)
        if (result.moveToFirst()) {
            do {
                var proses = Proses()
                proses.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                proses.name = result.getString(result.getColumnIndex(COL_NAME))
                proses.step = result.getString(result.getColumnIndex(COL_STEP))
                list.add(proses)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun toastMessage(text: String) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

}
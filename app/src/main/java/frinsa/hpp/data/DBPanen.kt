package frinsa.hpp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME ="MyDB"
val COL_STATUS = "status"
//Spinner
val TABLE_VARIETAS="Varietas"
val TABLE_BLOK="Blok"
val TABLE_PROSES="Proses"
val COL_NAME = "name"
val COL_ID = "id"
val COL_STEP = "step"
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
            COL_BIAYA_PETIK + " REAL, " +
            COL_BIAYA_OJEK + " REAL, " +
            COL_BIAYA_CUCI + " REAL, " +
            " FOREIGN KEY ("+ COL_ID_PETIK_PRODUKSI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    fun createTableSpinner(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50))"

    fun createTableProses(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50), " +
            COL_STEP + " TEXT)"

    fun selectAll(TABLE_NAME: String) = "Select * from " + TABLE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTableSpinner(TABLE_VARIETAS))
        db?.execSQL(createTableSpinner(TABLE_BLOK))
        db?.execSQL(createTableProses(TABLE_PROSES))
        db?.execSQL(createTableProduksi)
        db?.execSQL(createTablePetik)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_VARIETAS)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_BLOK)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PROSES)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PRODUKSI)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PETIK)
        onCreate(db)
    }

    fun insertPanen(pr: Produksi, pe: Petik): Pair<Long, Long> {
        val db = this.writableDatabase

        //Tabel Panen
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

        //Tabel Cherry
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cvC = ContentValues()
        cvC.put(COL_ID_PETIK, idProduksi)
        cvC.put(COL_TGL_PETIK, pe.tgl_petik)
        cvC.put(COL_BERAT_PETIK, pe.berat)
        cvC.put(COL_BIAYA_PETIK, pe.biaya_petik)
        cvC.put(COL_BIAYA_OJEK, pe.biaya_ojek)
        cvC.put(COL_BIAYA_CUCI, pe.biaya_cuci)
        var resultPE = db.insert(TABLE_PETIK, null, cvC)

        println("""
            id produksi =
            id petik produksi =
            id petik =
        """.trimIndent())
        return Pair(resultPR, resultPE)
    }

    fun readPanen():  MutableList<Produk> {
//        var daftarproduk: MutableList<Produksi> = ArrayList()
//        var daftarpetik: MutableList<Petik> = ArrayList()
        var  list : MutableList<Produk> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PRODUKSI JOIN $TABLE_PETIK ON " + TABLE_PETIK + "." + COL_ID_PETIK_PRODUKSI  + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI, null)
        if (result.moveToFirst()) {
            print("MASOK")
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
        println(list)
        return list
    }

//    fun insertPanen(p: Panen, c: Cherry) {
//        val db = this.writableDatabase
//
//        //Tabel Panen
//        var cvP = ContentValues()
//
//        cvP.put(COL_TGL, p.tanggal)
//        cvP.put(COL_VARIETAS, p.varietas)
//        cvP.put(COL_BLOK, p.blok)
//        cvP.put(COL_PROSES, p.proses)
//        var resultP = db.insert(TABLE_PANEN, null, cvP)
//
//        //Tabel Cherry
//        val idPanen = getId(TABLE_PANEN)
//        var cvC = ContentValues()
//        cvC.put(COL_ID2, idPanen)
//        cvC.put(COL_BERAT, c.berat)
//        cvC.put(COL_ONGKOS_PETIK_ATAU_HARGA_CERI, c.ongkosPetik_atau_hargaCeri)
//        cvC.put(COL_OJEK, c.ojek)
//        cvC.put(COL_ONGKOS_CUCI, c.ongkosCuci)
//        var resultC = db.insert(TABLE_CHERRY, null, cvC)
//
//        if ((resultP == -1.toLong()) || (resultC == -1.toLong())) {
//            println(resultP)
//            println(resultC)
//            toastMessage("Gagal")
//        }else {
//            toastMessage("Berhasil")
//        }
//    }

//    fun getPanen(): Pair<MutableList<Panen>, MutableList<Cherry>> {
//        var daftarPanen: MutableList<Panen> = ArrayList()
//        var daftarCheri: MutableList<Cherry> = ArrayList()
//        val db = this.readableDatabase
//        val result = db.rawQuery("SELECT * FROM $TABLE_PANEN JOIN $TABLE_CHERRY ON " + TABLE_CHERRY + "." + COL_ID_CHERRY  +"="  + TABLE_PANEN + "." + COL_ID_PANEN, null)
//        if (result.moveToFirst()) {
//            do {
//                var panen = Panen()
//                panen.id = result.getString(result.getColumnIndex(COL_ID_PANEN)).toInt()
//                panen.tanggal = result.getString(result.getColumnIndex(COL_TGL))
//                panen.varietas = result.getString(result.getColumnIndex(COL_VARIETAS))
//                panen.blok = result.getString(result.getColumnIndex(COL_BLOK))
//                panen.proses = result.getString(result.getColumnIndex(COL_PROSES))
//                panen.status = result.getString(result.getColumnIndex(COL_STATUS))
//
//                var cheri = Cherry()
//                cheri.id = result.getString(result.getColumnIndex(COL_ID_CHERRY)).toInt()
//                cheri.id2 = result.getString(result.getColumnIndex(COL_ID_PANEN)).toInt()
//                cheri.berat = result.getString(result.getColumnIndex(COL_BERAT)).toDouble()
//                cheri.ongkosPetik_atau_hargaCeri = result.getString(result.getColumnIndex(COL_ONGKOS_PETIK_ATAU_HARGA_CERI)).toInt()
//                cheri.ojek = result.getString(result.getColumnIndex(COL_OJEK)).toInt()
//                cheri.ongkosCuci = result.getString(result.getColumnIndex(COL_ONGKOS_CUCI)).toInt()
//
//                daftarPanen.add(panen)
//                daftarCheri.add(cheri)
//            }while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//        return Pair(daftarPanen,daftarCheri)
//    }



    fun getIdProduksi(TABLE_NAME: String): Int {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        result.moveToLast()
        return result.getString(result.getColumnIndex(COL_ID_PRODUKSI)).toInt()
    }

    fun insertVarietas(v: Varietas) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, v.name)

        var result = db.insert(TABLE_VARIETAS, null, cv)
        if (result == -1.toLong()) {
            toastMessage("Gagal")
        } else {
            toastMessage("Berhasil")
        }
    }

    fun insertBlok(b: Blok) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, b.name)

        var result = db.insert(TABLE_BLOK, null, cv)
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

    fun readVarietas(): MutableList<Varietas> {
        var list: MutableList<Varietas> = ArrayList()

        val db = this.readableDatabase
        val result = db.rawQuery(selectAll(TABLE_VARIETAS), null)
        if (result.moveToFirst()) {
            do {
                var varietas = Varietas()
                varietas.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                varietas.name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(varietas)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun readBlok(): MutableList<Blok> {
        var list: MutableList<Blok> = ArrayList()

        val db = this.readableDatabase
        val result = db.rawQuery(selectAll(TABLE_BLOK), null)
        if (result.moveToFirst()) {
            do {
                var blok = Blok()
                blok.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                blok.name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(blok)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

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
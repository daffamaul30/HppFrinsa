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
//Produksi
val COL_ID_PANEN = "id_panen"
val TABLE_PANEN = "Panen"
val COL_TGL = "tanggal"
val COL_VARIETAS = "varietas"
val COL_BLOK = "blok"
val COL_KOLEKTIF = "kolektif_dari"
val COL_PROSES = "tipe_proses"
//Cherry
val TABLE_CHERRY = "Cherry"
val COL_ID_CHERRY = "Cherry"
val COL_ID2 = "id_panen"
val COL_BERAT = "berat"
val COL_ONGKOS_PETIK_ATAU_HARGA_CERI = "ongkos_atau_harga"
val COL_OJEK = "ojek"
val COL_ONGKOS_CUCI = "ongkos_cuci"


class DBPanen(var context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null, 1) {
    val createTablePanen = "CREATE TABLE " + TABLE_PANEN + "(" +
            COL_ID_PANEN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TGL + " DATE, " +
            COL_VARIETAS + " VARCHAR(30), " +
            COL_BLOK + " VARCHAR(30), " +
            COL_PROSES + " VARCHAR(50), " +
            COL_STATUS + " VARCHAR(20) NOT NULL DEFAULT 'cherry')"

    val createTableCherry = "CREATE TABLE " + TABLE_CHERRY + "(" +
            COL_ID_CHERRY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID2 + " INTEGER, " +
            COL_BERAT + " REAL, " +
            COL_ONGKOS_PETIK_ATAU_HARGA_CERI + " INTEGER, " +
            COL_OJEK + " INTEGER, " +
            COL_ONGKOS_CUCI + " INTEGER, " +
            " FOREIGN KEY ("+ COL_ID2 +") REFERENCES "+ TABLE_PANEN +"("+ COL_ID_PANEN +"))"

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
        db?.execSQL(createTablePanen)
        db?.execSQL(createTableCherry)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_VARIETAS)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_BLOK)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PROSES)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PANEN)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_CHERRY)
        onCreate(db)
    }

    fun insertPanen(p: Panen, c: Cherry) {
        val db = this.writableDatabase

        //Tabel Panen
        var cvP = ContentValues()

        cvP.put(COL_TGL, p.tanggal)
        cvP.put(COL_VARIETAS, p.varietas)
        cvP.put(COL_BLOK, p.blok)
        cvP.put(COL_PROSES, p.proses)
        var resultP = db.insert(TABLE_PANEN, null, cvP)

        //Tabel Cherry
        val idPanen = getId(TABLE_PANEN)
        var cvC = ContentValues()
        cvC.put(COL_ID2, idPanen)
        cvC.put(COL_BERAT, c.berat)
        cvC.put(COL_ONGKOS_PETIK_ATAU_HARGA_CERI, c.ongkosPetik_atau_hargaCeri)
        cvC.put(COL_OJEK, c.ojek)
        cvC.put(COL_ONGKOS_CUCI, c.ongkosCuci)
        var resultC = db.insert(TABLE_CHERRY, null, cvC)

        if ((resultP == -1.toLong()) || (resultC == -1.toLong())) {
            println(resultP)
            println(resultC)
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun getPanen(): Pair<MutableList<Panen>, MutableList<Cherry>> {
        var daftarPanen: MutableList<Panen> = ArrayList()
        var daftarCheri: MutableList<Cherry> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PANEN JOIN $TABLE_CHERRY ON " + TABLE_CHERRY + "." + COL_ID_CHERRY  +"="  + TABLE_PANEN + "." + COL_ID_PANEN, null)
        if (result.moveToFirst()) {
            do {
                var panen = Panen()
                panen.id = result.getString(result.getColumnIndex(COL_ID_PANEN)).toInt()
                panen.tanggal = result.getString(result.getColumnIndex(COL_TGL))
                panen.varietas = result.getString(result.getColumnIndex(COL_VARIETAS))
                panen.blok = result.getString(result.getColumnIndex(COL_BLOK))
                panen.proses = result.getString(result.getColumnIndex(COL_PROSES))
                panen.status = result.getString(result.getColumnIndex(COL_STATUS))

                var cheri = Cherry()
                cheri.id = result.getString(result.getColumnIndex(COL_ID_CHERRY)).toInt()
                cheri.id2 = result.getString(result.getColumnIndex(COL_ID_PANEN)).toInt()
                cheri.berat = result.getString(result.getColumnIndex(COL_BERAT)).toDouble()
                cheri.ongkosPetik_atau_hargaCeri = result.getString(result.getColumnIndex(COL_ONGKOS_PETIK_ATAU_HARGA_CERI)).toInt()
                cheri.ojek = result.getString(result.getColumnIndex(COL_OJEK)).toInt()
                cheri.ongkosCuci = result.getString(result.getColumnIndex(COL_ONGKOS_CUCI)).toInt()

                daftarPanen.add(panen)
                daftarCheri.add(cheri)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return Pair(daftarPanen,daftarCheri)
    }

    fun getId(TABLE_NAME: String): Int {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        result.moveToLast()
        return result.getString(result.getColumnIndex(COL_ID_PANEN)).toInt()
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
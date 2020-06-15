package frinsa.hpp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf
import java.security.AccessControlContext

val DATABASE_NAME ="MyDB"
val TABLE_VARIETAS="Varietas"
val TABLE_BLOK="Blok"
val TABLE_PROSES="Proses"
val COL_NAME = "name"
val COL_ID = "id"

class DBLocal(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    fun createTable(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50))"

    fun selectAll(TABLE_NAME: String) = "Select * from " + TABLE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTable(TABLE_VARIETAS))
        db?.execSQL(createTable(TABLE_BLOK))
        db?.execSQL(createTable(TABLE_PROSES))

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_VARIETAS)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_BLOK)
        db?.execSQL("DROP IF TABLE EXISTS " + TABLE_PROSES)
        onCreate(db)
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

        var result = db.insert(TABLE_BLOK, null, cv)
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
        val result = db.rawQuery(selectAll(TABLE_VARIETAS), null)
        if (result.moveToFirst()) {
            do {
                var proses = Proses()
                proses.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                proses.name = result.getString(result.getColumnIndex(COL_NAME))
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
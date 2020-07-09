package frinsa.hpp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import frinsa.hpp.data.tahap.*

val DATABASE_NAME ="MyDB"
//Spinner
val TABLE_VARIETAS="Varietas"
val TABLE_BLOK="Blok"
val TABLE_PROSES="Proses"
val COL_NAME = "name"
val COL_ID = "id"
val COL_STEP = "step"
//Jemur Kadar Air
val COL_ID_KADAR_AIR = "id_kadar_air"
val COL_ID_PRODUKSI_KADAR_AIR = "id_produksi_kadar_air"
val COL_TGL_KADAR_AIR = "tanggal_kadar_air"
val COL_BERAT_KADAR_AIR = "berat_kadar_air"
val COL_KDR_AIR_KADAR_AIR = "kdr_air_kadar_air"
val COL_BIAYA_JEMUR_KADAR_AIR = "biaya_jemur_kadar_air"
val TABLE_JEMUR_KADAR_AIR = "Jemur_Kadar_Air"
//Hulling
val COL_ID_HULLING = "id_hulling"
val COL_ID_PRODUKSI_HULLING = "id_produksi_hulling"
val COL_TGL_HULLING = "tanggal_hulling"
val COL_BERAT_HULLING = "berat_hulling"
val COL_KDR_AIR_HULLING = "kdr_air_hulling"
val COL_BIAYA_HULLING = "biaya_hulling"
val TABLE_HULLING = "Hulling"
//Fermentasi
val COL_ID_FERMENTASI = "id_fermentasi"
val COL_BIAYA_FERMENTASI = "biaya_fermentasi"
val COL_BIAYA_MUAT_FERMENTASI = "biaya_muat_fermentasi"
val COL_ID_PRODUKSI_FERMENTASI = "id_produksi_fermentasi"
val COL_TGL_FERMENTASI = "tanggal_fermentasi"
val COL_BERAT_FERMENTASI = "berat_fermentasi"
val TABLE_FERMEN = "Fermentasi"
//Transportasi
val COL_ID_TRANSPORTASI = "id_transportasi"
val COL_ID_PRODUKSI_TRANSPORTASI = "id_produksi_transportasi"
val COL_TGL_TRANSPORTASI = "tanggal_transportasi"
val COL_BERAT_TRANSPORTASI = "berat_transportasi"
val COL_BIAYA_TRANSPORT = "biaya_transport"
val COL_BIAYA_KAWAL_TRANSPORTASI = "biaya_kawal"
val COL_BIAYA_BONGKAR_TRANSPORTASI = "biaya_bongkar"
val TABLE_TRANSPORTASI = "Transportasi"
//Pulping1
val COL_ID_PULPING1 = "id_pulping1"
val COL_ID_PRODUKSI_PULPING1 = "id_produksi_pulping"
val COL_TGL_PULPING1 = "tanggal_pupling1"
val COL_BERAT_PULPING1 = "berat_pulping1"
val COL_BIAYA_PULPING1 = "biaya_pulping"
val COL_BIAYA_FERMENTASI_PULPING1 = "biaya_fermentasi_pulping1"
val COL_BIAYA_CUCI_PULPING1 = "biaya_cuci_pulping1"
val COL_BIAYA_JEMUR_PULPING1 = "biaya_jemur"
val COL_BIAYA_MUAT_PULPING1 = "biaya_muat_pulping1"
val TABLE_PULPING1 = "pulping_satu"
//Pulping2
val COL_ID_PULPING2 = "id_pulping2"
val COL_ID_PRODUKSI_PULPING2 = "id_produksi_pulping2"
val COL_TGL_PULPING2 = "tanggal_pulping2"
val COL_BERAT_PULPING2 = "berat_pulping2"
val COL_BIAYA_PULPING2 = "biaya_pulping2"
val TABLE_PULPING2 = "Pulping_Dua"
//Jemur1
val COL_ID_JEMUR1 = "id_jemur1"
val COL_ID_PRODUKSI_JEMUR1 = "id_produksi_jemur1"
val COL_TGL_JEMUR1 = "tanggal_jemur1"
val COL_BERAT_JEMUR1 = "berat_jemur1"
val COL_BIAYA_JEMUR1 = "biaya_jemur1"
val TABLE_JEMUR1 = "Jemur_Satu"
//Jemur2
val COL_ID_JEMUR2 = "id_jemur2"
val COL_ID_PRODUKSI_JEMUR2 = "id_produksi_jemur2"
val COL_TGL_JEMUR2 = "tanggal_jemur2"
val COL_BERAT_JEMUR2 = "berat_jemur2"
val COL_BIAYA_JEMUR2 = "biaya_jemur2"
val TABLE_JEMUR2 = "Jemur_Dua"
//HandPick
val COL_ID_HAND_PICK = "id_hand_pick"
val COL_ID_PRODUKSI_HAND_PICK = "id_produksi_hand_pick"
val COL_TGL_HAND_PICK = "tanggal_hand_pick"
val COL_BERAT_HAND_PICK = "berat_hand_pick"
val COL_BIAYA_HAND_PICK = "biaya_hand_pick"
val TABLE_HAND_PICK = "Hand_Pick"
//Color Sorter
val COL_ID_COLOR_SORTER = "id_color_sorter"
val COL_ID_PRODUKSI_COLOR_SORTER = "id_produksi_color_sorter"
val COL_TGL_COLOR_SORTER = "tanggal_color_sorter"
val COL_BERAT_COLOR_SORTER = "berat_color_sorter"
val COL_BIAYA_COLOR_SORTER = "biaya_color_sorter"
val TABLE_COLOR_SORTER = "Color_Sorter"
//Size Grading
val COL_ID_SIZE_GRADING = "id_size_grading"
val COL_ID_PRODUKSI_SIZE_GRADING = "id_produksi_size_grading"
val COL_TGL_SIZE_GRADING = "tanggal_size_grading"
val COL_BERAT_SIZE_GRADING = "berat_size_grading"
val COL_BIAYA_SIZE_GRADING = "biaya_size_grading"
val TABLE_SIZE_GRADING = "Size_Grading"
//Suton Grader
val COL_ID_SUTON_GRADER = "id_suton_grader"
val COL_ID_PRODUKSI_SUTON_GRADER = "id_produksi_suton_grader"
val COL_TGL_SUTON_GRADER = "tanggal_suton_grader"
val COL_BERAT_SUTON_GRADER = "berat_suton_grader"
val COL_BIAYA_SUTON_GRADER = "biaya_suton_grader"
val TABLE_SUTON_GRADER = "Suton_Grader"
//Produk
val TABLE_PRODUKSI = "Produksi"
val COL_ID_PRODUKSI = "id_Produksi"
val COL_SUMBER = "sumber"
val COL_BELI_DARI = "beli_dari"
val COL_BENTUK ="bentuk"
val COL_VARI = "varietas"
val COL_BLOKP = "blok"
val COL_PROSES_PRODUKSI = "proses"
val COL_STATUS_PRODUKSI = "status"
//Petik
val TABLE_PETIK = "Petik"
val COL_ID_PETIK = "id_petik"
val COL_ID_PRODUKSI_PETIK = "id_produksi_petik"
val COL_TGL_PETIK = "tanggal"
val COL_BERAT_PETIK = "berat"
val COL_BIAYA_PETIK = "biaya_petik"
val COL_BIAYA_OJEK = "biaya_ojek"
val COL_BIAYA_CUCI_PETIK = "biaya_cuci"

class DBPanen(var context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null, 1) {

    val createTableProduksi = "CREATE TABLE " + TABLE_PRODUKSI + "(" +
            COL_ID_PRODUKSI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_SUMBER + " VARCHAR(30), " +
            COL_BELI_DARI + " INTEGER, " +
            COL_BENTUK + " VARCHAR(30), " +
            COL_VARI + " VARCHAR(30), " +
            COL_BLOKP + " VARCHAR(30), " +
            COL_PROSES_PRODUKSI+ " VARCHAR(30), " +
            COL_STATUS_PRODUKSI+ " VARCHAR(30) )"

    val createTablePetik = "CREATE TABLE " + TABLE_PETIK + "(" +
            COL_ID_PETIK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_PETIK + " INTEGER, " +
            COL_TGL_PETIK + " DATE, " +
            COL_BERAT_PETIK + " REAL, " +
            COL_BIAYA_PETIK + " INTEGER, " +
            COL_BIAYA_OJEK + " INTEGER, " +
            COL_BIAYA_CUCI_PETIK + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_PETIK +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableFermentasi = "CREATE TABLE " + TABLE_FERMEN + "(" +
            COL_ID_FERMENTASI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_FERMENTASI + " INTEGER, " +
            COL_TGL_FERMENTASI + " DATE, " +
            COL_BERAT_FERMENTASI + " REAL, " +
            COL_BIAYA_FERMENTASI + " INTEGER, " +
            COL_BIAYA_MUAT_FERMENTASI + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_FERMENTASI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableTransportasi = "CREATE TABLE " + TABLE_TRANSPORTASI + "(" +
            COL_ID_TRANSPORTASI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_TRANSPORTASI + " INTEGER, " +
            COL_TGL_TRANSPORTASI + " DATE, " +
            COL_BERAT_TRANSPORTASI + " REAL, " +
            COL_BIAYA_TRANSPORT + " INTEGER, " +
            COL_BIAYA_KAWAL_TRANSPORTASI + " INTEGER, " +
            COL_BIAYA_BONGKAR_TRANSPORTASI + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_TRANSPORTASI +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTablePulping1 = "CREATE TABLE " + TABLE_PULPING1 + "(" +
            COL_ID_PULPING1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_PULPING1 + " INTEGER, " +
            COL_TGL_PULPING1 + " DATE, " +
            COL_BERAT_PULPING1 + " REAL, " +
            COL_BIAYA_PULPING1 + " INTEGER, " +
            COL_BIAYA_FERMENTASI_PULPING1 + " INTEGER, " +
            COL_BIAYA_CUCI_PULPING1 + " INTEGER, " +
            COL_BIAYA_JEMUR_PULPING1 + " INTEGER, " +
            COL_BIAYA_MUAT_PULPING1 + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_PULPING1 +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    fun createTableSpinner(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50))"

    fun createTableProses(TABLE_NAME : String) = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " VARCHAR(50), " +
            COL_STEP + " TEXT)"

    val createTablePulping2 = "CREATE TABLE " + TABLE_PULPING2 + "(" +
            COL_ID_PULPING2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_PULPING2 + " INTEGER, " +
            COL_TGL_PULPING2 + " DATE, " +
            COL_BERAT_PULPING2 + " REAL, " +
            COL_BIAYA_PULPING2 + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_PULPING2 +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableJemur1 = "CREATE TABLE " + TABLE_JEMUR1 + "(" +
            COL_ID_JEMUR1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_JEMUR1 + " INTEGER, " +
            COL_TGL_JEMUR1 + " DATE, " +
            COL_BERAT_JEMUR1 + " REAL, " +
            COL_BIAYA_JEMUR1 + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_JEMUR1 +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableJemur2 = "CREATE TABLE " + TABLE_JEMUR2 + "(" +
            COL_ID_JEMUR2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_JEMUR2 + " INTEGER, " +
            COL_TGL_JEMUR2 + " DATE, " +
            COL_BERAT_JEMUR2 + " REAL, " +
            COL_BIAYA_JEMUR2 + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_JEMUR2 +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableHandPick = "CREATE TABLE " + TABLE_HAND_PICK + "(" +
            COL_ID_HAND_PICK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_HAND_PICK + " INTEGER, " +
            COL_TGL_HAND_PICK + " DATE, " +
            COL_BERAT_HAND_PICK + " REAL, " +
            COL_BIAYA_HAND_PICK + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_HAND_PICK +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableColorSorter = "CREATE TABLE " + TABLE_COLOR_SORTER + "(" +
            COL_ID_COLOR_SORTER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_COLOR_SORTER + " INTEGER, " +
            COL_TGL_COLOR_SORTER + " DATE, " +
            COL_BERAT_COLOR_SORTER + " REAL, " +
            COL_BIAYA_COLOR_SORTER + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_COLOR_SORTER +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableSizeGrading = "CREATE TABLE " + TABLE_SIZE_GRADING + "(" +
            COL_ID_SIZE_GRADING + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_SIZE_GRADING + " INTEGER, " +
            COL_TGL_SIZE_GRADING + " DATE, " +
            COL_BERAT_SIZE_GRADING + " REAL, " +
            COL_BIAYA_SIZE_GRADING + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_SIZE_GRADING +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableSutonGrader = "CREATE TABLE " + TABLE_SUTON_GRADER + "(" +
            COL_ID_SUTON_GRADER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_SUTON_GRADER + " INTEGER, " +
            COL_TGL_SUTON_GRADER + " DATE, " +
            COL_BERAT_SUTON_GRADER + " REAL, " +
            COL_BIAYA_SUTON_GRADER + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_SUTON_GRADER +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableJemurKadarAir = "CREATE TABLE " + TABLE_JEMUR_KADAR_AIR + "(" +
            COL_ID_KADAR_AIR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_KADAR_AIR + " INTEGER, " +
            COL_TGL_KADAR_AIR + " DATE, " +
            COL_BERAT_KADAR_AIR + " REAL, " +
            COL_KDR_AIR_KADAR_AIR + " REAL, " +
            COL_BIAYA_JEMUR_KADAR_AIR + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_KADAR_AIR +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"

    val createTableHulling = "CREATE TABLE " + TABLE_HULLING + "(" +
            COL_ID_HULLING + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ID_PRODUKSI_HULLING + " INTEGER, " +
            COL_TGL_HULLING + " DATE, " +
            COL_BERAT_HULLING + " REAL, " +
            COL_KDR_AIR_HULLING + " REAL, " +
            COL_BIAYA_HULLING + " INTEGER, " +
            "FOREIGN KEY ("+ COL_ID_PRODUKSI_HULLING +") REFERENCES "+ TABLE_PRODUKSI +"("+ COL_ID_PRODUKSI +"))"


    fun selectAll(TABLE_NAME: String) = "Select * from " + TABLE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTableSpinner(TABLE_VARIETAS))
        db?.execSQL(createTableSpinner(TABLE_BLOK))
        db?.execSQL(createTableProses(TABLE_PROSES))
        db?.execSQL(createTableJemurKadarAir)
        db?.execSQL(createTableHulling)
        db?.execSQL(createTablePulping2)
        db?.execSQL(createTableJemur1)
        db?.execSQL(createTableJemur2)
        db?.execSQL(createTableSutonGrader)
        db?.execSQL(createTableSizeGrading)
        db?.execSQL(createTableColorSorter)
        db?.execSQL(createTableHandPick)
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
        cvC.put(COL_ID_PRODUKSI_PETIK, idProduksi)
        cvC.put(COL_TGL_PETIK, pe.tgl_petik)
        cvC.put(COL_BERAT_PETIK, pe.berat)
        cvC.put(COL_BIAYA_PETIK, pe.biaya_petik)
        cvC.put(COL_BIAYA_OJEK, pe.biaya_ojek)
        cvC.put(COL_BIAYA_CUCI_PETIK, pe.biaya_cuci)
        var resultPE = db.insert(TABLE_PETIK, null, cvC)

        if ((resultPR == -1.toLong()) || (resultPE == -1.toLong())) {
//            println(resultPR)
//            println(resultPE)
            toastMessage("Gagal")
        }else {
            toastMessage("Berhasil")
        }
    }

    fun readPanen():  MutableList<Produk> {
//        var daftarproduk: MutableList<Produksi> = ArrayList()
//        var daftarpetik: MutableList<Petik> = ArrayList()
        var  list : MutableList<Produk> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PRODUKSI JOIN $TABLE_PETIK ON " + TABLE_PETIK + "." + COL_ID_PRODUKSI_PETIK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI, null)
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
                produksi.status = result.getString(result.getColumnIndex(COL_STATUS_PRODUKSI))

                var petik = Petik()
                petik.id_petik = result.getString(result.getColumnIndex(COL_ID_PETIK)).toInt()
                petik.id_petik_produksi = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PETIK)).toInt()
                petik.tgl_petik = result.getString(result.getColumnIndex(COL_TGL_PETIK))
                petik.berat = result.getString(result.getColumnIndex(COL_BERAT_PETIK)).toDouble()
                petik.biaya_petik = result.getString(result.getColumnIndex(COL_BIAYA_PETIK)).toInt()
                petik.biaya_ojek = result.getString(result.getColumnIndex(COL_BIAYA_OJEK)).toInt()
                petik.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PETIK)).toInt()

                var produk = Produk(produksi,petik)
                list.add(produk)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun getAllData(): MutableList<Produk> {
        var  list : MutableList<Produk> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PRODUKSI" +
                " JOIN $TABLE_PETIK ON " + TABLE_PETIK + "." + COL_ID_PRODUKSI_PETIK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_FERMEN ON " + TABLE_FERMEN + "." + COL_ID_PRODUKSI_FERMENTASI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_TRANSPORTASI ON " + TABLE_TRANSPORTASI + "." + COL_ID_PRODUKSI_TRANSPORTASI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_PULPING1 ON " + TABLE_PULPING1 + "." + COL_ID_PRODUKSI_PULPING1 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_PULPING2 ON " + TABLE_PULPING2 + "." + COL_ID_PRODUKSI_PULPING2 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR_KADAR_AIR ON " + TABLE_JEMUR_KADAR_AIR + "." + COL_ID_PRODUKSI_KADAR_AIR + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR1 ON " + TABLE_JEMUR1 + "." + COL_ID_PRODUKSI_JEMUR1 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR2 ON " + TABLE_JEMUR2 + "." + COL_ID_PRODUKSI_JEMUR2 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_HULLING ON " + TABLE_HULLING + "." + COL_ID_PRODUKSI_HULLING + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_SUTON_GRADER ON " + TABLE_SUTON_GRADER + "." + COL_ID_PRODUKSI_SUTON_GRADER + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_SIZE_GRADING ON " + TABLE_SIZE_GRADING + "." + COL_ID_PRODUKSI_SIZE_GRADING + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_COLOR_SORTER ON " + TABLE_COLOR_SORTER + "." + COL_ID_PRODUKSI_COLOR_SORTER + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_HAND_PICK ON " + TABLE_HAND_PICK + "." + COL_ID_PRODUKSI_HAND_PICK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI
            , null)

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
                produksi.status = result.getString(result.getColumnIndex(COL_STATUS_PRODUKSI))

                var petik = Petik()
                petik.id_petik = result.getString(result.getColumnIndex(COL_ID_PETIK)).toInt()
                petik.id_petik_produksi = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PETIK)).toInt()
                petik.tgl_petik = result.getString(result.getColumnIndex(COL_TGL_PETIK))
                petik.berat = result.getString(result.getColumnIndex(COL_BERAT_PETIK)).toDouble()
                petik.biaya_petik = result.getString(result.getColumnIndex(COL_BIAYA_PETIK)).toInt()
                petik.biaya_ojek = result.getString(result.getColumnIndex(COL_BIAYA_OJEK)).toInt()
                petik.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PETIK)).toInt()

                var ColorSorter = colorSorter()
                ColorSorter.id = result.getString(result.getColumnIndex(COL_ID_COLOR_SORTER)).toInt()
                ColorSorter.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_COLOR_SORTER)).toInt()
                ColorSorter.tanggal = result.getString(result.getColumnIndex(COL_TGL_COLOR_SORTER))
                ColorSorter.berat = result.getString(result.getColumnIndex(COL_BERAT_COLOR_SORTER)).toDouble()
                ColorSorter.biaya = result.getString(result.getColumnIndex(COL_BIAYA_COLOR_SORTER)).toInt()

                var fermentasi = Fermentasi()
                fermentasi.id = result.getString(result.getColumnIndex(COL_ID_FERMENTASI)).toInt()
                fermentasi.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_FERMENTASI)).toInt()
                fermentasi.tanggal = result.getString(result.getColumnIndex(COL_TGL_FERMENTASI))
                fermentasi.berat = result.getString(result.getColumnIndex(COL_BERAT_FERMENTASI)).toDouble()
                fermentasi.biaya_fermentasi= result.getString(result.getColumnIndex(COL_BIAYA_FERMENTASI)).toInt()
                fermentasi.biaya_muat = result.getString(result.getColumnIndex(COL_BIAYA_MUAT_FERMENTASI)).toInt()

                var handPick = handPick()
                handPick.id = result.getString(result.getColumnIndex(COL_ID_HAND_PICK)).toInt()
                handPick.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_HAND_PICK)).toInt()
                handPick.tanggal = result.getString(result.getColumnIndex(COL_TGL_HAND_PICK))
                handPick.berat = result.getString(result.getColumnIndex(COL_BERAT_HAND_PICK)).toDouble()
                handPick.biaya = result.getString(result.getColumnIndex(COL_BIAYA_HAND_PICK)).toInt()

                var hulling = Hulling()
                hulling.id = result.getString(result.getColumnIndex(COL_ID_HULLING)).toInt()
                hulling.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_HULLING)).toInt()
                hulling.tanggal = result.getString(result.getColumnIndex(COL_TGL_HULLING))
                hulling.berat = result.getString(result.getColumnIndex(COL_BERAT_HULLING)).toDouble()
                hulling.kadarAir = result.getString(result.getColumnIndex(COL_KDR_AIR_HULLING)).toDouble()
                hulling.biaya = result.getString(result.getColumnIndex(COL_BIAYA_HULLING)).toInt()

                var Jemursatu = jemurSatu()
                Jemursatu.id = result.getString(result.getColumnIndex(COL_ID_JEMUR1)).toInt()
                Jemursatu.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_JEMUR1)).toInt()
                Jemursatu.tanggal = result.getString(result.getColumnIndex(COL_TGL_JEMUR1))
                Jemursatu.berat = result.getString(result.getColumnIndex(COL_BERAT_JEMUR1)).toDouble()
                Jemursatu.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR1)).toInt()

                var Jemurdua = jemurDua()
                Jemurdua.id = result.getString(result.getColumnIndex(COL_ID_JEMUR2)).toInt()
                Jemurdua.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_JEMUR2)).toInt()
                Jemurdua.tanggal = result.getString(result.getColumnIndex(COL_TGL_JEMUR2))
                Jemurdua.berat = result.getString(result.getColumnIndex(COL_BERAT_JEMUR2)).toDouble()
                Jemurdua.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR2)).toInt()

                var JemurKadarAir = jemurKadarAir()
                JemurKadarAir.id = result.getString(result.getColumnIndex(COL_ID_KADAR_AIR)).toInt()
                JemurKadarAir.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_KADAR_AIR)).toInt()
                JemurKadarAir.tanggal = result.getString(result.getColumnIndex(COL_TGL_KADAR_AIR))
                JemurKadarAir.berat = result.getString(result.getColumnIndex(COL_BERAT_KADAR_AIR)).toDouble()
                JemurKadarAir.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR_KADAR_AIR)).toInt()

                var Pulping1 = pulpingSatu()
                Pulping1.id = result.getString(result.getColumnIndex(COL_ID_PULPING1)).toInt()
                Pulping1.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PULPING1)).toInt()
                Pulping1.tanggal = result.getString(result.getColumnIndex(COL_TGL_PULPING1))
                Pulping1.berat = result.getString(result.getColumnIndex(COL_BERAT_PULPING1)).toDouble()
                Pulping1.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PULPING1)).toInt()
                Pulping1.biaya_fermentasi = result.getString(result.getColumnIndex(COL_BIAYA_FERMENTASI_PULPING1)).toInt()
                Pulping1.biaya_muat = result.getString(result.getColumnIndex(COL_BIAYA_MUAT_PULPING1)).toInt()
                Pulping1.biaya_jemur = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR_PULPING1)).toInt()
                Pulping1.biaya_pulping = result.getString(result.getColumnIndex(COL_BIAYA_PULPING1)).toInt()

                var Pulping2 = pulpingDua()
                Pulping2.id = result.getString(result.getColumnIndex(COL_ID_PULPING2)).toInt()
                Pulping2.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PULPING2)).toInt()
                Pulping2.tanggal = result.getString(result.getColumnIndex(COL_TGL_PULPING2))
                Pulping2.berat = result.getString(result.getColumnIndex(COL_BERAT_PULPING2)).toDouble()
                Pulping2.biaya = result.getString(result.getColumnIndex(COL_BIAYA_PULPING2)).toInt()

                var SizeGrading = sizeGrading()
                SizeGrading.id = result.getString(result.getColumnIndex(COL_ID_SIZE_GRADING)).toInt()
                SizeGrading.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_SIZE_GRADING)).toInt()
                SizeGrading.tanggal = result.getString(result.getColumnIndex(COL_TGL_SIZE_GRADING))
                SizeGrading.berat = result.getString(result.getColumnIndex(COL_BERAT_SIZE_GRADING)).toDouble()
                SizeGrading.biaya = result.getString(result.getColumnIndex(COL_BIAYA_SIZE_GRADING)).toInt()

                var SutonGrader = sutonGrader()
                SutonGrader.id = result.getString(result.getColumnIndex(COL_ID_SUTON_GRADER)).toInt()
                SutonGrader.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_SUTON_GRADER)).toInt()
                SutonGrader.tanggal = result.getString(result.getColumnIndex(COL_TGL_SUTON_GRADER))
                SutonGrader.berat = result.getString(result.getColumnIndex(COL_BERAT_SUTON_GRADER)).toDouble()
                SutonGrader.biaya = result.getString(result.getColumnIndex(COL_BIAYA_SUTON_GRADER)).toInt()

                var transportasi = Transportasi()
                transportasi.id = result.getString(result.getColumnIndex(COL_ID_TRANSPORTASI)).toInt()
                transportasi.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_TRANSPORTASI)).toInt()
                transportasi.tanggal = result.getString(result.getColumnIndex(COL_TGL_TRANSPORTASI))
                transportasi.berat = result.getString(result.getColumnIndex(COL_BERAT_TRANSPORTASI)).toDouble()
                transportasi.biaya_bongkar = result.getString(result.getColumnIndex(COL_BIAYA_BONGKAR_TRANSPORTASI)).toInt()
                transportasi.biaya_kawal = result.getString(result.getColumnIndex(COL_BIAYA_KAWAL_TRANSPORTASI)).toInt()
                transportasi.biaya_transport = result.getString(result.getColumnIndex(COL_BIAYA_TRANSPORT)).toInt()

                var produk = Produk(
                    produksi,
                    petik,
                    fermentasi,
                    transportasi,
                    Pulping1,
                    Pulping2,
                    JemurKadarAir,
                    Jemursatu,
                    Jemurdua,
                    hulling,
                    SutonGrader,
                    SizeGrading,
                    ColorSorter,
                    handPick )

                list.add(produk)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun getAllDataSelesai(): MutableList<Produk> {
        var  list : MutableList<Produk> = ArrayList()
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PRODUKSI" +
                " JOIN $TABLE_PETIK ON " + TABLE_PETIK + "." + COL_ID_PRODUKSI_PETIK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_FERMEN ON " + TABLE_FERMEN + "." + COL_ID_PRODUKSI_FERMENTASI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_TRANSPORTASI ON " + TABLE_TRANSPORTASI + "." + COL_ID_PRODUKSI_TRANSPORTASI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_PULPING1 ON " + TABLE_PULPING1 + "." + COL_ID_PRODUKSI_PULPING1 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_PULPING2 ON " + TABLE_PULPING2 + "." + COL_ID_PRODUKSI_PULPING2 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR_KADAR_AIR ON " + TABLE_JEMUR_KADAR_AIR + "." + COL_ID_PRODUKSI_KADAR_AIR + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR1 ON " + TABLE_JEMUR1 + "." + COL_ID_PRODUKSI_JEMUR1 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR2 ON " + TABLE_JEMUR2 + "." + COL_ID_PRODUKSI_JEMUR2 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_HULLING ON " + TABLE_HULLING + "." + COL_ID_PRODUKSI_HULLING + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_SUTON_GRADER ON " + TABLE_SUTON_GRADER + "." + COL_ID_PRODUKSI_SUTON_GRADER + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_SIZE_GRADING ON " + TABLE_SIZE_GRADING + "." + COL_ID_PRODUKSI_SIZE_GRADING + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_COLOR_SORTER ON " + TABLE_COLOR_SORTER + "." + COL_ID_PRODUKSI_COLOR_SORTER + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_HAND_PICK ON " + TABLE_HAND_PICK + "." + COL_ID_PRODUKSI_HAND_PICK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " WHERE " + COL_STATUS_PRODUKSI + "=\"Selesai\""
            , null)

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
                produksi.status = result.getString(result.getColumnIndex(COL_STATUS_PRODUKSI))

                var petik = Petik()
                petik.id_petik = result.getString(result.getColumnIndex(COL_ID_PETIK)).toInt()
                petik.id_petik_produksi = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PETIK)).toInt()
                petik.tgl_petik = result.getString(result.getColumnIndex(COL_TGL_PETIK))
                petik.berat = result.getString(result.getColumnIndex(COL_BERAT_PETIK)).toDouble()
                petik.biaya_petik = result.getString(result.getColumnIndex(COL_BIAYA_PETIK)).toInt()
                petik.biaya_ojek = result.getString(result.getColumnIndex(COL_BIAYA_OJEK)).toInt()
                petik.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PETIK)).toInt()

                var ColorSorter = colorSorter()
                ColorSorter.id = result.getString(result.getColumnIndex(COL_ID_COLOR_SORTER)).toInt()
                ColorSorter.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_COLOR_SORTER)).toInt()
                ColorSorter.tanggal = result.getString(result.getColumnIndex(COL_TGL_COLOR_SORTER))
                ColorSorter.berat = result.getString(result.getColumnIndex(COL_BERAT_COLOR_SORTER)).toDouble()
                ColorSorter.biaya = result.getString(result.getColumnIndex(COL_BIAYA_COLOR_SORTER)).toInt()

                var fermentasi = Fermentasi()
                fermentasi.id = result.getString(result.getColumnIndex(COL_ID_FERMENTASI)).toInt()
                fermentasi.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_FERMENTASI)).toInt()
                fermentasi.tanggal = result.getString(result.getColumnIndex(COL_TGL_FERMENTASI))
                fermentasi.berat = result.getString(result.getColumnIndex(COL_BERAT_FERMENTASI)).toDouble()
                fermentasi.biaya_fermentasi= result.getString(result.getColumnIndex(COL_BIAYA_FERMENTASI)).toInt()
                fermentasi.biaya_muat = result.getString(result.getColumnIndex(COL_BIAYA_MUAT_FERMENTASI)).toInt()

                var handPick = handPick()
                handPick.id = result.getString(result.getColumnIndex(COL_ID_HAND_PICK)).toInt()
                handPick.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_HAND_PICK)).toInt()
                handPick.tanggal = result.getString(result.getColumnIndex(COL_TGL_HAND_PICK))
                handPick.berat = result.getString(result.getColumnIndex(COL_BERAT_HAND_PICK)).toDouble()
                handPick.biaya = result.getString(result.getColumnIndex(COL_BIAYA_HAND_PICK)).toInt()

                var hulling = Hulling()
                hulling.id = result.getString(result.getColumnIndex(COL_ID_HULLING)).toInt()
                hulling.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_HULLING)).toInt()
                hulling.tanggal = result.getString(result.getColumnIndex(COL_TGL_HULLING))
                hulling.berat = result.getString(result.getColumnIndex(COL_BERAT_HULLING)).toDouble()
                hulling.kadarAir = result.getString(result.getColumnIndex(COL_KDR_AIR_HULLING)).toDouble()
                hulling.biaya = result.getString(result.getColumnIndex(COL_BIAYA_HULLING)).toInt()

                var Jemursatu = jemurSatu()
                Jemursatu.id = result.getString(result.getColumnIndex(COL_ID_JEMUR1)).toInt()
                Jemursatu.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_JEMUR1)).toInt()
                Jemursatu.tanggal = result.getString(result.getColumnIndex(COL_TGL_JEMUR1))
                Jemursatu.berat = result.getString(result.getColumnIndex(COL_BERAT_JEMUR1)).toDouble()
                Jemursatu.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR1)).toInt()

                var Jemurdua = jemurDua()
                Jemurdua.id = result.getString(result.getColumnIndex(COL_ID_JEMUR2)).toInt()
                Jemurdua.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_JEMUR2)).toInt()
                Jemurdua.tanggal = result.getString(result.getColumnIndex(COL_TGL_JEMUR2))
                Jemurdua.berat = result.getString(result.getColumnIndex(COL_BERAT_JEMUR2)).toDouble()
                Jemurdua.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR2)).toInt()

                var JemurKadarAir = jemurKadarAir()
                JemurKadarAir.id = result.getString(result.getColumnIndex(COL_ID_KADAR_AIR)).toInt()
                JemurKadarAir.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_KADAR_AIR)).toInt()
                JemurKadarAir.tanggal = result.getString(result.getColumnIndex(COL_TGL_KADAR_AIR))
                JemurKadarAir.berat = result.getString(result.getColumnIndex(COL_BERAT_KADAR_AIR)).toDouble()
                JemurKadarAir.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR_KADAR_AIR)).toInt()

                var Pulping1 = pulpingSatu()
                Pulping1.id = result.getString(result.getColumnIndex(COL_ID_PULPING1)).toInt()
                Pulping1.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PULPING1)).toInt()
                Pulping1.tanggal = result.getString(result.getColumnIndex(COL_TGL_PULPING1))
                Pulping1.berat = result.getString(result.getColumnIndex(COL_BERAT_PULPING1)).toDouble()
                Pulping1.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PULPING1)).toInt()
                Pulping1.biaya_fermentasi = result.getString(result.getColumnIndex(COL_BIAYA_FERMENTASI_PULPING1)).toInt()
                Pulping1.biaya_muat = result.getString(result.getColumnIndex(COL_BIAYA_MUAT_PULPING1)).toInt()
                Pulping1.biaya_jemur = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR_PULPING1)).toInt()
                Pulping1.biaya_pulping = result.getString(result.getColumnIndex(COL_BIAYA_PULPING1)).toInt()

                var Pulping2 = pulpingDua()
                Pulping2.id = result.getString(result.getColumnIndex(COL_ID_PULPING2)).toInt()
                Pulping2.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PULPING2)).toInt()
                Pulping2.tanggal = result.getString(result.getColumnIndex(COL_TGL_PULPING2))
                Pulping2.berat = result.getString(result.getColumnIndex(COL_BERAT_PULPING2)).toDouble()
                Pulping2.biaya = result.getString(result.getColumnIndex(COL_BIAYA_PULPING2)).toInt()

                var SizeGrading = sizeGrading()
                SizeGrading.id = result.getString(result.getColumnIndex(COL_ID_SIZE_GRADING)).toInt()
                SizeGrading.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_SIZE_GRADING)).toInt()
                SizeGrading.tanggal = result.getString(result.getColumnIndex(COL_TGL_SIZE_GRADING))
                SizeGrading.berat = result.getString(result.getColumnIndex(COL_BERAT_SIZE_GRADING)).toDouble()
                SizeGrading.biaya = result.getString(result.getColumnIndex(COL_BIAYA_SIZE_GRADING)).toInt()

                var SutonGrader = sutonGrader()
                SutonGrader.id = result.getString(result.getColumnIndex(COL_ID_SUTON_GRADER)).toInt()
                SutonGrader.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_SUTON_GRADER)).toInt()
                SutonGrader.tanggal = result.getString(result.getColumnIndex(COL_TGL_SUTON_GRADER))
                SutonGrader.berat = result.getString(result.getColumnIndex(COL_BERAT_SUTON_GRADER)).toDouble()
                SutonGrader.biaya = result.getString(result.getColumnIndex(COL_BIAYA_SUTON_GRADER)).toInt()

                var transportasi = Transportasi()
                transportasi.id = result.getString(result.getColumnIndex(COL_ID_TRANSPORTASI)).toInt()
                transportasi.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_TRANSPORTASI)).toInt()
                transportasi.tanggal = result.getString(result.getColumnIndex(COL_TGL_TRANSPORTASI))
                transportasi.berat = result.getString(result.getColumnIndex(COL_BERAT_TRANSPORTASI)).toDouble()
                transportasi.biaya_bongkar = result.getString(result.getColumnIndex(COL_BIAYA_BONGKAR_TRANSPORTASI)).toInt()
                transportasi.biaya_kawal = result.getString(result.getColumnIndex(COL_BIAYA_KAWAL_TRANSPORTASI)).toInt()
                transportasi.biaya_transport = result.getString(result.getColumnIndex(COL_BIAYA_TRANSPORT)).toInt()

                var produk = Produk(
                    produksi,
                    petik,
                    fermentasi,
                    transportasi,
                    Pulping1,
                    Pulping2,
                    JemurKadarAir,
                    Jemursatu,
                    Jemurdua,
                    hulling,
                    SutonGrader,
                    SizeGrading,
                    ColorSorter,
                    handPick )

                list.add(produk)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun getAllDataConditional(idData: Int): Produk {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PRODUKSI" +
                " JOIN $TABLE_PETIK ON " + TABLE_PETIK + "." + COL_ID_PRODUKSI_PETIK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_FERMEN ON " + TABLE_FERMEN + "." + COL_ID_PRODUKSI_FERMENTASI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_TRANSPORTASI ON " + TABLE_TRANSPORTASI + "." + COL_ID_PRODUKSI_TRANSPORTASI + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_PULPING1 ON " + TABLE_PULPING1 + "." + COL_ID_PRODUKSI_PULPING1 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_PULPING2 ON " + TABLE_PULPING2 + "." + COL_ID_PRODUKSI_PULPING2 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR_KADAR_AIR ON " + TABLE_JEMUR_KADAR_AIR + "." + COL_ID_PRODUKSI_KADAR_AIR + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR1 ON " + TABLE_JEMUR1 + "." + COL_ID_PRODUKSI_JEMUR1 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_JEMUR2 ON " + TABLE_JEMUR2 + "." + COL_ID_PRODUKSI_JEMUR2 + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_HULLING ON " + TABLE_HULLING + "." + COL_ID_PRODUKSI_HULLING + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_SUTON_GRADER ON " + TABLE_SUTON_GRADER + "." + COL_ID_PRODUKSI_SUTON_GRADER + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_SIZE_GRADING ON " + TABLE_SIZE_GRADING + "." + COL_ID_PRODUKSI_SIZE_GRADING + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_COLOR_SORTER ON " + TABLE_COLOR_SORTER + "." + COL_ID_PRODUKSI_COLOR_SORTER + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " JOIN $TABLE_HAND_PICK ON " + TABLE_HAND_PICK + "." + COL_ID_PRODUKSI_HAND_PICK + "="  + TABLE_PRODUKSI + "." + COL_ID_PRODUKSI +
                " WHERE " + COL_STATUS_PRODUKSI + "=" + idData.toString()
            , null)
        result.moveToFirst()
        println(result.getString(result.getColumnIndex(COL_ID_PRODUKSI)).toInt())
        var produksi = Produksi()
        produksi.id_produksi = result.getString(result.getColumnIndex(COL_ID_PRODUKSI)).toInt()
        produksi.sumber = result.getString(result.getColumnIndex(COL_SUMBER))
        produksi.beli_dari = result.getString(result.getColumnIndex(COL_BELI_DARI))
        produksi.bentuk = result.getString(result.getColumnIndex(COL_BENTUK))
        produksi.varietas = result.getString(result.getColumnIndex(COL_VARI))
        produksi.blok = result.getString(result.getColumnIndex(COL_BLOKP))
        produksi.proses = result.getString(result.getColumnIndex(COL_PROSES_PRODUKSI))
        produksi.status = result.getString(result.getColumnIndex(COL_STATUS_PRODUKSI))

        var petik = Petik()
        petik.id_petik = result.getString(result.getColumnIndex(COL_ID_PETIK)).toInt()
        petik.id_petik_produksi = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PETIK)).toInt()
        petik.tgl_petik = result.getString(result.getColumnIndex(COL_TGL_PETIK))
        petik.berat = result.getString(result.getColumnIndex(COL_BERAT_PETIK)).toDouble()
        petik.biaya_petik = result.getString(result.getColumnIndex(COL_BIAYA_PETIK)).toInt()
        petik.biaya_ojek = result.getString(result.getColumnIndex(COL_BIAYA_OJEK)).toInt()
        petik.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PETIK)).toInt()

        var ColorSorter = colorSorter()
        ColorSorter.id = result.getString(result.getColumnIndex(COL_ID_COLOR_SORTER)).toInt()
        ColorSorter.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_COLOR_SORTER)).toInt()
        ColorSorter.tanggal = result.getString(result.getColumnIndex(COL_TGL_COLOR_SORTER))
        ColorSorter.berat = result.getString(result.getColumnIndex(COL_BERAT_COLOR_SORTER)).toDouble()
        ColorSorter.biaya = result.getString(result.getColumnIndex(COL_BIAYA_COLOR_SORTER)).toInt()

        var fermentasi = Fermentasi()
        fermentasi.id = result.getString(result.getColumnIndex(COL_ID_FERMENTASI)).toInt()
        fermentasi.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_FERMENTASI)).toInt()
        fermentasi.tanggal = result.getString(result.getColumnIndex(COL_TGL_FERMENTASI))
        fermentasi.berat = result.getString(result.getColumnIndex(COL_BERAT_FERMENTASI)).toDouble()
        fermentasi.biaya_fermentasi= result.getString(result.getColumnIndex(COL_BIAYA_FERMENTASI)).toInt()
        fermentasi.biaya_muat = result.getString(result.getColumnIndex(COL_BIAYA_MUAT_FERMENTASI)).toInt()

        var handPick = handPick()
        handPick.id = result.getString(result.getColumnIndex(COL_ID_HAND_PICK)).toInt()
        handPick.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_HAND_PICK)).toInt()
        handPick.tanggal = result.getString(result.getColumnIndex(COL_TGL_HAND_PICK))
        handPick.berat = result.getString(result.getColumnIndex(COL_BERAT_HAND_PICK)).toDouble()
        handPick.biaya = result.getString(result.getColumnIndex(COL_BIAYA_HAND_PICK)).toInt()

        var hulling = Hulling()
        hulling.id = result.getString(result.getColumnIndex(COL_ID_HULLING)).toInt()
        hulling.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_HULLING)).toInt()
        hulling.tanggal = result.getString(result.getColumnIndex(COL_TGL_HULLING))
        hulling.berat = result.getString(result.getColumnIndex(COL_BERAT_HULLING)).toDouble()
        hulling.kadarAir = result.getString(result.getColumnIndex(COL_KDR_AIR_HULLING)).toDouble()
        hulling.biaya = result.getString(result.getColumnIndex(COL_BIAYA_HULLING)).toInt()

        var Jemursatu = jemurSatu()
        Jemursatu.id = result.getString(result.getColumnIndex(COL_ID_JEMUR1)).toInt()
        Jemursatu.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_JEMUR1)).toInt()
        Jemursatu.tanggal = result.getString(result.getColumnIndex(COL_TGL_JEMUR1))
        Jemursatu.berat = result.getString(result.getColumnIndex(COL_BERAT_JEMUR1)).toDouble()
        Jemursatu.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR1)).toInt()

        var Jemurdua = jemurDua()
        Jemurdua.id = result.getString(result.getColumnIndex(COL_ID_JEMUR2)).toInt()
        Jemurdua.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_JEMUR2)).toInt()
        Jemurdua.tanggal = result.getString(result.getColumnIndex(COL_TGL_JEMUR2))
        Jemurdua.berat = result.getString(result.getColumnIndex(COL_BERAT_JEMUR2)).toDouble()
        Jemurdua.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR2)).toInt()

        var JemurKadarAir = jemurKadarAir()
        JemurKadarAir.id = result.getString(result.getColumnIndex(COL_ID_KADAR_AIR)).toInt()
        JemurKadarAir.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_KADAR_AIR)).toInt()
        JemurKadarAir.tanggal = result.getString(result.getColumnIndex(COL_TGL_KADAR_AIR))
        JemurKadarAir.berat = result.getString(result.getColumnIndex(COL_BERAT_KADAR_AIR)).toDouble()
        JemurKadarAir.biaya = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR_KADAR_AIR)).toInt()

        var Pulping1 = pulpingSatu()
        Pulping1.id = result.getString(result.getColumnIndex(COL_ID_PULPING1)).toInt()
        Pulping1.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PULPING1)).toInt()
        Pulping1.tanggal = result.getString(result.getColumnIndex(COL_TGL_PULPING1))
        Pulping1.berat = result.getString(result.getColumnIndex(COL_BERAT_PULPING1)).toDouble()
        Pulping1.biaya_cuci = result.getString(result.getColumnIndex(COL_BIAYA_CUCI_PULPING1)).toInt()
        Pulping1.biaya_fermentasi = result.getString(result.getColumnIndex(COL_BIAYA_FERMENTASI_PULPING1)).toInt()
        Pulping1.biaya_muat = result.getString(result.getColumnIndex(COL_BIAYA_MUAT_PULPING1)).toInt()
        Pulping1.biaya_jemur = result.getString(result.getColumnIndex(COL_BIAYA_JEMUR_PULPING1)).toInt()
        Pulping1.biaya_pulping = result.getString(result.getColumnIndex(COL_BIAYA_PULPING1)).toInt()

        var Pulping2 = pulpingDua()
        Pulping2.id = result.getString(result.getColumnIndex(COL_ID_PULPING2)).toInt()
        Pulping2.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_PULPING2)).toInt()
        Pulping2.tanggal = result.getString(result.getColumnIndex(COL_TGL_PULPING2))
        Pulping2.berat = result.getString(result.getColumnIndex(COL_BERAT_PULPING2)).toDouble()
        Pulping2.biaya = result.getString(result.getColumnIndex(COL_BIAYA_PULPING2)).toInt()

        var SizeGrading = sizeGrading()
        SizeGrading.id = result.getString(result.getColumnIndex(COL_ID_SIZE_GRADING)).toInt()
        SizeGrading.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_SIZE_GRADING)).toInt()
        SizeGrading.tanggal = result.getString(result.getColumnIndex(COL_TGL_SIZE_GRADING))
        SizeGrading.berat = result.getString(result.getColumnIndex(COL_BERAT_SIZE_GRADING)).toDouble()
        SizeGrading.biaya = result.getString(result.getColumnIndex(COL_BIAYA_SIZE_GRADING)).toInt()

        var SutonGrader = sutonGrader()
        SutonGrader.id = result.getString(result.getColumnIndex(COL_ID_SUTON_GRADER)).toInt()
        SutonGrader.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_SUTON_GRADER)).toInt()
        SutonGrader.tanggal = result.getString(result.getColumnIndex(COL_TGL_SUTON_GRADER))
        SutonGrader.berat = result.getString(result.getColumnIndex(COL_BERAT_SUTON_GRADER)).toDouble()
        SutonGrader.biaya = result.getString(result.getColumnIndex(COL_BIAYA_SUTON_GRADER)).toInt()

        var transportasi = Transportasi()
        transportasi.id = result.getString(result.getColumnIndex(COL_ID_TRANSPORTASI)).toInt()
        transportasi.id2 = result.getString(result.getColumnIndex(COL_ID_PRODUKSI_TRANSPORTASI)).toInt()
        transportasi.tanggal = result.getString(result.getColumnIndex(COL_TGL_TRANSPORTASI))
        transportasi.berat = result.getString(result.getColumnIndex(COL_BERAT_TRANSPORTASI)).toDouble()
        transportasi.biaya_bongkar = result.getString(result.getColumnIndex(COL_BIAYA_BONGKAR_TRANSPORTASI)).toInt()
        transportasi.biaya_kawal = result.getString(result.getColumnIndex(COL_BIAYA_KAWAL_TRANSPORTASI)).toInt()
        transportasi.biaya_transport = result.getString(result.getColumnIndex(COL_BIAYA_TRANSPORT)).toInt()

        var produk = Produk(
            produksi,
            petik,
            fermentasi,
            transportasi,
            Pulping1,
            Pulping2,
            JemurKadarAir,
            Jemursatu,
            Jemurdua,
            hulling,
            SutonGrader,
            SizeGrading,
            ColorSorter,
            handPick )

        result.close()
        db.close()
        return produk
    }

    fun getStepProses(nama: String): String {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_PROSES WHERE $COL_NAME=\"$nama\"", null)
        result.moveToFirst()
        return result.getString(result.getColumnIndex(COL_STEP))
    }

    fun insertFermentasi(f: Fermentasi): Boolean {
        val db = this.writableDatabase
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI_FERMENTASI, idProduksi)
        cv.put(COL_TGL_FERMENTASI, f.tanggal)
        cv.put(COL_BERAT_FERMENTASI, f.berat)
        cv.put(COL_BIAYA_FERMENTASI, f.biaya_fermentasi)
        cv.put(COL_BIAYA_MUAT_FERMENTASI, f.biaya_muat)
        var result = db.insert(TABLE_FERMEN, null, cv)

//        if (result == -1.toLong()) {
//            toastMessage("Gagal Insert Fermentasi")
//            return false
//        }else {
//            toastMessage("Berhasil Insert Fermentasi")
//            return true
//        }
        return true
    }

    fun updateFermentasi(id: Int, f: Fermentasi): Boolean {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(COL_TGL_FERMENTASI, f.tanggal)
        cv.put(COL_BERAT_FERMENTASI, f.berat)
        cv.put(COL_BIAYA_FERMENTASI, f.biaya_fermentasi)
        cv.put(COL_BIAYA_MUAT_FERMENTASI, f.biaya_muat)
        val result = db.update(TABLE_FERMEN, cv, COL_ID_PRODUKSI_FERMENTASI + "=" + id, null)

        if (result == -1) {
            toastMessage("Gagal Update Fermentasi")
            return false
        }else {
            toastMessage("Berhasil Update Fermentasi")
            return true
        }
    }

    fun insertTransportasi(t: Transportasi): Boolean {
        val db = this.writableDatabase
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI_TRANSPORTASI, idProduksi)
        cv.put(COL_TGL_TRANSPORTASI, t.tanggal)
        cv.put(COL_BERAT_TRANSPORTASI, t.berat)
        cv.put(COL_BIAYA_TRANSPORT, t.biaya_transport)
        cv.put(COL_BIAYA_KAWAL_TRANSPORTASI, t.biaya_kawal)
        cv.put(COL_BIAYA_BONGKAR_TRANSPORTASI, t.biaya_bongkar)
        var result = db.insert(TABLE_TRANSPORTASI, null, cv)

//        if (result == -1.toLong()) {
//            toastMessage("Gagal Insert Transportasi")
//            return false
//        }else {
//            toastMessage("Berhasil Insert Transportasi")
//            return true
//        }
        return true
    }

    fun updateTransportasi(id: Int, t: Transportasi): Boolean {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(COL_TGL_TRANSPORTASI, t.tanggal)
        cv.put(COL_BERAT_TRANSPORTASI, t.berat)
        cv.put(COL_BIAYA_TRANSPORT, t.biaya_transport)
        cv.put(COL_BIAYA_KAWAL_TRANSPORTASI, t.biaya_kawal)
        cv.put(COL_BIAYA_BONGKAR_TRANSPORTASI, t.biaya_bongkar)
        val result = db.update(TABLE_TRANSPORTASI, cv, COL_ID_PRODUKSI_TRANSPORTASI + "=" + id, null)

        if (result == -1) {
            toastMessage("Gagal Update Transportasi")
            return false
        }else {
            toastMessage("Berhasil Update Transportasi")
            return true
        }
    }

    fun updateStatus(id: Int, status: String): Boolean {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(COL_STATUS_PRODUKSI, status)
        val result = db.update(TABLE_PRODUKSI, cv, COL_ID_PRODUKSI + "=" + id, null)

        if (result == -1) {
            toastMessage("Gagal Update Status")
            return false
        }else {
            toastMessage("Berhasil Update Status")
            return true
        }
    }

    fun insertPulping1(p: pulpingSatu): Boolean {
        val db = this.writableDatabase
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cv = ContentValues()

        cv.put(COL_ID_PRODUKSI_PULPING1, idProduksi)
        cv.put(COL_TGL_PULPING1, p.tanggal)
        cv.put(COL_BERAT_PULPING1, p.berat)
        cv.put(COL_BIAYA_PULPING1, p.biaya_pulping)
        cv.put(COL_BIAYA_FERMENTASI_PULPING1, p.biaya_fermentasi)
        cv.put(COL_BIAYA_CUCI_PULPING1, p.biaya_cuci)
        cv.put(COL_BIAYA_JEMUR_PULPING1, p.biaya_jemur)
        cv.put(COL_BIAYA_MUAT_PULPING1, p.biaya_muat)
        var result = db.insert(TABLE_PULPING1, null, cv)

//        if (result == -1.toLong()) {
//            toastMessage("Gagal Insert Pulping 1")
//            return false
//        }else {
//            toastMessage("Berhasil Insert Pulping 1")
//            return true
//        }
        return true
    }

    fun updatePulping1(id: Int, p: pulpingSatu): Boolean {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(COL_TGL_PULPING1, p.tanggal)
        cv.put(COL_BERAT_PULPING1, p.berat)
        cv.put(COL_BIAYA_PULPING1, p.biaya_pulping)
        cv.put(COL_BIAYA_FERMENTASI_PULPING1, p.biaya_fermentasi)
        cv.put(COL_BIAYA_CUCI_PULPING1, p.biaya_cuci)
        cv.put(COL_BIAYA_JEMUR_PULPING1, p.biaya_jemur)
        cv.put(COL_BIAYA_MUAT_PULPING1, p.biaya_muat)
        val result = db.update(TABLE_PULPING1, cv, COL_ID_PRODUKSI_PULPING1 + "=" + id, null)

        if (result == -1) {
            toastMessage("Gagal Update Pulping 1")
            return false
        }else {
            toastMessage("Berhasil Update Pulping 1")
            return true
        }
    }

    fun <T: Standard> insertStandard(data: T, TABLE_NAME: String, A: String, B: String, C: String, D:String): Boolean {
        val db = this.writableDatabase
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cv = ContentValues()

        cv.put(A, idProduksi)
        cv.put(B, data.tanggal)
        cv.put(C, data.berat)
        cv.put(D, data.biaya)
        var result = db.insert(TABLE_NAME, null, cv)

//        if (result == -1.toLong()) {
//            toastMessage("Gagal Insert $TABLE_NAME")
//            return false
//        }else {
//            toastMessage("Berhasil Insert $TABLE_NAME")
//            return true
//        }
        return true
    }

    fun <T: Standard> updateStandard(id: Int, data: T, TABLE_NAME: String, A: String, B: String, C: String, D:String): Boolean {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(B, data.tanggal)
        cv.put(C, data.berat)
        cv.put(D, data.biaya)
        val result = db.update(TABLE_NAME, cv, A + "=" + id, null)

        if (result == -1) {
            toastMessage("Gagal Update Standard")
            return false
        }else {
            toastMessage("Berhasil Update Standard")
            return true
        }
    }

    fun <A: jemurKadarAir> insertKadarAir(data : A, TABLE_NAME: String, V: String, W:String, X: String, Y: String, Z: String): Boolean{
        val db = this.writableDatabase
        val idProduksi = getIdProduksi(TABLE_PRODUKSI)
        var cv = ContentValues()

        cv.put(V, idProduksi)
        cv.put(W, data.tanggal)
        cv.put(X, data.berat)
        cv.put(Y, data.kadarAir)
        cv.put(Z, data.biaya)

        var result = db.insert(TABLE_NAME, null, cv)

//        if (result == -1.toLong()) {
//            toastMessage("Gagal Insert $TABLE_NAME")
//            return false
//        }else {
//            toastMessage("Berhasil Insert $TABLE_NAME")
//            return true
//        }
        return true
    }

    fun <T: jemurKadarAir> updateKadarAir(id: Int, data: T, TABLE_NAME: String, V: String, W:String, X: String, Y: String, Z: String): Boolean {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(W, data.tanggal)
        cv.put(X, data.berat)
        cv.put(Y, data.kadarAir)
        cv.put(Z, data.biaya)
        val result = db.update(TABLE_NAME, cv, V + "=" + id, null)

        if (result == -1) {
            toastMessage("Gagal Update Kadar Air")
            return false
        }else {
            toastMessage("Berhasil Update Kadar Air")
            return true
        }
    }

    fun deleteConditional(TABLE_NAME: String, A: String, B: Any): Int {
        val db = this.writableDatabase

        val result = db.delete(TABLE_NAME, A + "=" + B, null)

//        if (result == -1) {
//            toastMessage("Gagal Menghapus Item")
//            return false
//        }else {
//            toastMessage("Berhasil Menghapus Item")
//            return true
//        }
        return result
    }

    fun getIdProduksi(TABLE_NAME: String): Int {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        result.moveToLast()
        return result.getString(result.getColumnIndex(COL_ID_PRODUKSI)).toInt()
    }

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
package frinsa.hpp.daftar_produksi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//1st Data bagian Recycler View
@Parcelize
data class ModelDaftarProduksi (
    var id: Int = 0,
    var tanggal:String = "",
    var blok:String = "",
    var varietas:String = "",
    var berat:Double = 0.0,
    var proses:String = "",
    var biaya:Int = 0,
    var tahap:String = ""
): Parcelable
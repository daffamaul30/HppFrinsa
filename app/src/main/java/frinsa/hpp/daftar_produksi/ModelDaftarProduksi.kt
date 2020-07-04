package frinsa.hpp.daftar_produksi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//1st Data bagian Recycler View
@Parcelize
data class ModelDaftarProduksi (
    var tanggal:String?,
    var blok:String?,
    var varietas:String?,
    var berat:Double?,
    var proses:String?,
    var biaya:Int?,
    var tahap:String?
): Parcelable
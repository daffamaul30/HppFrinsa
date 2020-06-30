package frinsa.hpp.data

data class ModelProduksi(
    var id_produksi : Int,
    var tgl_produksi : String,
    var sumber : String,
    var beli_dari : String,
    var bentuk : String,
    var blok : String,
    var berat : Double,
    var proses : String,
    var status : String
)
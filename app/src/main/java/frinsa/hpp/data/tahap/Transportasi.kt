package frinsa.hpp.data.tahap

data class Transportasi(
    var id : Int = 0,
    var id2 : Int = 0,
    var tanggal : String = "",
    var berat : Double = 0.0,
    var biaya_transport : Int = 0,
    var biaya_kawal : Int = 0,
    var biaya_bongkar : Int = 0
)
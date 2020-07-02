package frinsa.hpp.data.tahap

data class pulpingSatu(
    var id : Int,
    var id2 : Int,
    var tanggal : String,
    var berat : Double,
    var biaya_pulping : Int,
    var biaya_fermentasi : Int,
    var biaya_cuci : Int,
    var biaya_jemur : Int,
    var biaya_muat : Int
)
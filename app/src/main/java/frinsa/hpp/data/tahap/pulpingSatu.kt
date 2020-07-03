package frinsa.hpp.data.tahap

data class pulpingSatu(
    var id : Int = 0,
    var id2 : Int = 0,
    var tanggal : String = "",
    var berat : Double = 0.0,
    var biaya_pulping : Int = 0,
    var biaya_fermentasi : Int = 0,
    var biaya_cuci : Int = 0,
    var biaya_jemur : Int = 0,
    var biaya_muat : Int = 0
)
package frinsa.hpp.data

class Produksi {
    private lateinit var petik : Petik
    private lateinit var produk : ModelProduksi

    constructor(petik: Petik, produk: ModelProduksi){
        this.petik = petik
        this.produk = produk
    }
}
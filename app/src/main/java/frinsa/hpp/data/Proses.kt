package frinsa.hpp.data

import android.content.Context

data class Proses(
    var id:Int = 0,
    var name:String = ""
)

//class Proses {
//    private val blok: MutableList<String> = ArrayList()
//    var id: Int = 0
//    var name: String = ""
//    lateinit var context: Context
//    private lateinit var db: DBPanen
//
//    constructor(name: String) {
//        this.name = name
//    }
//
//    constructor(context: Context) {
//        this.context = context
//        db = DBPanen(this.context)
//    }
//
//    constructor(){}
//}
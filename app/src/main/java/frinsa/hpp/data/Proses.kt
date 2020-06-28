package frinsa.hpp.data

import android.content.Context

class Proses {
    private val blok: MutableList<String> = ArrayList()
    var id: Int = 0
    var name: String = ""
    var step: String = ""
    lateinit var context: Context
    private lateinit var db: DBPanen

    constructor(context: Context) {
        this.context = context
        db = DBPanen(this.context)
    }

    constructor(name: String, step: String) {
        this.name = name
        this.step = step
    }

    constructor(){}

    fun addProses(name: String, step: String) {
        val proses = Proses(name, step)
        db.insertProses(proses)
    }
}
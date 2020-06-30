package frinsa.hpp.data

import android.content.Context

class Proses {
    private val proses: MutableList<String> = ArrayList()
    private var daftarStep = mapOf<String, String>().toMutableMap()
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

    fun getProses(): MutableList<String> {
        val listP = db.readProses()
        proses.clear()
        proses.add(0, "Pilih Proses")
        if (listP.size > 0) {
            for (i in 0 until listP.size) {
                proses.add(listP[i].name)
            }
        }
        return proses
    }

    fun getStepProses(): MutableMap<String, String> {
        val listP = db.readProses()
        if (listP.size > 0) {
            for (i in 0 until listP.size) {
                daftarStep.put(listP[i].name, listP[i].step)
            }
        }
        return daftarStep
    }
}
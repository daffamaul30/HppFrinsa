package frinsa.hpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class InputPanen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_panen)

        //set action bar title
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Hasil Panen"
        }

        //DatePicker

        //Spinner Varietas
        val spinner:Spinner = findViewById(R.id.spinner_varietas)

//        val varietas:List<String> = ArrayList<>()
        val varietas: MutableList<String> = ArrayList()
        varietas.add(0, "Pilih Varietas")
        varietas.add("Arabica")
        varietas.add("Robusta")

        //Style and populate the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, varietas)
        //Dropdown layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Attaching the data to spinner
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (parent.getItemAtPosition(position) === "Pilih Varietas" ) {

                } else {
                    var item = parent.getItemAtPosition(position).toString()
                    Toast.makeText(parent.context, item, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //Spinner Blok

        //Spinner Proses
    }
}
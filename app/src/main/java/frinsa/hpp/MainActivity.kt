package frinsa.hpp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnMasuk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.input_username)
        edtPassword = findViewById(R.id.input_password)
        btnMasuk = findViewById(R.id.btn_masuk)

        btnMasuk.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_masuk) {
            val inputUsername = edtUsername.text.toString().trim()
            val inputPassword = edtPassword.text.toString().trim()

            var isEmptyFields = false
//            var isInvalidFormat = false

            if (inputUsername.isEmpty()) {
                isEmptyFields = true
                edtUsername.error ="Username tidak boleh kosong"
            }

            if (inputPassword.isEmpty()) {
                isEmptyFields = true
                edtPassword.error ="Password tidak boleh kosong"
            }

            if (inputUsername == "frinsa" && inputPassword == "frinsa" && !isEmptyFields) {
                var intent = Intent(this@MainActivity, Dashboard::class.java)
                startActivity(intent)
                finish()
            } else{
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                edtUsername.text = null
                edtPassword.text = null
            }
        }
    }

}
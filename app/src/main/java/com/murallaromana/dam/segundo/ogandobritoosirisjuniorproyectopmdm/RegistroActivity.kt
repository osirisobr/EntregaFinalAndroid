package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class RegistroActivity : AppCompatActivity() {


    lateinit var btHacerEfectivoRegistro: Button
    lateinit var etNombreR: EditText
    lateinit var etContraseñaR: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btHacerEfectivoRegistro=findViewById(R.id.btHacerEfectivoRegistro)
        etNombreR=findViewById(R.id.etNombreR)
        etContraseñaR=findViewById(R.id.etContraseñaR)


        btHacerEfectivoRegistro.setOnClickListener(){
            saveData()
            onBackPressed()



        }

    }

    fun saveData(){
        val nombreR = etNombreR.text.toString()
        val contraseñaR = etContraseñaR.text.toString()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply(){
            putString("NOMBRER",nombreR)
            putString("CONTRASEÑAR",contraseñaR)
        }.apply()

        Toast.makeText(this,"datos Guardados", Toast.LENGTH_SHORT).show()

    }

    private fun showAlert(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }

}
package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {


    private lateinit var btRegistrarse: Button

    private lateinit var etNombreL: EditText
    private lateinit var etContraseñaL: EditText
    private lateinit var btAcceder: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_OgandoBritoOsirisJuniorProyectoPMDM)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btRegistrarse = findViewById(R.id.btRegistrarse)
        btAcceder = findViewById(R.id.btAcceder)
        etNombreL = findViewById(R.id.etNombreL)
        etContraseñaL = findViewById(R.id.etContraseñaL)
        btAcceder = findViewById(R.id.btAcceder)




        loadData2()


        btAcceder.setOnClickListener(){

            val nombreL = etNombreL.text.toString()
            val contraseñaL = etContraseñaL.text.toString()
            val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)

            val editor = sharedPreferences.edit()
            editor.apply(){
                putString("NOMBREL",nombreL)
                putString("CONTRASEÑAL",contraseñaL)
            }.apply()


            loadData()

        }

        btRegistrarse.setOnClickListener() {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

    }
    fun loadData() {

        val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)

        val nombreL = sharedPreferences.getString("NOMBREL",null)
        val contraseñaL = sharedPreferences.getString("CONTRASEÑAL",null)
        val nombreR = sharedPreferences.getString("NOMBRER",null)
        val contraseñaR = sharedPreferences.getString("CONTRASEÑAR",null)

        if (nombreR == nombreL && contraseñaR == contraseñaL ) {
            val intent = Intent(this, PeliculasActivity::class.java)
            startActivity(intent)

        }else{
            Toast.makeText(this,"Datos Incorrectos", Toast.LENGTH_SHORT).show()
        }


        // tvNombre.text=nombreR
        // tvContraseña.text=contraseñaR


    }


    fun loadData2() {

        val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)



        val nombreL = sharedPreferences.getString("NOMBREL",null)
        val contraseñaL = sharedPreferences.getString("CONTRASEÑAL",null)
        val nombreR = sharedPreferences.getString("NOMBRER",null)
        val contraseñaR = sharedPreferences.getString("CONTRASEÑAR",null)

        if (nombreR == nombreL && contraseñaR == contraseñaL ) {
            val intent = Intent(this, PeliculasActivity::class.java)
            startActivity(intent)

        }

    }






















    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
    //.....Intent Llamar al director...

    //val callIntent: Intent = Uri.parse("tel:5551234").let { number ->
    //    Intent(Intent.ACTION_DIAL, number)
    //}
    //startActivity(callIntent)


}


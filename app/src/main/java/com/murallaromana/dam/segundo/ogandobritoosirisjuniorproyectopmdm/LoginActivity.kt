package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Api
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Token
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        val context = this


        etNombreL.setText("hola@hotmail.com")
        etContraseñaL.setText("1234")













        loadData2()


        btAcceder.setOnClickListener(){

            var usuario = etNombreL.text.toString()
            var contraseña = etContraseñaL.text.toString()

            val u = Usuario(usuario,contraseña)
            val llamadaApi: Call<Token> = RetrofictClient.apiRetrofit.login(u)

            llamadaApi.enqueue(object: Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                        Log.d("respuesta: onFailure", t.toString())

                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        Log.d("respuesta: onResponse", response.toString())

                    if (response.code() > 299 || response.code() < 200) {
                        // Muestro alerta: no se ha podido crear el usuario
                        Toast.makeText(context,"no se ha podido acceder", Toast.LENGTH_SHORT).show()

                    } else {
                        val token = response.body()?.token
                        Log.d("respuesta: token:", token.orEmpty())
                        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.apply(){
                            putString("TOKEN",token)
                        }.apply()
                        val intent = Intent(context, PeliculasActivity::class.java)
                        startActivity(intent)

                        // TODO: Muestro mensaje de usuario creado correctamente.

                        // TODO: Guardo en sharedPreferences el token

                        // TODO: Inicio nueva activity
                    }

                }
            })
            //hio






























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


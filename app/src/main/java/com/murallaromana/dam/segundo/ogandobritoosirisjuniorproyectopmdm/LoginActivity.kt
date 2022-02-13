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



        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val correo: String? = sharedPreferences.getString("correo", null)
        val Tkn: String? = sharedPreferences.getString("TOKEN", null)
        if (Tkn == null){
            btAcceder.setOnClickListener() {
                acceder()
            }
        }else{
            val intent = Intent(context, PeliculasActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (correo != null) {
            etNombreL.setText(correo)
        }

        btRegistrarse.setOnClickListener() {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
            btRegistrarse.isEnabled = false
        }
    }


    fun acceder() {

        val context = this
        var usuario = etNombreL.text.toString()
        var contraseña = etContraseñaL.text.toString()
        val u = Usuario(usuario, contraseña)
        val llamadaApi: Call<Token> = RetrofictClient.apiRetrofit.login(u)

        llamadaApi.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
                // TODO: alertdialog de "NO se ha podido acceder a la pagina"
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.d("respuesta: onResponse", response.toString())

                if (response.code() > 299 || response.code() < 200) {
                    Toast.makeText(context, "no se ha podido acceder", Toast.LENGTH_SHORT).show()

                } else {

                    val token: String? = response.body()?.token
                    Log.d("respuesta: token:", token.orEmpty())
                    val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.apply() {
                        putString("TOKEN", token)
                    }.apply()
                    val intent = Intent(context, PeliculasActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Aviso")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
}


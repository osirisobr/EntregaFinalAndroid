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
        var usuario = etNombreL.text.toString()
        var contraseña = etContraseñaL.text.toString()

        val llamadaAlApi: Call<List<Pelicula>> = RetrofictClient.apiRetrofit.getPeliculas("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxZjc5Nzc4ODgxM2Q2ZTRlNDVmZWQwMyIsImlhdCI6MTY0MzcwNDcxMiwiZXhwIjoxNjQzNzkxMTEyfQ.kpA3ZkCrKSNNFCWBGUT6IEAhdKQHvOOSh00t6pAyV9Q")
        llamadaAlApi.enqueue(object: Callback<List<Pelicula>>{
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>

            ) {
                if (response.isSuccessful){

                    Toast.makeText(context,"codigo en el rango de 200", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, PeliculasActivity::class.java)
                    startActivity(intent)


                }
                var listaPeliculas = response.body().toString()

                Toast.makeText(context, listaPeliculas, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("Prueba",t.message.toString())
            }
        } )






        loadData2()


        btAcceder.setOnClickListener(){

            val nombreL = etNombreL.text.toString()
            val contraseñaL = etContraseñaL.text.toString()
            val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)

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


        //configuroRetrofit
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://damapi.herokuapp.com/api/v1/")
        .build()



    val service = retrofit.create(Api::class.java)
  //  val loginCall = service.login(u)


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


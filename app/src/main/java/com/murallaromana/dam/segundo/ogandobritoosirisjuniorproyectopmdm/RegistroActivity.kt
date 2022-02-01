package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Token
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroActivity : AppCompatActivity() {


    lateinit var btHacerEfectivoRegistro: Button
    lateinit var etEmail: EditText
    lateinit var etContraseñaR: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btHacerEfectivoRegistro=findViewById(R.id.btHacerEfectivoRegistro)
        etEmail=findViewById(R.id.etNombreR)
        etContraseñaR=findViewById(R.id.etContraseñaR)
        val context = this

        btHacerEfectivoRegistro.setOnClickListener(){
            saveData()
            onBackPressed()


            var usuario = etEmail.text.toString()
            var contraseña = etContraseñaR.text.toString()

            val u = Usuario(usuario,contraseña)
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://damapi.herokuapp.com/api/v1/")
                .build()
            val llamadaApi: Call<Token> = RetrofictClient.apiRetrofit.login(u)


            llamadaApi.enqueue(object: Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Log.d("respuesta: onFailure", t.toString())

                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d("respuesta: onResponse", response.toString())

                    if (response.code() > 299 || response.code() < 200) {
                        showAlert("Usuario ")

                    } else {
                        val token = response.body()?.token
                        Log.d("respuesta: token:", token.orEmpty())

                       showAlert("Usuario creado correctamente")




                        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.apply(){
                            putString("TOKEN",token)
                        }.apply()
                        val intent = Intent(context, PeliculasActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(context,"datos Guardados", Toast.LENGTH_SHORT).show()









                        // TODO: Inicio nueva activity
                    }

                }
            })







        }

    }

    fun saveData(){
        val nombreR = etEmail.text.toString()
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
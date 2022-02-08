package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeliculasActivity : AppCompatActivity() {
    private lateinit var rvPeliculas : RecyclerView
    private lateinit var faButton: FloatingActionButton
    private lateinit var adapter: ListaPeliculasAdapter
    private lateinit var tvPrueba: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)
        rvPeliculas=findViewById(R.id.rvPeliculas)
        rvPeliculas.layoutManager = LinearLayoutManager(this)
        faButton = findViewById(R.id.faButton)




        val context = this


        faButton.setOnClickListener() {
            val intent = Intent(this, DetalleActivity::class.java)
            startActivity(intent) }

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val Token ="Bearer " + sharedPreferences.getString("TOKEN",null)
        Toast.makeText(context,Token, Toast.LENGTH_LONG).show()


        val llamadaAlApi: Call<List<Pelicula>> = RetrofictClient.apiRetrofit.getPeliculas(Token.toString())
        llamadaAlApi.enqueue(object: Callback<List<Pelicula>>{
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>

            ) {
                if (response.isSuccessful){
                    Toast.makeText(context,"Todo correcto", Toast.LENGTH_LONG).show()
                }else{
                    val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.apply(){
                        putString("TOKEN","")
                    }.apply()
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)

                }
                var listaPeliculas: List<Pelicula>? = response.body()

                var adapter = ListaPeliculasAdapter(listaPeliculas,context)
                rvPeliculas.adapter = adapter

             //   Toast.makeText(context, listaPeliculas, Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("Prueba",t.message.toString())
            }
        } )

    }
    fun loadData() {

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val nombreL = sharedPreferences.getString("TOKEN",null)

    }


     override fun onResume() {
         super.onResume()

     }

}
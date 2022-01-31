package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)
        rvPeliculas=findViewById(R.id.rvPeliculas)
        rvPeliculas.layoutManager = LinearLayoutManager(this)
        faButton = findViewById(R.id.faButton)
//hello

        val context = this

        faButton.setOnClickListener() {
            val intent = Intent(this, DetalleActivity::class.java)
            startActivity(intent)
        }
        val llamadaAlApi: Call<List<Pelicula>> = RetrofictClient.apiRetrofit.getPeliculas("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxZjc5Nzc4ODgxM2Q2ZTRlNDVmZWQwMyIsImlhdCI6MTY0MzYxNjk0OSwiZXhwIjoxNjQzNzAzMzQ5fQ.HoOat_nZnc0RNDzCusn5uWsxoVDoAeC6Krxj8Ot0XVY")
        llamadaAlApi.enqueue(object: Callback<List<Pelicula>>{
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>

            ) {
                if (response.isSuccessful){





                }
                var listaPeliculas = response.body().toString()

                Toast.makeText(context, listaPeliculas, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("Prueba",t.message.toString())
            }
        } )

    }


     override fun onResume() {
         super.onResume()

     }







}
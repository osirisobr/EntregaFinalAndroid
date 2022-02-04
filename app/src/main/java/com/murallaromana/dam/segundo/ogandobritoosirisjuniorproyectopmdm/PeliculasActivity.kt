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
//helloo

        val context = this

        faButton.setOnClickListener() {
            val intent = Intent(this, DetalleActivity::class.java)
            startActivity(intent)
        }
        val llamadaAlApi: Call<List<Pelicula>> = RetrofictClient.apiRetrofit.getPeliculas("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxZjhmMDc2MGFiYjI3OGE5YzQ1Mjg3NSIsImlhdCI6MTY0Mzk3NTIzMiwiZXhwIjoxNjQ0MDYxNjMyfQ.XsoWD3dC2Imixsjebx9g1kDXi8ty6OwYGzaGcLbEhYk")
        llamadaAlApi.enqueue(object: Callback<List<Pelicula>>{
            override fun onResponse(
                call: Call<List<Pelicula>>,
                response: Response<List<Pelicula>>

            ) {
                if (response.isSuccessful){


                    Toast.makeText(context,"", Toast.LENGTH_LONG).show()


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
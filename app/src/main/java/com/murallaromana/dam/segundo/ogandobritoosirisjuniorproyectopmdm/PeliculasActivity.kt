package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.App.Companion.peliculas

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
        val peliculasDao = PeliculasDaoMockImpl()
        val peliculas = peliculasDao.getTodos()
        var adapter = ListaPeliculasAdapter(peliculas, this)
        rvPeliculas.adapter = adapter



        faButton.setOnClickListener() {
            val intent = Intent(this, DetalleActivity::class.java)
            startActivity(intent)


        }

    }









    override fun onBackPressed() {




        if (true) {

        } else {

        }
    }





    fun saveData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply(){
            putString("NOMBREL",null)
            putString("CONTRASEÑAL",null)
        }.apply()

        Toast.makeText(this,"Sesion Cerrada", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}
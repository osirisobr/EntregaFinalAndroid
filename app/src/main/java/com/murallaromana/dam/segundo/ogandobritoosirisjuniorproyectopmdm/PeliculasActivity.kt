package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeliculasActivity : AppCompatActivity() {
    companion object {
        var itemCerrarSesion: MenuItem? = null
    }

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




        faButton.setOnClickListener() {
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("estado",true)
            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_peliculas, menu)
        if (menu != null) {
           itemCerrarSesion = menu.findItem(R.id.action_cerrarsesion)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_cerrarsesion -> {
                val context=this
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Cerrar Sesion")
                    .setMessage("Se cerrara la sesion. ¿Estas seguro?.")
                    .setPositiveButton("Aceptar") { _, _ ->
                        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.apply() {
                            putString("TOKEN", null)
                        }.apply()

                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Cancelar", null).create()
                dialog.show()

                return true

            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


     override fun onResume() {
         super.onResume()
         val context = this
         val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

         val Token ="Bearer " + sharedPreferences.getString("TOKEN",null)
         Toast.makeText(context,Token, Toast.LENGTH_LONG).show()


         val llamadaAlApi: Call<List<Pelicula>> = RetrofictClient.apiRetrofit.getPeliculas(Token)
         llamadaAlApi.enqueue(object: Callback<List<Pelicula>>{
             override fun onResponse(
                 call: Call<List<Pelicula>>,
                 response: Response<List<Pelicula>>

             ) {
                 if (response.isSuccessful){
                     Toast.makeText(context,"Sesion Iniciada", Toast.LENGTH_LONG).show()
                 }else{
                     val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                     val editor = sharedPreferences.edit()
                     editor.apply(){
                         putString("TOKEN",null)
                     }.apply()
                     val intent = Intent(context, LoginActivity::class.java)
                     startActivity(intent)
                     finish()

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

}
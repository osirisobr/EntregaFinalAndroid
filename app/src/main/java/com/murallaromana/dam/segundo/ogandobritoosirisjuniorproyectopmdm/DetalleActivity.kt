package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Token
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Arrays.toString
import java.util.Objects.toString

class DetalleActivity : AppCompatActivity() {



    companion object {
        var itemGuardar: MenuItem? = null
        var itemEditar: MenuItem? = null
        var itemBorrar: MenuItem? = null
    }
    lateinit var ibLlamarDirectorD: ImageButton
    lateinit var ivCaratulaDetalle: ImageView
    lateinit var tvDirectorDetalles: EditText
    lateinit var tvTituloDetalle: EditText
    lateinit var etUrl: EditText
    lateinit var tvAñoPeliculaDetalle:EditText
    lateinit var tvSinopsisDetalle:EditText
    lateinit var tvGeneroDetalle: EditText
    private lateinit var pelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        ibLlamarDirectorD = findViewById(R.id.ibLlamarDirectorD)
        ivCaratulaDetalle = findViewById(R.id.ivCaratulaDetalle)
        tvDirectorDetalles = findViewById(R.id.tvDirectorDetalle)
        tvAñoPeliculaDetalle = findViewById(R.id.tvAñoPeliculaDetalle)
        tvTituloDetalle = findViewById(R.id.tvAñoPeliculaDetalle)
        tvSinopsisDetalle = findViewById(R.id.tvSinopsisDetalles)
        tvGeneroDetalle = findViewById(R.id.tvGeneroDetalle)
        etUrl = findViewById(R.id.etUrl)
        val context = this
        ibLlamarDirectorD.setOnClickListener(){

            val telefono = "tel:"+pelicula.numeroDirector
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))
        }
        tvTituloDetalle.isEnabled = false
        tvGeneroDetalle.isEnabled = false
        tvSinopsisDetalle.isEnabled = false
        tvDirectorDetalles.isEnabled = false
        etUrl.isEnabled = false

        tvTituloDetalle.setTextColor(Color.BLACK);
        tvGeneroDetalle.setTextColor(Color.BLACK);
        tvSinopsisDetalle.setTextColor(Color.BLACK);
        tvDirectorDetalles.setTextColor(Color.BLACK);
        etUrl.setTextColor(Color.BLACK);
    }
    override fun onResume() {
        super.onResume()

        if (intent.extras?.get("pelicula") != null) {
            pelicula = intent.extras?.get("pelicula") as Pelicula
            title = pelicula.titulo
            tvGeneroDetalle.setText(pelicula.genero)
            tvDirectorDetalles.setText(pelicula.director)
            tvTituloDetalle.setText(pelicula.titulo)
            tvSinopsisDetalle.setText(pelicula.sinopsis)
            etUrl.setText(pelicula.url)
            Picasso.get().load(pelicula.url).into(ivCaratulaDetalle)
        } else {
            title = "nueva pelicula"
            tvTituloDetalle.isEnabled = true
            tvGeneroDetalle.isEnabled = true
            tvSinopsisDetalle.isEnabled = true
            tvDirectorDetalles.isEnabled = true
            etUrl.isEnabled = true
        }

}
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        if (menu != null) {
            itemGuardar = menu.findItem(R.id.action_guardar)
            itemBorrar = menu.findItem(R.id.action_borrar)
            itemEditar = menu.findItem(R.id.action_editar)


        }
        return true
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (intent.extras?.get("pelicula") != null) {
            itemEditar?.isVisible = true
            itemGuardar?.isVisible = false
            itemBorrar?.isVisible = true

        } else {
            itemEditar?.isVisible = false
            itemGuardar?.isVisible = true
            itemBorrar?.isVisible = false

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_editar -> {

                tvTituloDetalle.isEnabled = true
                tvGeneroDetalle.isEnabled = true
                tvSinopsisDetalle.isEnabled = true
                tvDirectorDetalles.isEnabled = true

                Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show()
                itemGuardar?.isVisible = true
                itemEditar?.isVisible = false
                itemBorrar?.isVisible = false


                return true
            }
            R.id.action_borrar -> {

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Eliminar pelicula")
                    .setMessage("la pelicula " +pelicula.titulo + "sera eliminada. ¿Estas seguro?.")
                    .setPositiveButton("Aceptar") { _, _ ->
                    App.peliculas.remove(pelicula)
                        Toast.makeText(this, "Película Borrada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Cancelar", null).create()
                dialog.show()

                return true


            }

            R.id.action_guardar -> {
                if (tvSinopsisDetalle.text.toString().isEmpty() || tvDirectorDetalles.text.toString()
                        .isEmpty() || tvDirectorDetalles.text.toString().isEmpty()
                    || tvTituloDetalle.text.toString()
                        .isEmpty() || tvGeneroDetalle.text.toString().isEmpty()

                ) {


                    if (tvTituloDetalle.text.toString().isEmpty()) {
                        tvTituloDetalle.error = "El campo titulo no puede estar vacio"
                    }
                    if (tvSinopsisDetalle.text.toString().isEmpty()) {
                        tvSinopsisDetalle.error = "El campo nota no puede estar vacio"
                    }
                    if (tvDirectorDetalles.text.toString().isEmpty()) {
                        tvDirectorDetalles.error = "El campo Director no puede estar vacio"
                    }
                    if (tvGeneroDetalle.text.toString().isEmpty()) {
                        tvGeneroDetalle.error = "El campo Genero no puede estar vacio"
                    }

                } else {
                    val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

                    val Token ="Bearer " + sharedPreferences.getString("TOKEN",null)



                      val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://damapi.herokuapp.com/api/v1/")
                        .build()
                    val llamadaApi: Call<Unit> = RetrofictClient.apiRetrofit.create(
                        //Terminar de rellenar con los editTexts

                        Pelicula(tvTituloDetalle.text.toString(),"8","Accion","Sakamoto","2000","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/e9UQGoX2ZFNdrjLwEejMSMDSxmR.jpg","100 participantes, uno sobrevive","456","9375638273")
                        ,Token.toString())


                    llamadaApi.enqueue(object: Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("respuesta: onFailure", t.toString())
                            showAlert("Pelicula no añadia")

                        }

                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            Log.d("respuesta: onResponse", response.toString())

                            if (response.code() > 299 || response.code() < 200) {
                                showAlert("movida rara")

                            } else {

                                showAlert("Pelicula añadida")


                            }

                        }
                    })
























                }
                return true
            }


            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hola soy pavy")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }

}
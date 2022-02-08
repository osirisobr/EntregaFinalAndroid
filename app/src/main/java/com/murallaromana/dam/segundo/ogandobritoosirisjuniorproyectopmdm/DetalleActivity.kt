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
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Id
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.Token
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetalleActivity : AppCompatActivity() {


    companion object {
        var itemGuardar: MenuItem? = null
        var itemEditar: MenuItem? = null
        var itemBorrar: MenuItem? = null
    }

    lateinit var ibLlamarDirectorD: ImageButton
    lateinit var ivCaratulaDetalle: ImageView
    lateinit var etDirector: EditText
    lateinit var etTituloDetalle: EditText
    lateinit var etRating: EditText
    lateinit var etDuracion: EditText
    lateinit var etUrl: EditText
    lateinit var etAñoPeliculaDetalle: EditText
    lateinit var etSinopsisDetalle: EditText
    lateinit var etGeneroDetalle: EditText
    lateinit var etNumeroDirector: EditText

    private lateinit var pelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        ibLlamarDirectorD = findViewById(R.id.ibLlamarDirectorD)
        ivCaratulaDetalle = findViewById(R.id.ivCaratulaDetalle)
        etRating = findViewById(R.id.etRatingDetalle)
        etDirector = findViewById(R.id.etDirector)
        etAñoPeliculaDetalle = findViewById(R.id.etAnhoDetalle)
        etTituloDetalle = findViewById(R.id.etTituloDetalle)
        etSinopsisDetalle = findViewById(R.id.tvSinopsisDetalles)
        etGeneroDetalle = findViewById(R.id.etGeneroDetalle)
        etDuracion = findViewById(R.id.etTiempoDuracion)
        etUrl = findViewById(R.id.etGeneroDetalle)
        etNumeroDirector = findViewById(R.id.etNumeroDirector)


        val context = this
        ibLlamarDirectorD.setOnClickListener() {

            val telefono = "tel:" + pelicula.numeroDirector
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))
        }
        etTituloDetalle.isEnabled = false
        etGeneroDetalle.isEnabled = false
        etSinopsisDetalle.isEnabled = false
        etDirector.isEnabled = false
        etUrl.isEnabled = false
        etRating.isEnabled = false
        etAñoPeliculaDetalle.isEnabled = false
        etDuracion.isEnabled = false
        etNumeroDirector.isEnabled = false


        etTituloDetalle.setTextColor(Color.BLACK)
        etGeneroDetalle.setTextColor(Color.BLACK)
        etAñoPeliculaDetalle.setTextColor(Color.BLACK)
        etRating.setTextColor(Color.BLACK)
        etDuracion.setTextColor(Color.BLACK)
        etNumeroDirector.setTextColor(Color.BLACK)
        etSinopsisDetalle.setTextColor(Color.BLACK)
        etDirector.setTextColor(Color.BLACK)
        etUrl.setTextColor(Color.BLACK)
    }

    override fun onResume() {
        super.onResume()

        if (intent.extras?.get("pelicula") != null) {
            pelicula = intent.extras?.get("pelicula") as Pelicula
            title = pelicula.titulo
            etGeneroDetalle.setText(pelicula.genero)
            etDirector.setText(pelicula.director)
            etTituloDetalle.setText(pelicula.titulo)
            etSinopsisDetalle.setText(pelicula.sinopsis)
            etRating.setText(pelicula.rating)
            etAñoPeliculaDetalle.setText(pelicula.año)
            etDuracion.setText(pelicula.duracion)
            etUrl.setText(pelicula.url)

            Picasso.get().load(pelicula.url).into(ivCaratulaDetalle)
        } else {
            title = "nueva pelicula"
            etTituloDetalle.isEnabled = true
            etGeneroDetalle.isEnabled = true
            etSinopsisDetalle.isEnabled = true
            etDirector.isEnabled = true
            etUrl.isEnabled = true
            etAñoPeliculaDetalle.isEnabled = true
            etRating.isEnabled = true
            etDuracion.isEnabled = true
            etNumeroDirector.isEnabled = true
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


                etTituloDetalle.isEnabled = true
                etGeneroDetalle.isEnabled = true
                etSinopsisDetalle.isEnabled = true
                etDirector.isEnabled = true
                etUrl.isEnabled = true
                etAñoPeliculaDetalle.isEnabled = true
                etRating.isEnabled = true
                etDuracion.isEnabled = true
                etNumeroDirector.isEnabled = true


                Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show()
                itemGuardar?.isVisible = true
                itemEditar?.isVisible = false
                itemBorrar?.isVisible = false

                //Editar id (En Proceso)-----------------------------------------------------------






























                return true
            }
            R.id.action_borrar -> {

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Eliminar pelicula")
                    .setMessage("la pelicula " + pelicula.titulo + "sera eliminada. ¿Estas seguro?.")
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












                if (etTituloDetalle.text.toString().isEmpty() || etDuracion.text.toString()
                        .isEmpty() || etRating.text.toString().isEmpty()
                ) {


                    if (etTituloDetalle.text.toString().isEmpty()) {
                        etTituloDetalle.error = "El campo titulo no puede estar vacio"
                    }
                    if (etDuracion.text.toString().isEmpty()) {
                        etDuracion.error = "El campo duracion no puede estar vacio"
                    }
                    if (etRating.text.toString().isEmpty()) {
                        etRating.error = "El campo rating no puede estar vacio"
                    }


                } else {


                            //Todo en procesoo

                  val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                    val llamadaApi: Call<Pelicula> = RetrofictClient.apiRetrofit.getId(Token,pelicula.id)
                    llamadaApi.enqueue(object : Callback<Pelicula>{
                        override fun onResponse(
                            call: Call<Pelicula>,
                            response: Response<Pelicula>
                        ) {
                            var id = response.body()?.id
                            if ( id != null ){

                                val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                                val llamadaApi: Call<Unit> = RetrofictClient.apiRetrofit.update(  Pelicula(
                                    etTituloDetalle.text.toString(),
                                    etRating.text.toString(),
                                    etGeneroDetalle.text.toString(),
                                    etDirector.text.toString(),
                                    etAñoPeliculaDetalle.text.toString(),
                                    etUrl.text.toString(),
                                    etSinopsisDetalle.text.toString(),
                                    etDuracion.text.toString(),
                                    etNumeroDirector.text.toString(),
                                    pelicula.id
                                ), Token   )
                                llamadaApi.enqueue(object : Callback<Unit>{
                                    override fun onResponse(
                                        call: Call<Unit>,
                                        response: Response<Unit>
                                    ) {
                                        showAlert("pelicula actualizada")
                                        finish()
                                    }

                                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                                       showAlert("Algo mal va mal en actualizar")
                                    }
                                })


                            }   else {
                                val sharedPreferences =
                                    getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                                val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                                val retrofit = Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .baseUrl("https://damapi.herokuapp.com/api/v1/")
                                    .build()
                                val llamadaApi: Call<Unit> = RetrofictClient.apiRetrofit.create(

                                    //Terminar de rellenar con los editTexts

                                    Pelicula(
                                        etTituloDetalle.text.toString(),
                                        etRating.text.toString(),
                                        etGeneroDetalle.text.toString(),
                                        etDirector.text.toString(),
                                        etAñoPeliculaDetalle.text.toString(),
                                        etUrl.text.toString(),
                                        etSinopsisDetalle.text.toString(),
                                        etDuracion.text.toString(),
                                        etNumeroDirector.text.toString(),
                                        null
                                    ), Token
                                )

                                llamadaApi.enqueue(object : Callback<Unit> {
                                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                                        Log.d("respuesta: onFailure", t.toString())
                                        showAlert("Pelicula no añadia")

                                    }

                                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                        Log.d("respuesta: onResponse", response.toString())

                                        if (response.code() > 299 || response.code() < 200) {
                                            showAlert("Error " + response.code().toString())

                                        } else {

                                            showAlert("Pelicula creada")
                                        }
                                    }
                                })


                            }
                        }

                        override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                           showAlert("Error en añadir")



                        }

                    })


                    etTituloDetalle.isEnabled = true
                    etGeneroDetalle.isEnabled = true
                    etSinopsisDetalle.isEnabled = true
                    etDirector.isEnabled = true
                    etUrl.isEnabled = true
                    etAñoPeliculaDetalle.isEnabled = true
                    etRating.isEnabled = true
                    etDuracion.isEnabled = true
                    etNumeroDirector.isEnabled = true


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
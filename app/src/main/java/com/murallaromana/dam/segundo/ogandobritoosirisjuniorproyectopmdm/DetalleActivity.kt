package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm.Retrofit.RetrofictClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    lateinit var etA├▒oPeliculaDetalle: EditText
    lateinit var etSinopsisDetalle: EditText
    lateinit var etGeneroDetalle: EditText
    lateinit var etNumeroDirector: EditText

    private var pelicula: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        ibLlamarDirectorD = findViewById(R.id.ibLlamarDirectorD)
        ivCaratulaDetalle = findViewById(R.id.ivCaratulaDetalle)
        etRating = findViewById(R.id.etRatingDetalle)
        etDirector = findViewById(R.id.etDirector)
        etA├▒oPeliculaDetalle = findViewById(R.id.etAnhoDetalle)
        etTituloDetalle = findViewById(R.id.etTituloDetalle)
        etSinopsisDetalle = findViewById(R.id.tvSinopsisDetalles)
        etGeneroDetalle = findViewById(R.id.etGeneroDetalle)
        etDuracion = findViewById(R.id.etTiempoDuracion)
        etUrl = findViewById(R.id.etUrlDetalle)
        etNumeroDirector = findViewById(R.id.etNumeroDirector)
        var estado:Boolean = intent.extras?.get("estado") as Boolean
        etNumeroDirector.isVisible = estado


     /*   if (estado == true){
            etNumeroDirector.isVisible = true

        }else{
            etNumeroDirector.isVisible = false
        }

        //    val extras = intent.extras


*/



        val context = this
        ibLlamarDirectorD.setOnClickListener() {

            val telefono = "tel:" + pelicula?.numeroDirector
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))
        }
        etTituloDetalle.isEnabled = false
        etGeneroDetalle.isEnabled = false
        etSinopsisDetalle.isEnabled = false
        etDirector.isEnabled = false
        etUrl.isEnabled = false
        etRating.isEnabled = false
        etA├▒oPeliculaDetalle.isEnabled = false
        etDuracion.isEnabled = false
        etNumeroDirector.isEnabled = false


        etTituloDetalle.setTextColor(Color.BLACK)
        etGeneroDetalle.setTextColor(Color.BLACK)
        etA├▒oPeliculaDetalle.setTextColor(Color.BLACK)
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
            title = pelicula?.titulo
            etGeneroDetalle.setText(pelicula?.genero)
            etDirector.setText(pelicula?.director)
            etTituloDetalle.setText(pelicula?.titulo)
            etSinopsisDetalle.setText(pelicula?.sinopsis)
            etRating.setText(pelicula?.rating)
            etA├▒oPeliculaDetalle.setText(pelicula?.a├▒o)
            etDuracion.setText(pelicula?.duracion)
            etNumeroDirector.setText(pelicula?.numeroDirector)
            etUrl.setText(pelicula?.url)

            Picasso.get().load(pelicula?.url).into(ivCaratulaDetalle)
        } else {
            title = "nueva pelicula"
            etTituloDetalle.isEnabled = true
            etGeneroDetalle.isEnabled = true
            etSinopsisDetalle.isEnabled = true
            etDirector.isEnabled = true
            etUrl.isEnabled = true
            etA├▒oPeliculaDetalle.isEnabled = true
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

                etNumeroDirector.isVisible = true
                etTituloDetalle.isEnabled = true
                etGeneroDetalle.isEnabled = true
                etSinopsisDetalle.isEnabled = true
                etDirector.isEnabled = true
                etUrl.isEnabled = true
                etA├▒oPeliculaDetalle.isEnabled = true
                etRating.isEnabled = true
                etDuracion.isEnabled = true
                etNumeroDirector.isEnabled = true


                Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show()
                itemGuardar?.isVisible = true
                itemEditar?.isVisible = false
                itemBorrar?.isVisible = false
                return true
            }
            R.id.action_borrar -> {

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Eliminar pelicula")
                    .setMessage("la pelicula " + pelicula?.titulo + "sera eliminada. ┬┐Estas seguro?.")
                    .setPositiveButton("Aceptar") { _, _ ->
                        App.peliculas.remove(pelicula)


                        //Configuracion retrofit

                        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                        val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                        val llamadaApi: Call<Pelicula> = RetrofictClient.apiRetrofit.getId(Token,pelicula?.id)
                        llamadaApi.enqueue(object : Callback<Pelicula>{
                            override fun onResponse(
                                call: Call<Pelicula>,
                                response: Response<Pelicula>
                            ) {
                                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                                val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                                val llamadaApi: Call<Unit> = RetrofictClient.apiRetrofit.borrar(Token,pelicula?.id)
                                llamadaApi.enqueue(object : Callback<Unit>{
                                    override fun onResponse(
                                        call: Call<Unit>,
                                        response: Response<Unit>
                                    ) {
                                    }
                                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                                        TODO("Not yet implemented")
                                    }
                                })
                            }
                            override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                        Toast.makeText(this, "Pel├şcula Borrada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Cancelar", null).create()
                dialog.show()

                return true

            }

            R.id.action_guardar -> {

                if (etTituloDetalle.text.toString().isEmpty() || etDuracion.text.toString()
                        .isEmpty() || etRating.text.toString().isEmpty() || etGeneroDetalle.text.toString().isEmpty() || etDirector.text.toString().isEmpty() || etA├▒oPeliculaDetalle.text.toString().isEmpty() || etUrl.text.toString().isEmpty() || etNumeroDirector.text.toString().isEmpty() || etSinopsisDetalle.text.toString().isEmpty()
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
                    if (etGeneroDetalle.text.toString().isEmpty()) {
                        etGeneroDetalle.error = "El campo Genero no puede estar vacio"
                    }
                    if (etDirector.text.toString().isEmpty()) {
                        etDirector.error = "El campo Director no puede estar vacio"
                    }
                    if (etSinopsisDetalle.text.toString().isEmpty()) {
                        etSinopsisDetalle.error = "El campo Sinopsis no puede estar vacio"
                    }
                    if (etUrl.text.toString().isEmpty()) {
                        etUrl.error = "El campo Url no puede estar vacio"
                    }
                    if (etA├▒oPeliculaDetalle.text.toString().isEmpty()) {
                        etA├▒oPeliculaDetalle.error = "El campo Numero no puede estar vacio"
                    }
                    if (etNumeroDirector.text.toString().isEmpty()) {
                        etNumeroDirector.error = "El campo Sinopsis no puede estar vacio"
                    }


                } else {


                            //Todo en procesoo


                                if (intent.extras?.get("id") != null){

                                    val sharedPreferences =  getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                                    val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                                    val llamadaApi: Call<Unit> = RetrofictClient.apiRetrofit.update(  Pelicula(
                                        etTituloDetalle?.text.toString(),
                                        etRating?.text.toString(),
                                        etGeneroDetalle?.text.toString(),
                                        etDirector?.text.toString(),
                                        etA├▒oPeliculaDetalle?.text.toString(),
                                        etUrl?.text.toString(),
                                        etSinopsisDetalle?.text.toString(),
                                        etDuracion?.text.toString(),
                                        etNumeroDirector?.text.toString(),
                                        pelicula!!.id
                                    ), Token   )

                                    val context = this
                                    llamadaApi.enqueue(object : Callback<Unit>{
                                        override fun onResponse(
                                            call: Call<Unit>,
                                            response: Response<Unit>
                                        ) {





                                            if (response.isSuccessful){
                                                showAlert("pelicula actualizada")
                                                showAlert(response.code().toString())
                                                finish()
                                            }else {
                                                val sharedPreferences = getSharedPreferences(
                                                    "sharedPrefs",
                                                    MODE_PRIVATE
                                                )
                                                val editor = sharedPreferences.edit()
                                                editor.apply() {
                                                    putString("TOKEN", null)
                                                }.apply()
                                                val intent = Intent(context, LoginActivity::class.java)
                                                startActivity(intent)
                                                finish()
                                            }









                                        }

                                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                                            showAlert("Pelicula no actualizada")
                                        }
                                    })






                                }else{


                                    val sharedPreferences =
                                        getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                                    val Token = "Bearer " + sharedPreferences.getString("TOKEN", null)
                                    val llamadaApi: Call<Unit> = RetrofictClient.apiRetrofit.create(

                                        //Terminar de rellenar con los editTexts

                                        Pelicula(
                                            etTituloDetalle?.text.toString(),
                                            etRating?.text.toString(),
                                            etGeneroDetalle?.text.toString(),
                                            etDirector?.text.toString(),
                                            etA├▒oPeliculaDetalle?.text.toString(),
                                            etUrl?.text.toString(),
                                            etSinopsisDetalle?.text.toString(),
                                            etDuracion?.text.toString(),
                                            etNumeroDirector?.text.toString(),pelicula?.id

                                        ), Token
                                    )
                                    llamadaApi.enqueue(object : Callback<Unit> {
                                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                                            Log.d("respuesta: onFailure", t.toString())
                                            showAlert("Pelicula no a├▒adia")
                                        }
                                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                            Log.d("respuesta: onResponse", response.toString())

                                            if (response.code() > 299 || response.code() < 200) {
                                                showAlert("Error " + response.code().toString())
                                            } else {
                                                showAlert("Pelicula creada")
                                                onBackPressed()
                                                finish()
                                            }
                                        }
                                    })
                                }
                    etTituloDetalle.isEnabled = true
                    etGeneroDetalle.isEnabled = true
                    etSinopsisDetalle.isEnabled = true
                    etDirector.isEnabled = true
                    etUrl.isEnabled = true
                    etA├▒oPeliculaDetalle.isEnabled = true
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
        builder.setTitle("Aviso")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
}
































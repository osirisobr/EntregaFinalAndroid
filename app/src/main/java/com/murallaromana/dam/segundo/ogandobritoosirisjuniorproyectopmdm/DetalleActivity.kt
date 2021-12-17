package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import java.lang.Exception

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

        ibLlamarDirectorD.setOnClickListener(){

            val telefono = "tel:604001000"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))
        }
        tvTituloDetalle.isEnabled = false
        tvGeneroDetalle.isEnabled = false
        tvSinopsisDetalle.isEnabled = false
        tvDirectorDetalles.isEnabled = false
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
            Picasso.get().load(pelicula.imagenPanoramica).into(ivCaratulaDetalle)
        } else {
            title = "nueva pelicula"
            tvTituloDetalle.isEnabled = true
            tvGeneroDetalle.isEnabled = true
            tvSinopsisDetalle.isEnabled = true
            tvDirectorDetalles.isEnabled = true

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

                }
                return true
            }


            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}